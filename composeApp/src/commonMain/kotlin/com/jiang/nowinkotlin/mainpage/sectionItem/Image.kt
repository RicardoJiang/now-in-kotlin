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

package com.jiang.nowinkotlin.mainpage.sectionItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.jiang.nowinkotlin.rememberLocalImage
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.image_cat
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SimpleImage() {
//    Column {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()) {

        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Red))

        val contentScaleOptions = listOf(
            "ContentScale.Crop" to ContentScale.Crop,  // 1
            "ContentScale.Fit" to ContentScale.Fit,  // 2
            "ContentScale.FillBounds" to ContentScale.FillBounds,  // 3
            "ContentScale.FillWidth" to ContentScale.FillWidth,  // 4
            "ContentScale.FillHeight" to ContentScale.FillHeight,  // 5
            "ContentScale.Inside" to ContentScale.Inside,  // 6
            "ContentScale.None" to ContentScale.None  // 7
        )

        val alignmentsList = listOf(
            listOf(Alignment.TopCenter, Alignment.TopEnd, Alignment.BottomStart),  // 1
            listOf(Alignment.CenterStart, Alignment.Center, Alignment.BottomEnd),  // 2
            listOf(Alignment.TopStart, Alignment.BottomCenter, Alignment.BottomStart),  // 3
            listOf(Alignment.TopStart, Alignment.CenterStart, Alignment.BottomStart),  // 4
            listOf(Alignment.CenterStart, Alignment.Center, Alignment.BottomEnd),  // 5
            listOf(Alignment.TopStart, Alignment.BottomCenter, Alignment.BottomStart),  // 6
            listOf(Alignment.TopCenter, Alignment.TopEnd, Alignment.BottomStart),  // 7
        )

        val clips = listOf(
            CircleShape
        )

        for ((index, pair) in contentScaleOptions.withIndex()) {
            val (title, scale) = pair
            val clipShape = clips.getOrNull(index)
            Text(title)
            Row(modifier = Modifier.fillMaxWidth()) {
                val customAlignments = alignmentsList[index]
                for (alignment in customAlignments) {
                    Image(
                        bitmap = rememberLocalImage(Res.drawable.image_cat),
                        contentDescription = null,
                        alignment = alignment,
                        contentScale = scale,
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Yellow)
                            .padding(1.dp)
                            .graphicsLayer(
                                shape = clipShape ?: RectangleShape,
                                clip = clipShape != null
                            )
                    )
                }
            }
        }
    }
}
