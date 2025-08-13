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

@file:OptIn(ExperimentalForeignApi::class, ExperimentalResourceApi::class)

package com.tencent.compose.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.skia.Image
import platform.resource.OH_ResourceManager_CloseRawFile
import platform.resource.OH_ResourceManager_GetRawFileSize
import platform.resource.OH_ResourceManager_OpenRawFile
import platform.resource.OH_ResourceManager_ReadRawFile

typealias NativeResourceManager = CPointer<cnames.structs.NativeResourceManager>?

var nativeResourceManager: NativeResourceManager = null

private val emptyImageBitmap: ImageBitmap by lazy { ImageBitmap(1, 1) }

@Composable
internal actual fun rememberLocalImage(id: DrawableResource): ImageBitmap {
    var imageBitmap: ImageBitmap by remember { mutableStateOf(emptyImageBitmap) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val rawFile = OH_ResourceManager_OpenRawFile(nativeResourceManager, id.resourceItemPath())
            val size = OH_ResourceManager_GetRawFileSize(rawFile)

            val buffer = ByteArray(size.toInt())
            buffer.usePinned { pinnedBuffer ->
                OH_ResourceManager_ReadRawFile(rawFile, pinnedBuffer.addressOf(0), size.toULong())
            }
            OH_ResourceManager_CloseRawFile(rawFile)

            imageBitmap = Image.makeFromEncoded(buffer).toComposeImageBitmap()
        }
    }
    return imageBitmap
}