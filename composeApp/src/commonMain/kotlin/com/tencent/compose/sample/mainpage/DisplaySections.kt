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

import com.tencent.compose.sample.LinearGradientLine
import com.tencent.compose.sample.MultiTouches
import com.tencent.compose.sample.mainpage.sectionItem.BouncingBallsApp
import com.tencent.compose.sample.data.DisplayItem
import com.tencent.compose.sample.data.DisplaySection
import com.tencent.compose.sample.mainpage.sectionItem.CarouselTransition
import com.tencent.compose.sample.mainpage.sectionItem.CheckboxExamples
import com.tencent.compose.sample.mainpage.sectionItem.DialogExamples
import com.tencent.compose.sample.mainpage.sectionItem.DropdownMenu
import com.tencent.compose.sample.mainpage.sectionItem.FallingBalls
import com.tencent.compose.sample.mainpage.sectionItem.GestureDemo
import com.tencent.compose.sample.mainpage.sectionItem.ImageExamplesScreen
import com.tencent.compose.sample.mainpage.sectionItem.NestedScrollDemo
import com.tencent.compose.sample.mainpage.sectionItem.ProgressIndicatorExamples
import com.tencent.compose.sample.mainpage.sectionItem.SimpleImage
import com.tencent.compose.sample.mainpage.sectionItem.SimpleTextPage
import com.tencent.compose.sample.mainpage.sectionItem.SliderExamples
import com.tencent.compose.sample.mainpage.sectionItem.SwitchExamples
import com.tencent.compose.sample.mainpage.sectionItem.TextField2
import com.tencent.compose.sample.mainpage.sectionItem.TextField3
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.balls
import nowinkotlin.composeapp.generated.resources.carousel
import nowinkotlin.composeapp.generated.resources.cat
import nowinkotlin.composeapp.generated.resources.checkbox
import nowinkotlin.composeapp.generated.resources.dialog
import nowinkotlin.composeapp.generated.resources.dog
import nowinkotlin.composeapp.generated.resources.falling
import nowinkotlin.composeapp.generated.resources.gesture
import nowinkotlin.composeapp.generated.resources.gradient
import nowinkotlin.composeapp.generated.resources.menu
import nowinkotlin.composeapp.generated.resources.multi_touch
import nowinkotlin.composeapp.generated.resources.progress
import nowinkotlin.composeapp.generated.resources.scroll
import nowinkotlin.composeapp.generated.resources.simple_text
import nowinkotlin.composeapp.generated.resources.sliders
import nowinkotlin.composeapp.generated.resources.switch
import nowinkotlin.composeapp.generated.resources.text_field
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
internal fun displaySections(): List<DisplaySection> {
    return listOf(
        DisplaySection(
            sectionTitle = "Compose Component",
            items = listOf(
                DisplayItem("dialog21", Res.drawable.dialog) { DialogExamples() },
                DisplayItem("switch", Res.drawable.switch) { SwitchExamples() },
                DisplayItem("sliders", Res.drawable.sliders) { SliderExamples() },
                DisplayItem("checkbox", Res.drawable.checkbox) { CheckboxExamples() },
                DisplayItem("progress", Res.drawable.progress) { ProgressIndicatorExamples() },
                DisplayItem("simple-text", Res.drawable.simple_text) { SimpleTextPage() },
                DisplayItem("image-cat", Res.drawable.cat) { SimpleImage() },
                DisplayItem("image-dog", Res.drawable.dog) { ImageExamplesScreen() },
                DisplayItem("carousel", Res.drawable.carousel) { CarouselTransition() },
            )
        ),
        DisplaySection(
            sectionTitle = "Input Box",
            items = listOf(
                DisplayItem("keyboard-type", Res.drawable.text_field) { TextField2() },
                DisplayItem("alert-type", Res.drawable.text_field) { TextField3() },
            )
        ),
        DisplaySection(
            sectionTitle = "Mixed native UI & Gesture",
            items = listOf(
                DisplayItem("NestedScrollDemo", Res.drawable.scroll) { NestedScrollDemo() },
                DisplayItem("MultiTouches", Res.drawable.multi_touch) { MultiTouches() },
                DisplayItem("drag", Res.drawable.gesture) { GestureDemo() }
            ) + platformSections()
        ),
        DisplaySection(
            sectionTitle = "Others",
            items = listOf(
                DisplayItem("Bouncing Balls", Res.drawable.balls) { BouncingBallsApp() },
                DisplayItem("Falling Balls", Res.drawable.falling) { FallingBalls() },
                DisplayItem("DropdownMenu", Res.drawable.menu) { DropdownMenu() },
                DisplayItem("GradientLine", Res.drawable.gradient) { LinearGradientLine() }
            )
        )
    )
}

internal expect fun platformSections() : List<DisplayItem>
