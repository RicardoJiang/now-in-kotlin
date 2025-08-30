package com.jiang.nowinkotlin

import com.jiang.nowinkotlin.data.MonthlyReportItem
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
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
}
