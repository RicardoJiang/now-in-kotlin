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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun SwitchExamples() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Minimal switch component")
        SwitchMinimalExample()
        Text("Switch with label")
        SwitchWithLabelMinimalExample()
        Text("Switch with custom colors")
        SwitchWithCustomColors()
    }
}

@Preview
// [START android_compose_components_switchminimal]
@Composable
private fun SwitchMinimalExample() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }, modifier = Modifier.testTag("minimalSwitch")
    )
}
// [END android_compose_components_switchminimal]

@Preview
// [START android_compose_components_switchwithlabel]
@Composable
private fun SwitchWithLabelMinimalExample() {
    var checked by remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = if (checked) "Checked" else "Unchecked",
        )
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }, modifier = Modifier.testTag("minimalLabelSwitch")
        )
    }
}
// [END android_compose_components_switchwithlabel]

@Preview
// [START android_compose_components_switchwithcustomcolors]
@Composable
private fun SwitchWithCustomColors() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        modifier = Modifier.testTag("customSwitch"),
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color(0.4f, 0.3137255f, 0.6431373f, 1.0f),
            checkedTrackColor = Color(0.91764706f, 0.8666667f, 1.0f, 1.0f),
            uncheckedThumbColor = Color(0.38431373f, 0.35686275f, 0.44313726f, 1.0f),
            uncheckedTrackColor = Color(0.9098039f, 0.87058824f, 0.972549f, 1.0f),
        )
    )
}
// [END android_compose_components_switchwithcustomcolors]
