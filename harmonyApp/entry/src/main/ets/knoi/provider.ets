/***
*    !!!  GEN CODE DO NOT EDIT  !!!
***/
import { getService } from "@kuiklybase/knoi"

export interface OhosStateListener {
            
   updatePlaybackState(isPlaying: boolean, positionMs: number, duration: number): void;

}

export function getOhosStateListener(): OhosStateListener {
   return getService<OhosStateListener>("OhosStateListener")
}

export interface MediaCommandHandler {
            
   onNext(): void;

   onPrevious(): void;

}

export function getMediaCommandHandler(): MediaCommandHandler {
   return getService<MediaCommandHandler>("MediaCommandHandler")
}

