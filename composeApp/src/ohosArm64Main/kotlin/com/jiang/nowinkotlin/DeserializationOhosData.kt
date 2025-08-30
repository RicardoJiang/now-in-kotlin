package com.jiang.nowinkotlin

import com.jiang.nowinkotlin.data.MonthlyReportItem

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
}