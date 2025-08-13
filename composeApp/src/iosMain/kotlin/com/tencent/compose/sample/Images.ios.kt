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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.UikitImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.Foundation.NSBundle
import platform.UIKit.UIImage

private val emptyImageBitmap: ImageBitmap by lazy { ImageBitmap(1, 1) }

private val imageFolderPath:String by lazy { "${NSBundle.mainBundle().bundlePath}/compose-resources/" }

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun rememberLocalImage(id: DrawableResource): ImageBitmap {
    var imageBitmap: ImageBitmap by remember { mutableStateOf(emptyImageBitmap) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val imagePath = "$imageFolderPath/${id.resourceItemPath()}"
            val image = UIImage.imageNamed(imagePath) ?: return@withContext
            imageBitmap = UikitImageBitmap(image)
        }
    }
    return imageBitmap
}