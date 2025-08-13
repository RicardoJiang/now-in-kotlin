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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
internal fun Modifier.carouselTransition(
    start: Float,
    stop: Float,
    index: Int,
    pagerState: PagerState
) = graphicsLayer {

    val pageOffset =
        ((pagerState.currentPage - index) + pagerState.currentPageOffsetFraction).absoluteValue

    val transformation = androidx.compose.ui.util.lerp(
        start = start,
        stop = stop,
        fraction = 1f - pageOffset.coerceIn(
            0f,
            1f
        )
    )
    alpha = transformation
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CarouselTransition() {
    val pageCount = 3
    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })

    Column(
        Modifier.width(300.dp).height(300.dp).background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize().background(Color.Blue),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 10.dp),
            pageSpacing = 20.dp
        ) { page: Int ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("文本:${page}", color = Color.White)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .carouselTransition(
                            start = 0.6f,
                            stop = 0.0f,
                            index = page,
                            pagerState = pagerState
                        ).background(Color.Black)
                ) {}

                Box(Modifier
                    .align(Alignment.BottomEnd)
                    .height(16.dp)
                    .width(16.dp).background(Color.Green)
                    .carouselTransition(
                        start = .0f,
                        stop = 1.0f,
                        index = page,
                        pagerState = pagerState
                    ))
            }
        }
    }
}