import { media } from "@kit.MediaKit";
import { common } from "@kit.AbilityKit";

export class AVPlayerManager {
  private static instance: AVPlayerManager | null = null;
  private avPlayer: media.AVPlayer | null = null;
  private tag: string = 'AVPlayerManager';
  private isBufferingState: boolean = true;

  private constructor() {}

  public static getInstance(): AVPlayerManager {
    if (AVPlayerManager.instance == null) {
      AVPlayerManager.instance = new AVPlayerManager();
    }
    return AVPlayerManager.instance;
  }

  public async createAVPlayer(): Promise<void> {
    if (this.avPlayer) {
      console.info(`${this.tag}: init avPlayer release2createNew`);
      await this.avPlayer.release();
    }
    // 创建avPlayer实例对象
    this.avPlayer = await media.createAVPlayer();
  }

  public async releaseAVPlayer(): Promise<void> {
    if (this.avPlayer == null) {
      console.info(`${this.tag}: avPlayer has not init, nothing to release`);
      return;
    }
    await this.avPlayer.release();
    console.info(`${this.tag}: avPlayer released successfully`);
    this.avPlayer = null;
  }

  public async setAVPlayerCallback(
    callback: (avPlayer: media.AVPlayer) => void,
    surfaceId?: string
  ): Promise<void> {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return;
    }

    // seek操作结果回调函数
    this.avPlayer.on('seekDone', (seekDoneTime) => {
      console.info(`${this.tag}: AVPlayer seek succeeded, seek time is ${seekDoneTime}`);
    });

    this.avPlayer.on('speedDone', (speed) => {
      console.info(`${this.tag}: AVPlayer speedDone, speed is ${speed}`);
    });

    // error回调监听函数
    this.avPlayer.on('error', (err) => {
      console.error(`${this.tag}: Invoke avPlayer failed ${JSON.stringify(err)}`);
      if (this.avPlayer == null) {
        console.error(`${this.tag}: avPlayer has not init on error`);
        return;
      }
      this.avPlayer.reset();
    });

    // 状态机变化回调函数
    this.avPlayer.on('stateChange', async (state, reason) => {
      if (this.avPlayer == null) {
        console.info(`${this.tag}: avPlayer has not init on state change`);
        return;
      }
      switch (state) {
        case 'idle':
          console.info(`${this.tag}: AVPlayer state idle called.`);
          break;
        case 'initialized':
          console.info(`${this.tag}: AVPlayer state initialized called.`);
          if (surfaceId) {
            this.avPlayer.surfaceId = surfaceId;
            console.info(`${this.tag}: this.avPlayer.surfaceId = ${this.avPlayer.surfaceId}`);
            this.avPlayer.prepare();
          }
          break;
        case 'prepared':
          console.info(`${this.tag}: AVPlayer state prepared called.`);
          this.avPlayer.on('bufferingUpdate', (infoType: media.BufferingInfoType, value: number) => {
            // console.info(`${this.tag}: bufferingUpdate called, infoType value: ${infoType}, value:${value}`);
            // 根据 infoType 判断是否正在缓冲
            if (infoType === media.BufferingInfoType.BUFFERING_START) {
              this.isBufferingState = true;
            } else if (infoType === media.BufferingInfoType.BUFFERING_END) {
              this.isBufferingState = false;
            }
          })
          this.avPlayer.play();
          callback(this.avPlayer);
          break;
        case 'playing':
          this.isBufferingState = false
          console.info(`${this.tag}: AVPlayer state playing called.`);
          break;
        case 'completed':
          console.info(`${this.tag}: AVPlayer state completed called.`);
          this.avPlayer.off('bufferingUpdate')
          break;
        case 'released':
          console.info(`${this.tag}: released called.`);
          break
        case 'stopped':
          console.info(`${this.tag}: AVPlayer state stopped called.`);
          break
        case 'error':
          console.error(`${this.tag}: AVPlayer state error called.`);
          break
        case 'paused':
          console.info(`${this.tag}: AVPlayer state paused called.`);
          break
        default:
          console.info(`${this.tag}: AVPlayer state unknown called.`);
          break;
      }
    });

    // 时间上报监听函数
    this.avPlayer.on('timeUpdate', (time: number) => {
      // 可以在这里更新当前播放时间
    });
  }

  public setUrl(url: string): void {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return;
    }
    this.isBufferingState = true
    this.avPlayer.url = url;
  }

  public play(): void {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return;
    }
    this.avPlayer.play();
  }

  public pause(): void {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return;
    }
    this.avPlayer.pause();
  }

  public seekTo(position: number): void {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return;
    }
    this.avPlayer.seek(position);
  }

  public getCurrentTime(): number {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return 0;
    }
    return this.avPlayer.currentTime;
  }

  public getDuration(): number {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return 0;
    }
    return this.avPlayer.duration;
  }

  public isPlaying(): boolean {
    if (this.avPlayer == null) {
      console.error(`${this.tag}: avPlayer has not init!`);
      return false;
    }
    return this.avPlayer.state === 'playing';
  }

  public isBuffering(): boolean {
    return this.isBufferingState;
  }

  public getAVPlayer(): media.AVPlayer | null {
    return this.avPlayer;
  }
}
