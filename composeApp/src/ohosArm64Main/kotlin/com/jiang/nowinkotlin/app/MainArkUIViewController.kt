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

package com.jiang.nowinkotlin.app

import androidx.compose.ui.window.ComposeArkUIViewController
import com.jiang.nowinkotlin.app.MainPage
import com.jiang.nowinkotlin.core.util.NativeResourceManager
import com.jiang.nowinkotlin.core.util.nativeResourceManager
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.initMainHandler
import platform.ohos.napi_env
import platform.ohos.napi_value
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
@CName("MainArkUIViewController")
fun MainArkUIViewController(env: napi_env): napi_value {
    initMainHandler(env)
    return ComposeArkUIViewController(env) { MainPage() }
}

@OptIn(ExperimentalForeignApi::class)
fun initResourceManager(resourceManager: NativeResourceManager) {
    nativeResourceManager = resourceManager
}

