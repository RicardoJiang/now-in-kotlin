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

package com.jiang.nowinkotlin.mainpage.sectionItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jiang.nowinkotlin.rememberLocalImage
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.feathertop
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Preview
// [START android_compose_components_dialogparent]
@Composable
internal fun DialogExamples() {
    // [START_EXCLUDE]
    val openMinimalDialog = remember { mutableStateOf(false) }
    val openDialogWithImage = remember { mutableStateOf(false) }
    val openFullScreenDialog = remember { mutableStateOf(false) }
    // [END_EXCLUDE]
    val openAlertDialog = remember { mutableStateOf(false) }

    // [START_EXCLUDE]
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Click the following button to toggle the given dialog example.")
        Button(
            onClick = { openMinimalDialog.value = !openMinimalDialog.value }
        ) {
            Text("Minimal dialog component")
        }
        Button(
            onClick = { openDialogWithImage.value = !openDialogWithImage.value }
        ) {
            Text("Dialog component with an image")
        }
        Button(
            onClick = { openAlertDialog.value = !openAlertDialog.value }
        ) {
            Text("Alert dialog component with buttons")
        }
        Button(
            onClick = { openFullScreenDialog.value = !openFullScreenDialog.value }
        ) {
            Text("Full screen dialog")
        }

        // [END_EXCLUDE]
        when {
            // [START_EXCLUDE]
            openMinimalDialog.value -> {
                MinimalDialog(
                    onDismissRequest = { openMinimalDialog.value = false },
                )
            }
            openDialogWithImage.value -> {
                DialogWithImage(
                    onDismissRequest = { openDialogWithImage.value = false },
                    onConfirmation = {
                        openDialogWithImage.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                    },
                    bitmap = rememberLocalImage(Res.drawable.feathertop),
                    imageDescription = "",
                )
            }
            openFullScreenDialog.value -> {
                FullScreenDialog(
                    onDismissRequest = { openFullScreenDialog.value = false },
                )
            }
            // [END_EXCLUDE]
            openAlertDialog.value -> {
                AlertDialogExample(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                    },
                    dialogTitle = "Alert dialog example",
                    dialogText = "This is an example of an alert dialog with buttons.",
                    icon = Icons.Default.Info
                )
            }
        }
    }
}
// [END android_compose_components_dialogparent]

// [START android_compose_components_minimaldialog]
@Composable
private fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .clickable {
                    onDismissRequest()
                },
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}
// [END android_compose_components_minimaldialog]

// [START android_compose_components_dialogwithimage]
@Composable
private fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    bitmap: ImageBitmap,
    imageDescription: String,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bitmap,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(160.dp)
                )
                Text(
                    text = "This is a dialog with buttons and an image.",
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}
// [END android_compose_components_dialogwithimage]

// [START android_compose_components_alertdialog]
@Composable
private fun AlertDialogExample(
    onDismissRequest: (() -> Unit)? = null,
    onConfirmation: (() -> Unit)? = null,
    dialogTitle: String = "dialogTitle",
    dialogText: String = "dialogText",
    icon: ImageVector = Icons.Default.Info,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest?.invoke()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation?.invoke()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest?.invoke()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}
// [END android_compose_components_alertdialog]

// [START android_compose_components_fullscreendialog]
@Composable
private fun FullScreenDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
        ),
    ) {
        Surface (
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "This is a full screen dialog",
                    textAlign = TextAlign.Center,
                )
                TextButton(onClick = { onDismissRequest() }) {
                    Text("Dismiss")
                }
            }
        }
    }
}
// [END android_compose_components_fullscreendialog]
