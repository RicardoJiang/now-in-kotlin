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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.napi.js
import androidx.compose.ui.unit.dp


@Composable
internal fun InteropListSimple() {
    Box {
        Column(Modifier.background(Color.LightGray).fillMaxSize()) {
            LazyColumn(Modifier.background(Color.Red).fillMaxSize()) {
                items(80) { index ->
                    Column {
                        ArkUIView(
                            name = "label",
                            modifier = Modifier.width(250.dp).height(100.dp),
                            parameter = js {
                                "text"("ArkUI Button $index")
                                "backgroundColor"("#FF0000FF")
                            },
                        )

                        Button({
                            println("Compose Button $index clicked")
                        }, modifier = Modifier.height(30.dp).fillMaxWidth()) {
                            Text("Compose Button $index")
                        }
                    }
                }
            }
        }
    }
}