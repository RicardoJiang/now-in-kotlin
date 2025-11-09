import { OhosVideoAVPlayer } from './consumer';
import { AVPlayerManager } from '../video/AVPlayerManager';

export class OhosVideoAVPlayerImpl implements OhosVideoAVPlayer {
  private avPlayerManager: AVPlayerManager;

  constructor() {
    this.avPlayerManager = new AVPlayerManager();
  }

  setUpVideo(surfaceId: string, url: string): void {
    this.avPlayerManager.setupVideo(surfaceId, url);
  }

  isPlaying(): boolean {
    return this.avPlayerManager.isPlaying();
  }

  isBuffering(): boolean {
    return this.avPlayerManager.isBuffering();
  }

  currentTime(): number {
    return this.avPlayerManager.getCurrentTime();
  }

  duration(): number {
    return this.avPlayerManager.getDuration();
  }

  pause(): void {
    this.avPlayerManager.pause();
  }

  play(): void {
    this.avPlayerManager.play();
  }

  seekTo(position: number): void {
    this.avPlayerManager.seekTo(position);
  }

  release(): void {
    this.avPlayerManager.releaseAVPlayer()
  }
}