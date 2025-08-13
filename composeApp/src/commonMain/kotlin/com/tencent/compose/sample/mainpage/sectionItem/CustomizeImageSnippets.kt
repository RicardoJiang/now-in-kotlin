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
 
@file:OptIn(ExperimentalResourceApi::class, ExperimentalResourceApi::class)

package com.tencent.compose.sample.mainpage.sectionItem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tencent.compose.sample.rememberLocalImage
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.home_icon
import nowinkotlin.composeapp.generated.resources.image_dog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val examples: List<ItemTitle> = listOf(
    ItemTitle("ContentScaleExample") {
        ContentScaleExample()
    },
    ItemTitle("ClipImageExample") {
        ClipImageExample()
    },
    ItemTitle("ClipRoundedCorner") {
        ClipRoundedCorner()
    },
    ItemTitle("CustomClippingShape") {
        CustomClippingShape()
    },
    ItemTitle("ImageWithBorder") {
        ImageWithBorder()
    },
    ItemTitle("ImageRainbowBorder") {
        ImageRainbowBorder()
    },
    ItemTitle("ImageAspectRatio") {
        ImageAspectRatio()
    },
    ItemTitle("ImageColorFilter") {
        ImageColorFilter()
    },
    ItemTitle("ImageBlendMode") {
        ImageBlendMode()
    },
    ItemTitle("ImageColorMatrix") {
        ImageColorMatrix()
    },
    ItemTitle("ImageAdjustBrightnessContrast") {
        ImageAdjustBrightnessContrast()
    },
    ItemTitle("ImageInvertColors") {
        ImageInvertColors()
    },
    ItemTitle("ImageBlur") {
        ImageBlur()
    },
    ItemTitle("ImageBlurBox") {
        ImageBlurBox()
    },
    ItemTitle("ImageBlurEdgeTreatment") {
        ImageBlurEdgeTreatment()
    }
)

private class ItemTitle(
    val name: String,
    val content: @Composable () -> Unit
)

@Preview
@Composable
internal fun ImageExamplesScreen(start: Int = 0, end: Int = examples.size) {
    if (start < 0 || end > examples.size) {
        throw RuntimeException("ImageExamplesScreen input error, start=$start, end=$end")
    }
    val items = examples.subList(start, end)
    LazyColumn(Modifier.padding(top = 15.dp, bottom = 15.dp)) {
        items(items) { item ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                item.content()
            }
        }
    }
}

@Composable
private fun ContentScaleExample() {
    // [START android_compose_content_scale]
    val imageModifier = Modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
    // [END android_compose_content_scale]
}

@Preview
@Composable
private fun ClipImageExample() {
    // [START android_compose_clip_image]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
    // [END android_compose_clip_image]
}

@Preview
@Composable
private fun ClipRoundedCorner() {
    // [START android_compose_clip_image_rounded_corner]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
    )
    // [END android_compose_clip_image_rounded_corner]
}

@Preview
@Composable
private fun CustomClippingShape() {
    // [START android_compose_custom_clipping_shape]
    class SquashedOval : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                // We create an Oval that starts at ¼ of the width, and ends at ¾ of the width of the container.
                addOval(
                    Rect(
                        left = size.width / 4f,
                        top = 0f,
                        right = size.width * 3 / 4f,
                        bottom = size.height
                    )
                )
            }
            return Outline.Generic(path = path)
        }
    }

    Box(modifier = Modifier.background(Color.Green)) {
        Image(
            bitmap = rememberLocalImage(Res.drawable.image_dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(SquashedOval())
        )
    }
    // [END android_compose_custom_clipping_shape]
}

@Preview
@Composable
private fun ImageWithBorder() {
    // [START android_compose_image_border]
    val borderWidth = 4.dp
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, Color.Yellow),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
    // [END android_compose_image_border]
}

@Preview
@Composable
private fun ImageRainbowBorder() {
    // [START android_compose_image_rainbow_border]
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    val borderWidth = 15.dp
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, rainbowColorsBrush),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )

    val density = LocalDensity.current.density
    val pxValue = borderWidth * density
    println("ImageRainbowBorder to px$pxValue")

    Box(
        modifier = Modifier.size(100.dp).background(Color.Black).border(
            BorderStroke(borderWidth, rainbowColorsBrush),
            CircleShape
        )
    ) {

    }
    // [END android_compose_image_rainbow_border]
}

@Composable
@Preview
private fun ImageAspectRatio() {
    // [START android_compose_image_aspect_ratio]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        modifier = Modifier.aspectRatio(16f / 9f)
    )
    // [END android_compose_image_aspect_ratio]
}

@Composable
@Preview
private fun ImageColorFilter() {
    // [START android_compose_image_color_filter]
    Column {
        Image(
            modifier = Modifier.size(32.dp),
            bitmap = rememberLocalImage(Res.drawable.home_icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Red, BlendMode.SrcAtop)
        )
        Text(text = "首页")
    }
    // [END android_compose_image_color_filter]
}

@Preview
@Composable
private fun ImageBlendMode() {
    // [START android_compose_image_blend_mode]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        colorFilter = ColorFilter.tint(Color.Green, blendMode = BlendMode.Darken)
    )
    // [END android_compose_image_blend_mode]
}

@Preview
@Composable
private fun ImageColorMatrix() {
    // [START android_compose_image_colormatrix]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    )
    // [END android_compose_image_colormatrix]
}

@Preview
@Composable
private fun ImageAdjustBrightnessContrast() {
    // [START android_compose_image_brightness]
    val contrast = 2f // 0f..10f (1 should be default)
    val brightness = -180f // -255f..255f (0 should be default)
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
    )
    // [END android_compose_image_brightness]
}

@Preview
@Composable
private fun ImageInvertColors() {
    // [START android_compose_image_invert_colors]
    val colorMatrix = floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f
    )
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
    )
    // [END android_compose_image_invert_colors]
}

@Preview
@Composable
private fun ImageBlur() {
    var blurRadius by remember { mutableStateOf(40.dp) }
    // [START android_compose_image_blur]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clickable {
                blurRadius = if (blurRadius == 40.dp) {
                    0.dp
                } else {
                    40.dp
                }
            }
            .blur(
                radiusX = blurRadius,
                radiusY = blurRadius,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )
    // [END android_compose_image_blur]
}

@Preview
@Composable
private fun ImageBlurBox() {
    // [START android_compose_image_blur]
    var blurRadius by remember { mutableStateOf(40.dp) }
    // [START android_compose_image_blur]

    Box(
        modifier = Modifier
            .background(Color.Black)
            .size(150.dp)
            .blur(blurRadius).clickable {
                blurRadius = if (blurRadius == 40.dp) {
                    0.dp
                } else {
                    40.dp
                }
            }
    ) {
        Image(
            bitmap = rememberLocalImage(Res.drawable.image_dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
        )
    }
    // [END android_compose_image_blur]
}

@Preview
@Composable
private fun ImageBlurEdgeTreatment() {
    // [START android_compose_image_blur_edge_treatment]
    Image(
        bitmap = rememberLocalImage(Res.drawable.image_dog),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment.Unbounded
            )
            .clip(RoundedCornerShape(8.dp))
    )
    // / [END android_compose_image_blur_edge_treatment]
}