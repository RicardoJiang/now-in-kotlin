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

import { Compose } from 'compose';
import { MainArkUIViewController } from 'libentry.so';
import { registerComposeInteropBuilders } from './ComposeSample';

registerComposeInteropBuilders();

@Entry
@Component
struct Index {
  private controller = MainArkUIViewController()

  onPageShow(): void {
    this.controller.onPageShow()
  }

  onPageHide(): void {
    this.controller.onPageHide()
  }

  onBackPress(): boolean | void {
    // 返回事件转发给 Compose，优先给 Compose 消费，若 Compose 不消费再给系统消费
    return this.controller.onBackPress()
  }

  build() {
    Stack() {
      Compose(
        {
          controller: this.controller,
          libraryName: "entry",
          // 若 Compose 不消费，事件会流转到这里，若这里返回 false，则交由系统处理
          onBackPressed: () => false
        }
      ).width('100%').height('100%')
    }.width('100%').height('100%')
  }
}
