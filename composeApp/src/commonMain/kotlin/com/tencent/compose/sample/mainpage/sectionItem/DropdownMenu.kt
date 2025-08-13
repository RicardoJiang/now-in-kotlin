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

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
internal fun DropdownMenu(
    name: String = "点我的任意位置",
    dropdownMenuItems: List<DropdownItem> = List(10) { DropdownItem("DropdownItem$it") },
    modifier: Modifier = Modifier,
    onItemClick: (DropdownItem) -> Unit = {}
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(0.dp)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        elevation = 40.dp,
        modifier = modifier
            .onSizeChanged {
                itemHeight = with(density) {
                    it.height.toDp()
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                        },
                        onPress = {
                            isContextMenuVisible = true
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        },
                        onTap = {
                            isContextMenuVisible = true
                        },
                        onDoubleTap = {
                            isContextMenuVisible = true
                        }
                    )
                }
                .padding(16.dp)
        ) {
            Text(text = name)
        }
        DropdownMenu(
            expanded = isContextMenuVisible,
            offset = DpOffset(x = 0.dp, y = 0.dp),
            onDismissRequest = {
                isContextMenuVisible = false
            }) {
            dropdownMenuItems.forEach { item ->
                run {
                    DropdownMenuItem(
                        onClick = {
                            onItemClick(item)
                            isContextMenuVisible = false
                        }
                    ) {
                        Text(text = item.text)
                    }
                }
            }
        }
    }
}

internal data class DropdownItem(
    val text: String
)