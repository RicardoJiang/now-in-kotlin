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

package com.jiang.nowinkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.jiang.nowinkotlin.screen.MainPage
import com.jiang.nowinkotlin.video.setActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        NowInKotlinContextProvider.initContext(this)
        setActivity(this)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0xFF0B1220.toInt()),
            navigationBarStyle = SystemBarStyle.dark(0xFF0B1220.toInt()),
        )
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()) {
                MainPage()
            }
        }
    }
}