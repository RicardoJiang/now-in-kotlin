/*
* Copyright (C) 2024 Huawei Device Co., Ltd.
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import { common, wantAgent } from '@kit.AbilityKit';
import { util } from '@kit.ArkTS';
import { audio } from '@kit.AudioKit';
import { avSession } from '@kit.AVSessionKit';
import { backgroundTaskManager } from '@kit.BackgroundTasksKit';
import { BusinessError } from '@kit.BasicServicesKit';
import { media } from '@kit.MediaKit';
import Constants, { AVPlayerState } from './Constants';
import GlobalContext from './GlobalContextUtils';
import MediaPlayerUtils from './MediaPlayerUtils';
import Logger from './Logger';
import { Episode } from './Episode';
import { ImageUtil } from './ImageUtil';
import { getMediaCommandHandler, getOhosStateListener } from '../knoi/provider';

/**
 * Processes playback logic and interacts with the AVSession.
 */
const TAG = 'ProviderFeature';

export class ProviderFeature {
  private static providerSelf: ProviderFeature | undefined = undefined;
  private context: common.UIAbilityContext | undefined =
    GlobalContext.getContext().getObject(Constants.CONTEXT) as common.UIAbilityContext;
  private session: avSession.AVSession | null = null;
  private isPlayLink: SubscribedAbstractProperty<boolean> | null = null;
  private currentLoopModeLink: SubscribedAbstractProperty<avSession.LoopMode> | undefined = undefined;
  private currentState: avSession.AVPlaybackState = {
    state: avSession.PlaybackState.PLAYBACK_STATE_PAUSE
  };
  private mediaPlayerUtil: MediaPlayerUtils = new MediaPlayerUtils();
  private avPlayer?: media.AVPlayer;
  currentTime: number = 0;
  duration: number = 0;

  static async getInstance(): Promise<ProviderFeature> {
    Logger.info(TAG, ' provider getInstance');
    if (!ProviderFeature.providerSelf) {
      Logger.info(TAG, ' new provider');
      ProviderFeature.providerSelf = new ProviderFeature();
      await ProviderFeature.providerSelf.init();
    }
    return ProviderFeature.providerSelf;
  }

  private constructor() {
    this.isPlayLink = AppStorage.setAndLink('IsPlaying', false);
    this.currentLoopModeLink =
      AppStorage.setAndLink<avSession.LoopMode>('CurrentLoopMode', avSession.LoopMode.LOOP_MODE_SEQUENCE);
    this.currentLoopModeLink!.set(avSession.LoopMode.LOOP_MODE_SEQUENCE)
  }

  /**
   * Initialize resources.
   */
  async init(): Promise<void> {
    this.avPlayer = await this.mediaPlayerUtil.init();
    await this.CreateAVSession();
    await this.InitFirstMusicState();
    await this.RegisterAVPlayerListener();
  }

  async unInit(): Promise<void> {
    this.UnRegisterListener();
    if (this.avPlayer) {
      this.unRegisterAVPlayerListener();
      this.avPlayer.release();
    }
  }

  /**
   * Reads local lyrics.
   */
  async getLocalLrc(fileName: string): Promise<string> {
    const value: Uint8Array = await this.context!.resourceManager.getRawFileContent(fileName);
    Logger.info(TAG, 'local lrc:' + fileName + ':' + value);

    if (!value) {
      Logger.error(TAG, 'get lyc fail:' + fileName);
      return Promise.reject('get lyc fail');
    }
    let textDecoder = util.TextDecoder.create('utf-8', { ignoreBOM: true });
    let retStr: string = textDecoder.decodeToString(value, { stream: false });
    Logger.info(TAG, 'retStr: ' + retStr);
    return retStr;
  }

  /**
   * Start a long-time task.
   */
  async startContinuousTask(): Promise<void> {
    const context: common.UIAbilityContext | undefined = AppStorage.get('context')
    if (!context) {
      return
    }
    let wantAgentInfo: wantAgent.WantAgentInfo = {
      wants: [
        {
          bundleName: context.abilityInfo.bundleName,
          abilityName: context.abilityInfo.name
        }
      ],
      operationType: wantAgent.OperationType.START_ABILITY,
      requestCode: 0,
      wantAgentFlags: [wantAgent.WantAgentFlags.UPDATE_PRESENT_FLAG]
    };

    let tmpWantAgent = await wantAgent.getWantAgent(wantAgentInfo);
    // Sets the application page that can be invoked when a playback control card is clicked.
    this.session?.setLaunchAbility(tmpWantAgent);
    await backgroundTaskManager.startBackgroundRunning(context,
      backgroundTaskManager.BackgroundMode.AUDIO_PLAYBACK, tmpWantAgent);
  }

  /**
   * Close a long-time task.
   */
  async stopContinuousTask(): Promise<boolean> {
    Logger.info(TAG, 'stop background ');
    return new Promise((resolve) => {
      backgroundTaskManager.stopBackgroundRunning(this.context, (err, _) => {
        if (err) {
          Logger.error(TAG, `stop background startBackgroundRunning err ${err.stack}`);
          resolve(false);
        } else {
          Logger.info(TAG, `stop background startBackgroundRunning success`);
          resolve(true);
        }
      });
    });
  }

  /**
   * Create AVSession.
   */
  async CreateAVSession(): Promise<boolean> {
    Logger.info(TAG, `Start create AVSession`);
    let ret: boolean = true;
    if (this.session) {
      Logger.info(TAG, `has a session: ` + this.session.sessionId);
      return ret;
    }
    // Create an audio session. The Favorites, Previous, Pause, and Repeat buttons are displayed on the playback control page.
    // A developer can also create a session of the video type. The playback control page displays the fast-forward, rewind, and pause buttons.
    this.session = await avSession.createAVSession(this.context!, 'AVSessionDemo', 'audio');
    // Activate the session.
    this.RegisterSessionListener();
    await this.session?.activate().catch((err: BusinessError) => {
      if (err) {
        Logger.error(TAG, `Failed to activate AVSession, error info: ${JSON.stringify(err)}`);
        ret = false;
      }
    });
    return ret;
  }

  async InitFirstMusicState(): Promise<void> {
    Logger.info(TAG, ` InitFirstMusicState`);
    this.isPlayLink!.set(false);
    this.currentState.state = avSession.PlaybackState.PLAYBACK_STATE_PAUSE;
    await this.session?.setAVPlaybackState(this.currentState);
  }

  /**
   * Registers the session callback function.
   * Register the command as required. If the command is not supported, do not register it.
   * For the session of the audio type, implement the progress bar, favorites, next song, play/pause, and loop mode callback functions. If the functions are not supported, disable the interface or do not register the interface.
   * For a session of the video type, implement the fast-forward, rewind, next episode, and playback pause callback functions. If the functions are not supported, disable the interface or do not register the interface.
   */
  async RegisterSessionListener(): Promise<void> {
    // Processes playback commands.
    this.session?.on('play', async () => {
      Logger.info(TAG, `on play, do play task`);
      this.avPlayer?.play();
      this.isPlayLink!.set(true);
      this.currentState = {
        state: avSession.PlaybackState.PLAYBACK_STATE_PLAY,
        position: {
          elapsedTime: this.currentTime,
          updateTime: new Date().getTime(),
        }
      };
      await this.session?.setAVPlaybackState(this.currentState);
    });

    // Suspend instruction processing.
    this.session?.on('pause', async () => {
      Logger.info(TAG, `on pause, do pause task`);
      this.avPlayer?.pause();
      this.isPlayLink!.set(false);
      this.currentState = {
        state: avSession.PlaybackState.PLAYBACK_STATE_PAUSE,
        position: {
          elapsedTime: this.currentTime,
          updateTime: new Date().getTime(),
        }
      };
      await this.session?.setAVPlaybackState(this.currentState);
    });

    // Stop instruction processing.
    this.session?.on('stop', async () => {
      Logger.info(TAG, `on stop , do stop task`);
      this.avPlayer?.stop();
      this.isPlayLink!.set(false);
      this.currentState.state = avSession.PlaybackState.PLAYBACK_STATE_PAUSE;
      await this.session?.setAVPlaybackState(this.currentState);
    });

    // Next song/set instruction processing.
    this.session?.on('playNext', async () => {
      Logger.info(TAG, `on playNext , do playNext task`);
      getMediaCommandHandler().onNext()
    });

    // Previous song/set instruction processing.
    this.session?.on('playPrevious', async () => {
      Logger.info(TAG, `on playPrevious , do playPrevious task`);
      getMediaCommandHandler().onPrevious()
    });

    // Processes the progress bar dragging command.
    this.session?.on('seek', (position) => {
      Logger.info(TAG, 'on seek: seek to' + position);
      // Modify the playback progress based on the instruction.
      this.avPlayer?.seek(position);
      this.currentState.position = {
        elapsedTime: position,
        updateTime: new Date().getTime()
      };
      this.session?.setAVPlaybackState(this.currentState);
    });
  }

  /**
   * Deregister a session callback.
   */
  async UnRegisterListener(): Promise<void> {
    if (this.session) {
      this.session.off('play');
      this.session.off('pause');
      this.session.off('stop');
      this.session.off('playNext');
      this.session.off('playPrevious');
      this.session.off('fastForward');
      this.session.off('rewind');
      this.session.off('seek');

      // Destroys a created session.
      this.session.destroy((err) => {
        if (err) {
          Logger.info(TAG, `Destroy BusinessError: code: ${err.code}, message: ${err.message}`);
        } else {
          Logger.info(TAG, 'Destroy : SUCCESS');
        }
      });
    }
  }

  /**
   * Processing logic of the avplayer when the playback is paused.
   */
  async localPlayOrPause(): Promise<void> {
    Logger.info(TAG, 'localPlayOrPause start play' + this.avPlayer?.state);
    if (!this.avPlayer) {
      Logger.error(TAG, 'localPlayOrPause start play no avplayer');
      return;
    }
    if (this.avPlayer.state === AVPlayerState.PLAYING) {
      Logger.info(TAG, 'localPlayOrPause start play start pause');
      await this.avPlayer.pause();
      this.isPlayLink!.set(false);
    } else if (this.avPlayer.state === AVPlayerState.STOPPED) {
      Logger.info(TAG, 'localPlayOrPause start play from stopped');
      await this.avPlayer.prepare();
      await this.avPlayer.play();
      this.isPlayLink!.set(true);
    } else {
      Logger.info(TAG, 'localPlayOrPause start play');
      await this.avPlayer.play();
      this.isPlayLink!.set(true);
      Logger.info(TAG, 'localPlayOrPause start play done');
    }
    Logger.info(TAG, 'localPlayOrPause isPlay: ' + this.isPlayLink!.get());
  }

  /**
   * Set AVMetadata.
   */
  async setAVMetadataToController(episode: Episode, duration: number): Promise<void> {
    Logger.info(TAG, `setAVMetadataToController avMetadata: ` + JSON.stringify(episode));
    const imagePixMap = await ImageUtil.getPixmapFromMedia($r('app.media.episode_cover'));
    await this.session?.setAVMetadata({
      'assetId': episode.index.toString(),
      'title': episode.title,
      'artist': 'Kotlin 炉边漫谈',
      'mediaImage': imagePixMap,
      'duration': duration
    });
  }

  /**
   * In-app circulation mode.
   */
  async loopMode(): Promise<void> {
    let currentMode = this.currentLoopModeLink!.get();
    Logger.info(TAG, 'do setLooMode old: ' + currentMode);
    this.currentLoopModeLink!.set(currentMode === Constants.MAX_LOOP_MODE_COUNT ? 0 : ++currentMode);
    this.currentState.loopMode = this.currentLoopModeLink!.get();
    Logger.info(TAG, 'do setLooMode new: ' + this.currentState.loopMode);
    this.session?.setAVPlaybackState(this.currentState);
  }

  /**
   * Updates the playback status.
   */
  private clickPlayedAlready = false

  async play(): Promise<void> {
    Logger.info(TAG, `Start do play task`);
    this.clickPlayedAlready = true
    if (this.avPlayer?.state === AVPlayerState.PLAYING || this.avPlayer?.state === AVPlayerState.IDLE) {
      return
    }
    await this.localPlayOrPause();
    Logger.info(TAG, `currentState isPlay ` + this.isPlayLink!.get());
    this.currentState = {
      state: this.isPlayLink!.get() ? avSession.PlaybackState.PLAYBACK_STATE_PLAY :
      avSession.PlaybackState.PLAYBACK_STATE_PAUSE,
      position: {
        elapsedTime: this.currentTime,
        updateTime: new Date().getTime()
      },
    };
    Logger.info(TAG, `currentState` + JSON.stringify(this.currentState));
    await this.session?.setAVPlaybackState(this.currentState);
  }

  /**
   * In-app pause button.
   */
  async pause(): Promise<void> {
    Logger.info(TAG, `on pause , do pause task`);
    if (this.avPlayer?.state !== AVPlayerState.PLAYING) {
      return
    }
    await this.localPlayOrPause();
    Logger.info(TAG, `currentState isPlay ` + this.isPlayLink!.get());
    this.currentState = {
      state: this.isPlayLink!.get() ? avSession.PlaybackState.PLAYBACK_STATE_PLAY :
      avSession.PlaybackState.PLAYBACK_STATE_PAUSE,
      position: {
        elapsedTime: this.currentTime,
        updateTime: new Date().getTime()
      },
    };
    Logger.info(TAG, `currentState` + JSON.stringify(this.currentState));
    await this.session?.setAVPlaybackState(this.currentState);
  }


  /**
   * In-app progress bar.
   */
  async seek(currentPosition: number): Promise<void> {
    Logger.info(TAG, `on seek , do seek task to: ` + currentPosition);
    this.avPlayer?.seek(currentPosition);
    this.currentState.position = {
      elapsedTime: Math.floor(currentPosition),
      updateTime: new Date().getTime()
    };
    this.session?.setAVPlaybackState(this.currentState);
  }

  /**
   * Processes the previous/next command.
   */
  async handleNewItem(episode: Episode, playImmediately: boolean): Promise<void> {
    Logger.info(TAG, ' handleNewItem itemId: ' + episode.index);
    this.currentTime = 0
    await this.mediaPlayerUtil.loadFromUrl(episode.audioUrl);
    // The avplayer is ready to play.
    this.mediaPlayerUtil.on('prepared', () => {
      Logger.info(TAG, 'AVPlayer state prepared, start play:' + playImmediately);
      this.duration = this.mediaPlayerUtil.getDuration()
      if (playImmediately || this.clickPlayedAlready) {
        this.setAVMetadataToController(episode, this.duration);
        this.handleNewItemAVPlayback();
      }
    });
    this.mediaPlayerUtil?.on(AVPlayerState.COMPLETED,() => {
      getMediaCommandHandler().onNext()
    })
  }

  async handleNewItemAVPlayback(): Promise<void> {
    await this.localPlayOrPause();
    this.currentState = {
      state: this.isPlayLink!.get() ? avSession.PlaybackState.PLAYBACK_STATE_PLAY :
      avSession.PlaybackState.PLAYBACK_STATE_PAUSE,
      position: {
        elapsedTime: this.currentTime,
        updateTime: new Date().getTime()
      },
    };
    Logger.info(`currentState` + JSON.stringify(this.currentState));
    await this.session?.setAVPlaybackState(this.currentState);
  }

  /**
   * Registers the avplayer event listener.
   */
  async RegisterAVPlayerListener(): Promise<void> {
    // Registers focus interrupt listening.
    Logger.info(TAG, ` RegisterAVPlayerListener`);
    this.avPlayer?.on('audioInterrupt', (info: audio.InterruptEvent) => {
      Logger.info(TAG, 'audioInterrupt success,and InterruptEvent info is:' + info);
      if (this.avPlayer?.state === AVPlayerState.PLAYING) {
        Logger.info(TAG, 'audio interrupt, start pause');
        this.avPlayer?.pause();
        this.currentState.state = avSession.PlaybackState.PLAYBACK_STATE_PAUSE;
        this.session?.setAVPlaybackState(this.currentState);
      }
    });
    // Registers the playback time change callback function.
    this.avPlayer?.on('timeUpdate', (time: number) => {
      // The function of obtaining the current location globally is added.
      this.currentTime = time;
      this.updatePlaybackState()
      // Logger.info(TAG, 'time update progress:' + time);
      // this.seekPositionLink!.set(time / this.currentAVMetadataLink!.get().duration! * 100);
    });
    this.mediaPlayerUtil?.setOnStateChangeCallback((state) => {
      this.isPlayLink?.set(this.mediaPlayerUtil.isPlaying())
      this.updatePlaybackState()
    })
  }

  private updatePlaybackState() {
    getOhosStateListener()
      .updatePlaybackState(this.isPlayLink?.get() ?? false, this.currentTime, this.duration)
  }

  /**
   * UnRegister the event listener for the avplayer.
   */
  async unRegisterAVPlayerListener(): Promise<void> {
    Logger.info(TAG, ` unRegisterAVPlayerListener`);
    // UnRegister the listening of focus interrupt.
    this.avPlayer?.off('audioInterrupt');
    // UnRegister the playback time change callback function.
    this.avPlayer?.off('timeUpdate');
    this.avPlayer?.off('stateChange');
    this.avPlayer?.off('seekDone');
    this.avPlayer?.off('error');
  }
}