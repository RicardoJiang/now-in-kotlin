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

export default class Constants {
  /**
   * Percent full.
   */
  static readonly PERCENT_FULL: string = '100%';

  /**
   * Music duration.
   */
  static readonly MUSIC_DURATION: number = 219000;

  /**
   * Context.
   */
  static readonly CONTEXT: string = 'context';

  /**
   * Invalid intent name error code.
   */
  static readonly ERROR_INVALID_INTENT_NAME = -105;

  /**
   * Intent name play music.
   */
  static readonly INTENT_NAME_PLAY_MUSIC = 'PlayMusic';

  /**
   * Intent name play music list.
   */
  static readonly INTENT_NAME_PLAY_MUSIC_LIST = 'PlayMusicList';

  /**
   * Entity id first play list.
   */
  static readonly ENTITY_ID_FIRST_PLAY_LIST = 'FirstPlayList';

  /**
   * Entity id second play list.
   */
  static readonly ENTITY_ID_SECOND_PLAY_LIST = 'SecondPlayList';

  /**
   * Entity id third play list.
   */
  static readonly ENTITY_ID_THIRD_PLAY_LIST = 'ThirdPlayList';

  /**
   * One hundred.
   */
  static readonly ONE_HUNDRED: number = 100;

  /**
   * Slider change duration.
   */
  static readonly SLIDER_CHANGE_DURATION: number = 200;

  /**
   * Lrc name.
   */
  static readonly LRC_NAME: string = 'Dream It Possible.lrc';

  /**
   * Bundle name.
   */
  static readonly BUNDLE_NAME: string = 'com.samples.mediaprovider';

  /**
   * Ability name.
   */
  static readonly ABILITY_NAME: string = 'EntryAbility';

  /**
   * Music file name.
   */
  static readonly MUSIC_FILE_NAME: string = 'test_44100_2.wav';

  /**
   * Image pixel map width.
   */
  static readonly IMAGE_PIXEL_MAP_WIDTH: number = 900;

  /**
   * Max loop mode count.
   */
  static readonly MAX_LOOP_MODE_COUNT: number = 3;
}

export enum AVPlayerState {
  IDLE = 'idle',
  INITIALIZED = 'initialized',
  PREPARED = 'prepared',
  PLAYING = 'playing',
  PAUSED = 'paused',
  COMPLETED = 'completed',
  STOPPED = 'stopped',
  RELEASED = 'released'
}