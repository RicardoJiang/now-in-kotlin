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

package com.tencent.compose.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.arkui.ArkUIView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.AdaptiveParams
import androidx.compose.ui.interop.InteropContainer
import androidx.compose.ui.napi.JsObject
import androidx.compose.ui.napi.js

private val NoOp: Any.() -> Unit = {}

@Composable
internal fun ArkUIView(
    name: String,
    modifier: Modifier,
    parameter: JsObject = js(),
    update: (JsObject) -> Unit = NoOp,
    background: Color = Color.Unspecified,
    updater: (ArkUIView) -> Unit = NoOp,
    onCreate: (ArkUIView) -> Unit = NoOp,
    onRelease: (ArkUIView) -> Unit = NoOp,
    interactive: Boolean = true,
    adaptiveParams: AdaptiveParams? = null,
    tag: String? = null,
    container: InteropContainer = InteropContainer.BACK
) = androidx.compose.ui.interop.ArkUIView(
    name = name,
    modifier = modifier,
    parameter = parameter,
    update = update,
    background = background,
    updater = updater,
    onCreate = onCreate,
    onRelease = onRelease,
    interactive = interactive,
    adaptiveParams = adaptiveParams,
    tag = tag,
    container = container,
)