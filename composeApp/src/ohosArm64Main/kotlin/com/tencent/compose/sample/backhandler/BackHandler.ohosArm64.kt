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

package com.tencent.compose.sample.backhandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalArkUIViewController

@Composable
internal actual fun BackHandler(enable: Boolean, onBack: () -> Unit) {
    val onBackPressedDispatcher = LocalArkUIViewController.current.onBackPressedDispatcher
    val enableState by rememberUpdatedState(enable)
    DisposableEffect(Unit) {
        val cancel = onBackPressedDispatcher.addOnBackPressedCallback {
            if (enableState) {
                onBack()
                true
            } else {
                false
            }
        }
        onDispose(cancel)
    }
}