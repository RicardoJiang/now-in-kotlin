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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.napi.js
import androidx.compose.ui.unit.dp


@Composable
internal fun InteropRenderOrder() {
    var interLayer by remember { mutableStateOf(false) }
    Column {
        Box {
            ArkUIView(
                "layer", Modifier.width(400.dp).height(300.dp),
                js {
                    "text"("1")
                    "backgroundColor"("#FF0000FF")
                },
            )

            ArkUIView(
                "layer", Modifier.width(350.dp).height(250.dp),
                js {
                    "text"("2")
                    "backgroundColor"("#FF00FF00")
                },
            )

            if (interLayer) {
                ArkUIView(
                    "layer", Modifier.width(300.dp).height(200.dp),
                    js {
                        "text"("3")
                        "backgroundColor"("#FFFF0000")
                    },
                )
            }

            ArkUIView(
                "layer", Modifier.width(250.dp).height(150.dp),
                js {
                    "text"("4")
                    "backgroundColor"("#FF00FFFF")
                },
            )

            ArkUIView(
                "layer", Modifier.width(200.dp).height(100.dp),
                js {
                    "text"("5")
                    "backgroundColor"("#FFFFFF00")
                },
            )
        }

        Button({ interLayer = !interLayer }) {
            Text("Show / Hide")
        }
    }
}