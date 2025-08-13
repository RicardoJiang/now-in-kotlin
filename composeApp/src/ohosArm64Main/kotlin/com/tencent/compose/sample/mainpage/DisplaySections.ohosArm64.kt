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

package com.tencent.compose.sample.mainpage

import com.tencent.compose.sample.InteropListNested
import com.tencent.compose.sample.InteropListSimple
import com.tencent.compose.sample.InteropRenderOrder
import com.tencent.compose.sample.InteropTextInput
import com.tencent.compose.sample.data.DisplayItem
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.interop_list
import nowinkotlin.composeapp.generated.resources.interop_nested_scroll
import nowinkotlin.composeapp.generated.resources.interop_state
import nowinkotlin.composeapp.generated.resources.layers
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
internal actual fun platformSections(): List<DisplayItem> {
    return listOf(
        DisplayItem("混排层级", Res.drawable.layers) { InteropRenderOrder() },
        DisplayItem("混排滑动", Res.drawable.interop_list) { InteropListSimple() },
        DisplayItem("混排嵌滑", Res.drawable.interop_nested_scroll) { InteropListNested() },
        DisplayItem("混排状态", Res.drawable.interop_state) { InteropTextInput() }
    )
}