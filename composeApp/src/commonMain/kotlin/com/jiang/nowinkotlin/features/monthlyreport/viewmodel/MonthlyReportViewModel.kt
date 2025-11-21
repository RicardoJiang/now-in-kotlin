package com.jiang.nowinkotlin.features.monthlyreport.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jiang.nowinkotlin.features.monthlyreport.data.MonthlyReportRepository
import com.jiang.nowinkotlin.shared.data.MonthlyReportItem
import com.jiang.nowinkotlin.shared.viewmodel.LifecycleAware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 技术月报 UI 状态
 * @param monthlyReportList 月报列表
 * @param isLoading 是否正在加载
 * @param error 错误信息，null 表示无错误
 */
internal data class MonthlyReportUIState(
    val monthlyReportList: List<MonthlyReportItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * 技术月报 ViewModel
 * 负责管理技术月报页面的状态和业务逻辑
 */
internal class MonthlyReportViewModel(
    val scope: CoroutineScope,
    private val repository: MonthlyReportRepository
) : LifecycleAware {
    var uiState by mutableStateOf(MonthlyReportUIState())
        private set // 只允许类内部修改

    override fun onCreate() {
        loadMonthlyReports()
    }

    override fun onDestroy() {
        // 清理资源（如需要）
    }

    /**
     * 加载技术月报数据
     */
    private fun loadMonthlyReports() {
        scope.launch {
            try {
                // 设置加载状态
                uiState = uiState.copy(isLoading = true, error = null)

                // 从 Repository 获取数据
                val reports = repository.fetchMonthlyReports()

                // 更新 UI 状态
                uiState = uiState.copy(
                    monthlyReportList = reports,
                    isLoading = false
                )
            } catch (e: Exception) {
                // 处理异常，更新错误状态
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message ?: "加载失败"
                )
            }
        }
    }
}
