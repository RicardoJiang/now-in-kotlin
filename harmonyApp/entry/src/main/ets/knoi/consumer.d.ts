/***
 *    !!!  GEN CODE DO NOT EDIT  !!!
 ***/
export interface OhosServices {

  parseJson(json: string): object;

}

export interface OhosAVPlayer {

  play(): void;

  pause(): void;

  seekTo(positionMs: number): void;

  prepareAndPlay(index: number, title: string, audioUrl: string, imageUrl: string, playImmediately: boolean): void;

  currentTime(): number;

  duration(): number;

}

