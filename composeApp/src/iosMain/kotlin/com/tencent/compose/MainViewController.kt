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

@file:Suppress("unused")

package com.tencent.compose

import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.uikit.RenderBackend
import androidx.compose.ui.window.ComposeUIViewController
import com.tencent.compose.sample.mainpage.MainPage
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("MainViewController")
fun MainViewController() = ComposeUIViewController(
    configure = { onFocusBehavior = OnFocusBehavior.DoNothing }
) {
    MainPage(false)
}

@OptIn(ExperimentalObjCName::class)
@ObjCName("SkiaRenderViewController")
fun SkiaRenderMainViewController() = ComposeUIViewController(
    configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
        renderBackend = RenderBackend.Skia
    }
) {
    MainPage(true)
}
