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

import { media } from '@kit.MediaKit';
import { common } from '@kit.AbilityKit';
import Constants, { AVPlayerState } from './Constants';
import GlobalContext from './GlobalContextUtils';
import Logger from './Logger';

const TAG: string = 'MediaPlayer';

/**
 * Create the AVPlayer playback class.
 */
export default class MediaPlayer {
  private context: common.UIAbilityContext | undefined =
    GlobalContext.getContext().getObject(Constants.CONTEXT) as common.UIAbilityContext;
  public avPlayer?: media.AVPlayer;
  public state: string = '';
  public surfaceId: string = '';
  private callbackMap: Record<string, object | null> = {};
  private onStateChange: (state: string) => void = (state: string) => {
  }

  public async init(): Promise<media.AVPlayer> {
    this.avPlayer = await media.createAVPlayer();
    this.setAVPlayerCallback();
    return this.avPlayer;
  }

  public getDuration(): number {
    return this.avPlayer?.duration ?? 0
  }

  /**
   * Listens to AVPlayer status callback.
   */
  setAVPlayerCallback(): void {
    this.avPlayer?.on('seekDone', (seekDoneTime) => {
      Logger.info(TAG, `AVPlayer seek succeeded, seek time is ${seekDoneTime}`);
    });
    this.avPlayer?.on('error', (err) => {
      Logger.error(TAG, `Invoke avPlayer failed, code is ${err.code}, message is ${err.message}`);
      this.avPlayer?.reset();
    });
    this.avPlayer?.on('stateChange', async (state, _) => {
      if (this.avPlayer?.url != this.curUrl) {
        Logger.info(TAG, 'Not curUrl stateChange ' + state)
        return
      }
      this.state = state;
      Logger.info(TAG, 'AVPlayer state: ' + JSON.stringify(state) + "," + this.avPlayer?.url?.split("?")[1]);
      this.onStateChange(state)
      switch (state) {
        case AVPlayerState.IDLE:
          Logger.info(TAG, 'AVPlayer state idle called.');
          break;
        case AVPlayerState.INITIALIZED:
          Logger.info(TAG, 'AVPlayer state initialized called, callbackMap = ' + JSON.stringify(this.callbackMap));
          if (this.surfaceId) {
            this.avPlayer!.surfaceId = this.surfaceId;
          }
          await this.avPlayer?.prepare();
          break;
        case AVPlayerState.PREPARED:
          if (this.callbackMap[AVPlayerState.PREPARED]) {
            Logger.info(TAG, 'AVPlayer state prepared called callbackMap');
            let callbackPre: Function = this.callbackMap[AVPlayerState.PREPARED] as Function;
            callbackPre();
            this.callbackMap[AVPlayerState.PREPARED] = null;
          }
          break;
        case AVPlayerState.PLAYING:
          Logger.info(TAG, 'AVPlayer state playing called.');
          break;
        case AVPlayerState.PAUSED:
          Logger.info(TAG, 'AVPlayer state paused called.');
          break;
        case AVPlayerState.COMPLETED:
          Logger.info(TAG, 'AVPlayer state completed called.');
          let callback: Function = this.callbackMap[AVPlayerState.COMPLETED] as Function;
          callback();
          this.callbackMap[AVPlayerState.COMPLETED] = null;
          break;
        case AVPlayerState.STOPPED:
          Logger.info(TAG, 'AVPlayer state stopped called.');
          break;
        case AVPlayerState.RELEASED:
          Logger.info(TAG, 'AVPlayer state released called.');
          break;
        default:
          Logger.error(TAG, 'AVPlayer state unknown called.');
          break;
      }
    });
  }

  public on(event: string, callback: Function): void {
    this.callbackMap[event] = callback;
  }

  public setOnStateChangeCallback(callback: (state: string) => void) {
    this.onStateChange = callback
  }

  public isPlaying(): boolean {
    return this.avPlayer?.state === AVPlayerState.PLAYING
  }

  /**
   * Play a local resource.
   */
  private curUrl: string = ''

  async loadFromUrl(url: string): Promise<void> {
    this.curUrl = url
    this.callbackMap = {};
    Logger.info(TAG, 'loadFromUrl: ' + url + ' state: ' + this.state);
    if (!this.avPlayer) {
      Logger.error(TAG, 'no avplayer');
      return;
    }
    if (this.state !== AVPlayerState.IDLE) {
      await this.avPlayer.reset()
      Logger.info(TAG, 'avPlayer.reset called');
    }
    try {
      if (url != this.curUrl) {
        Logger.info(TAG, 'not equal:'+(url?.split("?")[1]) + ","+ (this.curUrl?.split("?")[1]))
        return
      }
      Logger.info(TAG, "set url when state:" + this.avPlayer.state + ',' + url?.split("?")[1])
      this.avPlayer.url = url
    } catch (error) {
      Logger.error(TAG, 'resourceManager getRawFd error: ' + JSON.stringify(error));
    }
  }
}