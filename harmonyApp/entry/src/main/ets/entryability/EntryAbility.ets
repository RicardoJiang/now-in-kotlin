/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { AbilityConstant, ConfigurationConstant, UIAbility, Want } from '@kit.AbilityKit';
import { hilog } from '@kit.PerformanceAnalysisKit';
import { window } from '@kit.ArkUI';
import { initResourceManager } from 'libentry.so';
import { registerServiceProvider, setup } from '@kuiklybase/knoi';
import { OhosServicesImpl } from '../knoi/OhosServicesImpl';
import { OhosAVPlayerImpl, OhosVideoAVPlayerFactoryImpl } from '../knoi/OhosAVPlayerImpl';
import GlobalContext from '../controller/GlobalContextUtils';
import Constants from '../controller/Constants';
import { WindowHelper } from '../pages/WindowHelper';
import { ProviderFeature } from '../controller/ProviderManager';
import { SetScreenOrientationImpl } from '../knoi/SetScreenOrientationImpl';
import { OhosVideoAVPlayerImpl } from '../knoi/OhosVideoAVPlayerImpl';

const DOMAIN = 0x0000;

setup("libkn.so", true)

export default class EntryAbility extends UIAbility {
  onCreate(want: Want, launchParam: AbilityConstant.LaunchParam): void {
    AppStorage.setOrCreate('context', this.context);
    GlobalContext.getContext().setObject(Constants.CONTEXT, this.context);
    this.context.getApplicationContext().setColorMode(ConfigurationConstant.ColorMode.COLOR_MODE_NOT_SET);
    initResourceManager(this.context.resourceManager)
    registerServiceProvider("OhosServices", false, OhosServicesImpl)
    registerServiceProvider("OhosAVPlayer", true, OhosAVPlayerImpl)
    registerServiceProvider("SetScreenOrientation", true, SetScreenOrientationImpl)
    registerServiceProvider("OhosVideoAVPlayer", false, OhosVideoAVPlayerImpl)
    registerServiceProvider("OhosVideoAVPlayerFactory", false, OhosVideoAVPlayerFactoryImpl)
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onCreate');
  }

  onDestroy(): void {
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onDestroy');
  }

  async onWindowStageCreate(windowStage: window.WindowStage) {
    // Main window is created, set main page for this ability
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onWindowStageCreate');
    WindowHelper.windowClass = await windowStage.getMainWindow();
    OhosAVPlayerImpl.provider = await ProviderFeature.getInstance();
    console.log('ProviderFeature init success')

    windowStage.loadContent('pages/Index', (err) => {
      if (err.code) {
        hilog.error(DOMAIN, 'testTag', 'Failed to load the content. Cause: %{public}s', JSON.stringify(err));
        return;
      }
      hilog.info(DOMAIN, 'testTag', 'Succeeded in loading the content.');
    });
  }

  onWindowStageDestroy(): void {
    // Main window is destroyed, release UI related resources
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onWindowStageDestroy');
  }

  onForeground(): void {
    // Ability has brought to foreground
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onForeground');
  }

  onBackground(): void {
    // Ability has back to background
    hilog.info(DOMAIN, 'testTag', '%{public}s', 'Ability onBackground');
  }
}