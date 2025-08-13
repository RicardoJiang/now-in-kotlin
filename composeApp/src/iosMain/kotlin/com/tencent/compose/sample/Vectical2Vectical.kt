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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView2
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi


@OptIn(ExperimentalForeignApi::class)
@Composable
internal fun Vertical2Vertical() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        val scrollState = rememberScrollState()
        Column {
            Text(
                "Compose 不可滚动区域顶部",
                Modifier.fillMaxWidth().height(40.dp).background(Color.Yellow)
            )
            Column(
                Modifier.fillMaxWidth().background(Color.LightGray).height(300.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Compose 可滚动区域顶部",
                    Modifier.fillMaxWidth().height(80.dp).background(Color.Cyan)
                )
                UIKitView2(
                    factory = {
                        allocObject("ComposeSample.ImagesScrollView")
                    },
                    modifier = Modifier.width(260.dp).height(440.dp)
                )
                Text(
                    "Compose 可滚动区域底部",
                    Modifier.fillMaxWidth().height(80.dp).background(Color.Cyan)
                )
            }
            Text(
                "Compose 不可滚动区域底部",
                color = Color.White,
                modifier = Modifier.fillMaxWidth().height(40.dp).background(Color.Blue)
            )
        }
    }
}