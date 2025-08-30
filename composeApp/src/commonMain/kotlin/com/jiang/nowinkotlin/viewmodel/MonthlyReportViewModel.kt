package com.jiang.nowinkotlin.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jiang.nowinkotlin.data.MonthlyReportItem
import com.jiang.nowinkotlin.parseMonthReport
import com.tencent.kmm.network.export.VBTransportContentType
import com.tencent.kmm.network.export.VBTransportGetRequest
import com.tencent.kmm.network.internal.VBPBLog
import com.tencent.kmm.network.service.VBTransportService
import kotlinx.coroutines.CoroutineScope

internal data class MonthlyReportUIState(val monthlyReportList: List<MonthlyReportItem> = emptyList())

internal class MonthlyReportViewModel(val scope: CoroutineScope) : LifecycleAware {
    var uiState by mutableStateOf(MonthlyReportUIState())
        private set // 只允许类内部修改

    override fun onCreate() {
        val getRequest = VBTransportGetRequest()
        getRequest.url =
            "https://www.nowinkotlin.top/index.json"

        getRequest.logTag = "MonthlyReport"
        getRequest.header["Content-Type"] = VBTransportContentType.JSON.toString()
        getRequest.header["Accept-Encoding"] = "identity"
        VBTransportService.sendGetRequest(getRequest) {
            val json = it.data.toString()
            val monthReportList = parseMonthReport(json).filter { item ->
                item.tags?.contains("技术月报") == true
            }
            VBPBLog.i(
                "[TRACE]",
                "get response code:${it.errorCode}, message:${it.errorMessage}, data: ${it.data}, request: ${it.request}, server ip:${it.serverIP}, port:${it.serverPort}"
            )
            uiState = uiState.copy(monthlyReportList = monthReportList)
        }
    }

    override fun onDestroy() {
    }

}
