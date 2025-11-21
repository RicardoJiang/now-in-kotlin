package com.jiang.nowinkotlin.shared.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * MonthlyReportItem 数据模型测试
 * 测试数据类的计算属性和基本功能
 */
class MonthlyReportItemTest {

    @Test
    fun testYearMonthAndDisplayYMDParsing() {
        // 测试日期解析和计算属性
        val item = MonthlyReportItem(
            permalink = "https://example.com/post",
            publishDate = "2024-03-15T10:00:00Z",
            tags = listOf("技术月报", "Kotlin", "Android"),
            title = "March 2024 Technical Report"
        )

        assertEquals("2024", item.year)
        assertEquals("03", item.month)
        assertEquals("2024-03-15", item.displayYMD)
        assertEquals("March 2024 Technical Report", item.title)
    }

    @Test
    fun testItemWithTags() {
        val item = MonthlyReportItem(
            permalink = "https://example.com/post",
            publishDate = "2024-01-01T00:00:00Z",
            tags = listOf("技术月报", "Kotlin"),
            title = "Test Report"
        )

        assertTrue(item.tags?.contains("技术月报") == true)
        assertTrue(item.tags?.contains("Kotlin") == true)
        assertFalse(item.tags?.contains("Android") == true)
    }

    @Test
    fun testItemWithoutTags() {
        val item = MonthlyReportItem(
            permalink = "https://example.com/post",
            publishDate = "2023-12-01T00:00:00Z",
            tags = null,
            title = "Test Report"
        )

        assertEquals("2023", item.year)
        assertEquals("12", item.month)
        assertEquals("2023-12-01", item.displayYMD)
        assertNull(item.tags)
    }

    @Test
    fun testItemWithEmptyTags() {
        val item = MonthlyReportItem(
            permalink = "https://example.com/post",
            publishDate = "2024-01-01T00:00:00Z",
            tags = emptyList(),
            title = "Test"
        )

        assertFalse(item.tags?.contains("技术月报") == true)
        assertEquals(0, item.tags?.size)
    }

    @Test
    fun testMultipleYearAndMonthFormats() {
        // 测试不同的日期格式
        val january = MonthlyReportItem(
            permalink = "https://example.com/1",
            publishDate = "2024-01-05T10:30:00Z",
            tags = listOf("技术月报"),
            title = "January"
        )

        val december = MonthlyReportItem(
            permalink = "https://example.com/2",
            publishDate = "2024-12-25T15:45:30Z",
            tags = listOf("技术月报"),
            title = "December"
        )

        val may2023 = MonthlyReportItem(
            permalink = "https://example.com/3",
            publishDate = "2023-05-15T08:00:00Z",
            tags = listOf("技术月报"),
            title = "May"
        )

        // 验证 January
        assertEquals("2024", january.year)
        assertEquals("01", january.month)
        assertEquals("2024-01-05", january.displayYMD)

        // 验证 December
        assertEquals("2024", december.year)
        assertEquals("12", december.month)
        assertEquals("2024-12-25", december.displayYMD)

        // 验证 May 2023
        assertEquals("2023", may2023.year)
        assertEquals("05", may2023.month)
        assertEquals("2023-05-15", may2023.displayYMD)
    }

    @Test
    fun testDateWithDifferentTimeFormats() {
        // 测试不同时间格式的处理
        val morning = MonthlyReportItem(
            permalink = "https://example.com/1",
            publishDate = "2024-06-10T08:00:00Z",
            tags = null,
            title = "Morning Post"
        )

        val evening = MonthlyReportItem(
            permalink = "https://example.com/2",
            publishDate = "2024-06-10T20:30:45Z",
            tags = null,
            title = "Evening Post"
        )

        // 两者应该有相同的 year, month, displayYMD
        assertEquals(morning.year, evening.year)
        assertEquals(morning.month, evening.month)
        assertEquals(morning.displayYMD, evening.displayYMD)
    }
}
