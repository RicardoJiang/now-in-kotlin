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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SimpleTextPage() {
    val textContent = "Compose Text 文本 & AnnotatedString 多种样式的文本的基本数据结构"
    LazyColumn(modifier = Modifier.fillMaxSize().fillMaxHeight()) {

        item {
            AnnotatedStringTextPage()
        }

        item {
            Text(
                text = "fontSize = 16.sp color = Red $textContent",
                color = Color.Red,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "fontSize = 18.sp color = Blue, $textContent",
                color = Color.Blue,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "FontWeight.Light, $textContent",
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "FontWeight.Bold, $textContent",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "FontWeight.Black, $textContent",
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "FontFamily.Cursive, $textContent",
                fontFamily = FontFamily.Cursive
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "FontFamily.Serif, $textContent",
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "letterSpacing = 4.sp, $textContent",
                letterSpacing = 4.sp
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextDecoration.Underline, $textContent",
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextDecoration.LineThrough, $textContent",
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextAlign.Left, $textContent",
                textAlign = TextAlign.Left,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextAlign.Center, $textContent",
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextAlign.Right, $textContent",
                textAlign = TextAlign.Right,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextAlign.Justify, $textContent",
                textAlign = TextAlign.Justify,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "lineHeight = 40.sp, $textContent",
                lineHeight = 40.sp,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextOverflow.Clip, $textContent",
                overflow = TextOverflow.Clip,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "softWrap = false, $textContent $textContent",
                softWrap = false,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "TextOverflow.Ellipsis, $textContent $textContent",
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "style = background:Color.Gray, $textContent",
                style = TextStyle(background = Color.Gray)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
        item {
            Text(
                text = "style = Brush.linearGradient, $textContent",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Blue)
                    ), alpha = 0.8f
                )
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        }
    }
}

@Composable
private fun AnnotatedStringTextPage() {
    val annotatedString3 = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) {
            append("Compose Text ")
        }
        withStyle(style = SpanStyle()) {
            append("通过")
        }
        withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, color = Color.Red)) {
            append(" AnnotatedString ")
        }
        withStyle(style = SpanStyle()) {
            append("设置富文本效果！")
        }
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("点击 www.qq.com")
        }

        addStringAnnotation(
            tag = "URL",
            annotation = "https://v.qq.com",
            start = 40,
            end = 55
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = annotatedString3, modifier = Modifier.clickable {
            annotatedString3.getStringAnnotations("URL", 7, 14)
                .firstOrNull()?.let { it ->
                    println("jump to v.qq.com $it")
                }
        })
        ClickableText(
            text = AnnotatedString("ClickableText ") + annotatedString3,
            onClick = { integer ->
                annotatedString3.getStringAnnotations("URL", integer, integer).firstOrNull()?.let {
                    println("jump to v.qq.com $it")
                }
            })
    }

}