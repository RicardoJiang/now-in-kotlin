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

package com.jiang.nowinkotlin.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

internal data class DisplaySection(
    val sectionTitle: String,
    val items: List<DisplayItem>
)

@OptIn(ExperimentalResourceApi::class)
@Immutable
internal data class DisplayItem(
    val title: String,
    val img: DrawableResource,
    val content: @Composable () -> Unit
)

@Serializable
data class MonthlyReportItem(
    val permalink: String,
    val publishDate: String,
    val tags: List<String>?,
    val title: String
) {
    val year: String
        get() = publishDate.split("T")[0].split("-")[0]

    val month: String
        get() = publishDate.split("T")[0].split("-")[1]

    val displayYMD: String
        get() = publishDate.split("T")[0]
}

data class Episode(
    val index: Int,
    val size: Int,
    val episodeTitle: String,
    val pubDate: String,
    val episodeDuration: Int,
    val imageUrl: String,
    val audioUrl: String,
    val description: String,
    val tags: List<String>
) {
    val id: String
        get() = index.toString()
    val episodeNumber: String
        get() = "EP ${size - index}"

    val duration: String
        get() {
            val hour = episodeDuration / 3600
            val minute = (episodeDuration % 3600) / 60
            val second = episodeDuration % 60
            var result = ""
            if (hour > 0) {
                result += if (hour < 10) {
                    "0$hour"
                } else {
                    hour
                }
                result += ":"
            }
            result += if (minute < 10) {
                "0$minute"
            } else {
                minute
            }
            result += ":"
            result += if (second < 10) {
                "0$second"
            } else {
                second
            }
            return result
        }

    val date: String
        get() = pubDate.split(" ")[0]

    val title: String
        get() {
            val titleNumber = size - index
            return episodeTitle.replace("#$titleNumber -", "")
                .replace("#$titleNumber ", "").trim()
        }

    val displayDescription: String
        get() {
            return description.replace("</p>", "")
                .replace("<p>", "")
                .replace("</ul>", "")
                .replace("<ul>", "")
                .replace("</li>", "")
                .replace("<li>", "")
                .replace("<br>", "")
                .split("其他收视/听平台")[0].trim()
        }
}

@Serializable
data class EpisodeModel(
    val title: String,
    val pubDate: String,
    val author: String,
    val thumbnail: String,
    val description: String,
    val audioResource: AudioResourceModel
)

@Serializable
data class AudioResourceModel(
    val link: String,
    val type: String,
    val duration: Int
)