package com.jiang.nowinkotlin.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jiang.nowinkotlin.data.MonthlyReportItem
import com.jiang.nowinkotlin.network.KmpNetworkHelper
import com.jiang.nowinkotlin.parseMonthReport
import com.tencent.kmm.network.export.VBTransportContentType
import com.tencent.kmm.network.export.VBTransportGetRequest
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal data class MonthlyReportUIState(val monthlyReportList: List<MonthlyReportItem> = emptyList())

internal class MonthlyReportViewModel(val scope: CoroutineScope) : LifecycleAware {
    var uiState by mutableStateOf(MonthlyReportUIState())
        private set // 只允许类内部修改

    override fun onCreate() {
        scope.launch(context = Dispatchers.Default + CoroutineExceptionHandler { _, e ->
            println("MonthlyReportViewModel exception ${e.message}")
        }) {
            val getRequest = VBTransportGetRequest()
            getRequest.url = "https://www.nowinkotlin.top/index.json"

            getRequest.logTag = "MonthlyReport"
            getRequest.header["Content-Type"] = VBTransportContentType.JSON.toString()
            getRequest.header["Accept-Encoding"] = "identity"
            val result = KmpNetworkHelper.sendGetRequest(getRequest)
            val json = result.data.toString()
            val monthReportList = parseMonthReport(json).filter { item ->
                item.tags?.contains("技术月报") == true
            }
            uiState = uiState.copy(monthlyReportList = monthReportList)
        }
    }

    override fun onDestroy() {
    }

}
