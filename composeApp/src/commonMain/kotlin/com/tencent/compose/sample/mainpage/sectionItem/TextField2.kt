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

package com.tencent.compose.sample.mainpage.sectionItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
internal fun TextField2() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("用户名")
        TextField(
            value = username,
            placeholder = { Text("请输入用户名") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                username = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text("密码")
        TextField(
            value = password,
            placeholder = { Text("请输入密码") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text("年龄")
        TextField(
            value = age,
            placeholder = { Text("请输入年龄") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                age = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text("邮箱")
        TextField(
            value = email,
            placeholder = { Text("请输入邮箱") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        val focusManager = LocalFocusManager.current
        Button(onClick = {
            focusManager.clearFocus()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("注册")
        }

        DisposableEffect(Unit) {
            onDispose {
                focusManager.clearFocus()
            }
        }
    }
}
