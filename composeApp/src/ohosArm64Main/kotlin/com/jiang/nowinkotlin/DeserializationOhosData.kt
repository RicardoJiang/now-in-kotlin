package com.jiang.nowinkotlin

import com.jiang.nowinkotlin.data.MonthlyReportItem
import com.jiang.nowinkotlin.data.Episode

internal object DeserializationOhosData {
    fun parseMonthReport(json: String): List<MonthlyReportItem> {
        val hashMap = getOhosServicesApi().parseJson(json)
        val result = mutableListOf<MonthlyReportItem>()
        hashMap.forEach {
            val item = it.value
            if (item is HashMap<*, *> && item.get("tags") is ArrayList<*>) {
                result.add(
                    MonthlyReportItem(
                        permalink = item.get("permalink").toString(),
                        title = item.get("title").toString(),
                        publishDate = item.get("publishDate").toString(),
                        tags = item.get("tags") as ArrayList<String>
                    )
                )
            }
        }
        return result
    }

    fun parseKotlinStoveList(json: String): List<Episode> {
        val hashMap = getOhosServicesApi().parseJson(json)
        val result = mutableListOf<Episode>()
        var index = 0
        val size = hashMap.values.filterIsInstance<HashMap<*,*>>().size
        hashMap.forEach {
            val item = it.value
            if (item is HashMap<*, *>) {
                val audioResource = item.get("audioResource") as HashMap<*, *>
                result.add(
                    Episode(
                        index = index,
                        size = size,
                        episodeTitle = (item.get("title") ?: "").toString(),
                        pubDate = (item.get("pubDate") ?: "").toString(),
                        episodeDuration = (audioResource.get("duration") ?: "0").toString().toFloat().toInt(),
                        imageUrl = (item.get("thumbnail") ?: "").toString(),
                        audioUrl = (audioResource.get("link") ?: "").toString(),
                        description = (item.get("description") ?: "").toString(),
                        tags = emptyList()
                    )
                )
                index++
            }
        }
        return result
    }
}