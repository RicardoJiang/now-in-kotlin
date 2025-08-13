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

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tencent.compose.sample.backhandler.BackHandler
import com.tencent.compose.sample.data.DisplayItem
import com.tencent.compose.sample.data.DisplaySection
import com.tencent.compose.sample.rememberLocalImage
import org.jetbrains.compose.resources.ExperimentalResourceApi

private fun appBarTitle(openedExample: DisplayItem?, skiaRender: Boolean = true): String {
    val title =
        if (skiaRender) "Tencent Video Compose - Skia" else "Tencent Video Compose - UIKit"

    val childTitle =
        if (skiaRender) "${openedExample?.title} - Skia" else "${openedExample?.title} - UIKit"

    return if (openedExample != null) childTitle else title
}

@Composable
internal fun MainPage(skiaRender: Boolean = true) {
    val displayItems by remember { mutableStateOf(displaySections()) }
    var openedExample: DisplayItem? by remember { mutableStateOf(null) }
    val listState = rememberLazyListState()
    BackHandler(openedExample != null) {
        openedExample = null
    }
    Column {
        TopAppBar(
            navigationIcon = {
                if (openedExample == null) return@TopAppBar
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { openedExample = null }
                )
            },
            title = {
                val title = appBarTitle(openedExample, skiaRender)
                Text(title)
            }
        )

        AnimatedContent(
            targetState = openedExample,
            transitionSpec = {
                val slideDirection =
                    if (targetState != null) AnimatedContentTransitionScope.SlideDirection.Start
                    else AnimatedContentTransitionScope.SlideDirection.End

                slideIntoContainer(
                    animationSpec = tween(250, easing = FastOutSlowInEasing),
                    towards = slideDirection
                ) + fadeIn(animationSpec = tween(250)) togetherWith
                        slideOutOfContainer(
                            animationSpec = tween(250, easing = FastOutSlowInEasing),
                            towards = slideDirection
                        ) + fadeOut(animationSpec = tween(250))
            }
        ) { target ->
            if (target == null) {
                LazyColumn(state = listState) {
                    items(displayItems) { displayItem ->
                        Section(displayItem) { clickItem ->
                            openedExample = clickItem
                        }
                    }

                    item {
                        Spacer(Modifier.fillMaxWidth().height(20.dp))
                    }
                }
            } else {
                Box(Modifier.fillMaxSize()) {
                    target.content()
                }
            }
        }
    }
}

@Composable
private fun Section(displaySection: DisplaySection, itemClick: (displayItem: DisplayItem) -> Unit) {
    Column {
        SectionTitle(displaySection.sectionTitle)
        val chunkedItems = displaySection.items.chunked(3)
        chunkedItems.forEach { rowItems ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                rowItems.forEach { BoxItem(it, itemClick) }

                repeat(3 - rowItems.size) {
                    Spacer(Modifier.weight(0.33f))
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Row(
        Modifier.fillMaxWidth().height(34.dp).background(Color.Black.copy(alpha = 0.11f)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.fillMaxHeight().width(10.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black.copy(alpha = 0.7f),
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RowScope.BoxItem(
    displayItem: DisplayItem,
    itemClick: (displayItem: DisplayItem) -> Unit
) {
    Column(
        Modifier.weight(0.33f)
            .height(86.dp)
            .border(0.3.dp, color = Color.LightGray).clickable {
                itemClick(displayItem)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.width(34.dp).size(28.dp),
            bitmap = rememberLocalImage(displayItem.img),
            contentDescription = null
        )

        Spacer(Modifier.fillMaxWidth().height(10.dp))
        Text(text = displayItem.title, fontSize = 14.sp, color = Color.Black.copy(alpha = 0.7f))
    }
}