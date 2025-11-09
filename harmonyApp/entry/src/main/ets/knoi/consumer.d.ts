/***
 *    !!!  GEN CODE DO NOT EDIT  !!!
 ***/
export interface OhosServices {

  parseJson(json: string): object;

}

export interface SetScreenOrientation {

  setOrientation(isLandscape: boolean): void;

}

export interface OhosAVPlayer {

  play(): void;

  pause(): void;

  seekTo(positionMs: number): void;

  prepareAndPlay(index: number, title: string, audioUrl: string, imageUrl: string, playImmediately: boolean): void;

  currentTime(): number;

  duration(): number;

}

export interface OhosVideoAVPlayer {

  setUpVideo(surfaceId: string, url: string): void;

  isPlaying(): boolean;

  isBuffering(): boolean;

  currentTime(): number;

  duration(): number;

  pause(): void;

  play(): void;

  seekTo(position: number): void;

  release(): void;

}

export interface OhosVideoAVPlayerFactory {

  createOhosVideoAVPlayer(): object;

}
