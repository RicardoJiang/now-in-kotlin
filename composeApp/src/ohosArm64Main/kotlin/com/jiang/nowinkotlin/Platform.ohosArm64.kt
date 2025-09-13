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

package com.jiang.nowinkotlin

import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.ohos.OH_GetOSFullName
import platform.resource.OH_ResourceManager_CloseRawFile
import platform.resource.OH_ResourceManager_GetRawFileSize
import platform.resource.OH_ResourceManager_OpenRawFile
import platform.resource.OH_ResourceManager_ReadRawFile

internal class OHOSPlatform : Platform {
    @OptIn(ExperimentalForeignApi::class)
    override val name: String = OH_GetOSFullName()?.toKString().orEmpty()
}

internal actual fun getPlatform(): Platform = OHOSPlatform()

@OptIn(ExperimentalResourceApi::class, ExperimentalForeignApi::class)
internal actual suspend fun readJson(path: String): String {
    return try {
        val rawFile = OH_ResourceManager_OpenRawFile(nativeResourceManager, path)
        val size = OH_ResourceManager_GetRawFileSize(rawFile)

        val buffer = ByteArray(size.toInt())
        buffer.usePinned { pinnedBuffer ->
            OH_ResourceManager_ReadRawFile(rawFile, pinnedBuffer.addressOf(0), size.toULong())
        }
        OH_ResourceManager_CloseRawFile(rawFile)
        buffer.decodeToString()
    } catch (e: Exception) {
        e.printStackTrace()
        "{}"
    }
}

actual abstract class PlatformContext private constructor() {
    companion object {
        val INSTANCE = object : PlatformContext() {}
    }
}

actual val LocalPlatformContext = staticCompositionLocalOf { PlatformContext.INSTANCE }