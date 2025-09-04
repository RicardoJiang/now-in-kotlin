package com.jiang.nowinkotlin

import com.jiang.nowinkotlin.data.MonthlyReportItem
import com.jiang.nowinkotlin.data.Episode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

internal object DeserializationiOSData {
    fun parseMonthReport(json: String): List<MonthlyReportItem> {
        val lenientJson = Json { isLenient = true }
        val jsonArray = lenientJson.decodeFromString(JsonArray.serializer(), json)
        return jsonArray.map { jsonElement ->
            require(jsonElement is JsonObject) { "Array element is not a JsonObject" }

            val content = jsonElement["content"]?.jsonPrimitive?.content ?: ""
            val permalink = jsonElement["permalink"]?.jsonPrimitive?.content ?: ""
            val publishDate = jsonElement["publishDate"]?.jsonPrimitive?.content ?: ""
            val summary = jsonElement["summary"]?.jsonPrimitive?.content ?: ""
            val title = jsonElement["title"]?.jsonPrimitive?.content ?: ""

            val tagsArray = jsonElement["tags"] as? JsonArray
            val tags = tagsArray?.map { it.jsonPrimitive.content } ?: emptyList()

            MonthlyReportItem(
                permalink = permalink,
                publishDate = publishDate,
                tags = tags,
                title = title
            )
        }
    }

    fun parseKotlinStoveList(json: String): List<Episode> {
        val jsonArray = Json.decodeFromString(JsonArray.serializer(), json)
        return jsonArray.mapIndexed { index, jsonElement ->
            require(jsonElement is JsonObject) { "Array element is not a JsonObject" }

            val title = jsonElement["title"]?.jsonPrimitive?.content ?: ""
            val pubDate = jsonElement["pubDate"]?.jsonPrimitive?.content ?: ""
            val thumbnail = jsonElement["thumbnail"]?.jsonPrimitive?.content ?: ""
            val description = jsonElement["description"]?.jsonPrimitive?.content ?: ""
            val audioResource = jsonElement["audioResource"]?.jsonObject

            val audioUrl = audioResource?.get("link")?.jsonPrimitive?.content ?: ""
            val audioDuration = audioResource?.get("duration")?.jsonPrimitive?.int ?: 0

            Episode(
                index = index,
                size = jsonArray.size,
                episodeTitle = title,
                pubDate = pubDate,
                episodeDuration = audioDuration,
                imageUrl = thumbnail,
                audioUrl = audioUrl,
                description = description,
                tags = emptyList()
            )
        }
    }
}
