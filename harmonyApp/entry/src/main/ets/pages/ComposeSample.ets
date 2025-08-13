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
import { registerComposeInteropBuilder } from 'compose/src/main/ets/compose/ArkUIView';
import {
  buttonBuilder,
  textBuilder,
  labelBuilder,
  textInputBuilder,
  buttonWrapContentBuilder,
  layerBuilder,
  verticalListBuilder,
  horizontalListBuilder,
} from './ComposeInterops';

export function registerComposeInteropBuilders() {
  registerComposeInteropBuilder('button', buttonBuilder)
  registerComposeInteropBuilder('label', labelBuilder)
  registerComposeInteropBuilder('buttonWrapContent', buttonWrapContentBuilder)
  registerComposeInteropBuilder('textInput', textInputBuilder)
  registerComposeInteropBuilder('layer', layerBuilder)
  registerComposeInteropBuilder('text', textBuilder)
  registerComposeInteropBuilder('verticalList', verticalListBuilder)
  registerComposeInteropBuilder('horizontalList', horizontalListBuilder)
}