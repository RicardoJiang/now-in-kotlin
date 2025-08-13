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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp

@Composable
internal fun LinearGradientLine() {
    Box(modifier = Modifier.fillMaxSize().padding(50.dp).background(Color.Gray.copy(0.5f))) {
        Column(modifier = Modifier.wrapContentSize()) {
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 80f,
                        color = Color.Red,
                        start = Offset(0f, 0.0f),
                        end = Offset(0.0f, 0.0f)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 80f,
                        color = Color.Blue,
                        start = Offset(0f, 0.0f),
                        end = Offset(0.0f, 300.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 80f,
                        color = Color.Green,
                        start = Offset(0f, 0.0f),
                        end = Offset(300.0f, 300.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 80f,
                        color = Color.Cyan,
                        start = Offset(0f, 0.0f),
                        end = Offset(300.0f, 0.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(50.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 80f,
                        color = Color.Magenta,
                        start = Offset(300f, 0.0f),
                        end = Offset(0.0f, 300.0f)
                    )
                }
            }
        }


        Column(modifier = Modifier.wrapContentSize()) {
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 40f,
                        brush = Brush.linearGradient(
                            0.1f to Color(0xFFBF230F),
                            0.3f to Color(0xFFFFC885),
                            0.6f to Color(0xFFE8912D),
                            start = Offset(0.0f, 0.0f),
                            end = Offset(600.0f, 0.0f), tileMode = TileMode.Clamp
                        ),
                        start = Offset(0f, 0.0f),
                        end = Offset(0.0f, 0.0f)
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 40f,
                        brush = Brush.linearGradient(
                            0.1f to Color(0xFFBF230F),
                            0.3f to Color(0xFFFFC885),
                            0.6f to Color(0xFFE8912D),
                            start = Offset(0.0f, 0.0f),
                            end = Offset(600.0f, 0.0f), tileMode = TileMode.Clamp
                        ),
                        start = Offset(0f, 0.0f),
                        end = Offset(0.0f, 300.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 40f,
                        brush = Brush.linearGradient(
                            0.1f to Color(0xFFBF230F),
                            0.3f to Color(0xFFFFC885),
                            0.6f to Color(0xFFE8912D),
                            start = Offset(0.0f, 0.0f),
                            end = Offset(600.0f, 0.0f), tileMode = TileMode.Clamp
                        ),
                        start = Offset(0f, 0.0f),
                        end = Offset(300.0f, 300.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 40f,
                        brush = Brush.linearGradient(
                            0.1f to Color(0xFFBF230F),
                            0.3f to Color(0xFFFFC885),
                            0.6f to Color(0xFFE8912D),
                            start = Offset(0.0f, 0.0f),
                            end = Offset(600.0f, 0.0f), tileMode = TileMode.Clamp
                        ),
                        start = Offset(0f, 0.0f),
                        end = Offset(300.0f, 0.0f)
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                modifier = Modifier.size(50.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        strokeWidth = 40f,
                        brush = Brush.linearGradient(
                            0.1f to Color(0xFFBF230F),
                            0.3f to Color(0xFFFFC885),
                            0.6f to Color(0xFFE8912D),
                            start = Offset(0.0f, 0.0f),
                            end = Offset(600.0f, 0.0f), tileMode = TileMode.Clamp
                        ),
                        start = Offset(300f, 0.0f),
                        end = Offset(0.0f, 300.0f)
                    )
                }
            }
        }
    }
}
