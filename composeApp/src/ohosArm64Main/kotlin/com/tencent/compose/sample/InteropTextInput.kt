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

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.napi.asString
import androidx.compose.ui.napi.js
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
internal fun InteropTextInput() {
    Column(Modifier.fillMaxWidth().fillMaxHeight().padding(30.dp)) {

        var inputText by remember { mutableStateOf("混排状态变量双向通信 输入文本...") }

        val state = remember(inputText) {
            js { "text"(inputText) }
        }

        ArkUIView(
            name = "textInput",
            modifier = Modifier.width(450.dp).wrapContentHeight(),
            parameter = state,
            update = {
                inputText = it["text"].asString().toString()
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Compose组件更新:", color = Color.Gray)

        Text(
            text = inputText,
            modifier = Modifier.fillMaxWidth()
                .border(width = 1.dp, color = Color.Gray)
                .padding(10.dp)
        )

        Button(onClick = { inputText += "[文本]" }) {
            Text("Append Text")
        }
    }
}