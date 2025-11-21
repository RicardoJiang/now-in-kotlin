package com.jiang.nowinkotlin.features.monthlyreport.data

import com.jiang.nowinkotlin.core.network.KmpNetworkHelper
import com.jiang.nowinkotlin.shared.data.MonthlyReportItem
import com.tencent.kmm.network.export.VBTransportContentType
import com.tencent.kmm.network.export.VBTransportGetRequest
import kotlinx.serialization.json.Json

/**
 * 技术月报数据仓库实现类
 * 负责从网络获取并解析技术月报数据
 */
internal class MonthlyReportRepositoryImpl : MonthlyReportRepository {
    private val jsonHelper by lazy {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    override suspend fun fetchMonthlyReports(): List<MonthlyReportItem> {
        val getRequest = VBTransportGetRequest()
        getRequest.url = "https://www.nowinkotlin.top/index.json"
        getRequest.logTag = "MonthlyReport"
        getRequest.header["Content-Type"] = VBTransportContentType.JSON.toString()
        getRequest.header["Accept-Encoding"] = "identity"

        val result = KmpNetworkHelper.sendGetRequest(getRequest)
        val json = result.data.toString()

        // 解析 JSON 并过滤包含"技术月报"标签的文章
        return jsonHelper.decodeFromString<List<MonthlyReportItem>>(json)
            .filter { item -> item.tags?.contains("技术月报") == true }
    }
}
