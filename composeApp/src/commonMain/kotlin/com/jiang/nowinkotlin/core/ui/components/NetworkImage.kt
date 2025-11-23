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

package com.jiang.nowinkotlin.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.jiang.nowinkotlin.core.util.rememberLocalImage
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * 网络图片加载组件
 *
 * 支持加载网络图片，并在加载过程中显示 placeholder
 *
 * @param url 网络图片 URL
 * @param contentDescription 图片描述
 * @param modifier 修饰符
 * @param placeholder 占位图资源，可选
 * @param placeholderColor 占位色，当 placeholder 未提供或加载中时显示
 * @param contentScale 图片缩放方式
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: DrawableResource? = null,
    placeholderColor: Color = Color.LightGray,
    contentScale: ContentScale = ContentScale.Crop
) {
    val placeholderBitmap = placeholder?.let { rememberLocalImage(it) }

    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        loading = {
            // 加载中显示 placeholder
            if (placeholderBitmap != null) {
                Image(
                    bitmap = placeholderBitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(placeholderColor)
                )
            }
        },
        error = {
            // 加载失败显示 placeholder
            if (placeholderBitmap != null) {
                Image(
                    bitmap = placeholderBitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(placeholderColor)
                )
            }
        }
    )
}
