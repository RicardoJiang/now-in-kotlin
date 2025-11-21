package com.jiang.nowinkotlin.features.monthlyreport.data

import com.jiang.nowinkotlin.shared.data.MonthlyReportItem

/**
 * 技术月报数据仓库接口
 * 负责获取技术月报数据
 */
interface MonthlyReportRepository {
    /**
     * 获取技术月报列表
     * @return 包含"技术月报"标签的文章列表
     * @throws Exception 网络请求失败或解析错误时抛出异常
     */
    suspend fun fetchMonthlyReports(): List<MonthlyReportItem>
}
