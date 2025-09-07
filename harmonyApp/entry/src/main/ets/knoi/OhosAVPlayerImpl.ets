import { ProviderFeature } from "../controller/ProviderManager";
import { OhosAVPlayer } from "./consumer";

export class OhosAVPlayerImpl implements OhosAVPlayer {
  public static provider?: ProviderFeature = undefined

  play(): void {
    OhosAVPlayerImpl.provider?.play()
  }

  pause(): void {
    OhosAVPlayerImpl.provider?.pause()
  }

  seekTo(positionMs: number): void {
    OhosAVPlayerImpl.provider?.seek(positionMs)
  }

  prepareAndPlay(index: number, title: string, audioUrl: string, imageUrl: string, playImmediately: boolean): void {
    OhosAVPlayerImpl.provider?.handleNewItem({
      index: index,
      title: title,
      audioUrl: audioUrl,
      imageUrl: imageUrl
    }, playImmediately)
  }

  currentTime(): number {
    return (OhosAVPlayerImpl.provider?.currentTime ?? 0)
  }

  duration(): number {
    return (OhosAVPlayerImpl.provider?.duration ?? 0)
  }
}