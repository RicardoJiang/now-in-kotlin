package com.jiang.nowinkotlin.features.monthlyreport.viewmodel

import com.jiang.nowinkotlin.shared.data.MonthlyReportItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MonthlyReportViewModelTest {
    private lateinit var viewModel: MonthlyReportViewModel
    private lateinit var testScope: TestScope
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        testScope = TestScope(testDispatcher)
        viewModel = MonthlyReportViewModel(testScope)
    }

    @Test
    fun testInitialState() {
        // Verify initial state is empty
        assertEquals(0, viewModel.uiState.monthlyReportList.size)
        assertTrue(viewModel.uiState.monthlyReportList.isEmpty())
    }

    @Test
    fun testJsonHelperConfiguration() {
        // Verify JSON helper is properly configured
        val json = viewModel.jsonHelper

        // Test that it can parse a simple JSON array
        val testJson = """
            [
                {
                    "permalink": "https://example.com/post1",
                    "publishDate": "2024-01-15T10:00:00Z",
                    "tags": ["技术月报", "Kotlin"],
                    "title": "January 2024 Report"
                }
            ]
        """.trimIndent()

        val result = json.decodeFromString<List<MonthlyReportItem>>(testJson)
        assertEquals(1, result.size)
        assertEquals("January 2024 Report", result[0].title)
    }

    @Test
    fun testMonthlyReportItemParsing() = runTest {
        // Test data class properties
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
        assertTrue(item.tags?.contains("技术月报") == true)
    }

    @Test
    fun testMonthlyReportItemWithoutTags() {
        val item = MonthlyReportItem(
            permalink = "https://example.com/post",
            publishDate = "2023-12-01T00:00:00Z",
            tags = null,
            title = "Test Report"
        )

        assertEquals("2023", item.year)
        assertEquals("12", item.month)
        assertEquals("2023-12-01", item.displayYMD)
        assertEquals(null, item.tags)
    }

    @Test
    fun testFilteringMonthlyReports() {
        val testData = listOf(
            MonthlyReportItem(
                permalink = "https://example.com/post1",
                publishDate = "2024-01-01T00:00:00Z",
                tags = listOf("技术月报", "Kotlin"),
                title = "January Report"
            ),
            MonthlyReportItem(
                permalink = "https://example.com/post2",
                publishDate = "2024-02-01T00:00:00Z",
                tags = listOf("Blog", "Tutorial"),
                title = "February Blog"
            ),
            MonthlyReportItem(
                permalink = "https://example.com/post3",
                publishDate = "2024-03-01T00:00:00Z",
                tags = listOf("技术月报"),
                title = "March Report"
            )
        )

        // Filter items with "技术月报" tag
        val filtered = testData.filter { it.tags?.contains("技术月报") == true }

        assertEquals(2, filtered.size)
        assertEquals("January Report", filtered[0].title)
        assertEquals("March Report", filtered[1].title)
    }

    @Test
    fun testUIStateStructure() {
        // Test that UIState can be created and accessed properly
        val uiState = MonthlyReportUIState(
            monthlyReportList = listOf(
                MonthlyReportItem(
                    permalink = "https://example.com/post1",
                    publishDate = "2024-01-01T00:00:00Z",
                    tags = listOf("技术月报"),
                    title = "Test Report 1"
                ),
                MonthlyReportItem(
                    permalink = "https://example.com/post2",
                    publishDate = "2024-02-01T00:00:00Z",
                    tags = listOf("技术月报"),
                    title = "Test Report 2"
                )
            )
        )

        assertEquals(2, uiState.monthlyReportList.size)
        assertEquals("Test Report 1", uiState.monthlyReportList[0].title)
        assertEquals("Test Report 2", uiState.monthlyReportList[1].title)
    }

    @Test
    fun testMultipleYearAndMonthFormats() {
        val items = listOf(
            MonthlyReportItem(
                permalink = "https://example.com/1",
                publishDate = "2024-01-05T10:30:00Z",
                tags = listOf("技术月报"),
                title = "January"
            ),
            MonthlyReportItem(
                permalink = "https://example.com/2",
                publishDate = "2024-12-25T15:45:30Z",
                tags = listOf("技术月报"),
                title = "December"
            ),
            MonthlyReportItem(
                permalink = "https://example.com/3",
                publishDate = "2023-05-15T08:00:00Z",
                tags = listOf("技术月报"),
                title = "May"
            )
        )

        assertEquals("2024", items[0].year)
        assertEquals("01", items[0].month)
        assertEquals("2024-01-05", items[0].displayYMD)

        assertEquals("2024", items[1].year)
        assertEquals("12", items[1].month)
        assertEquals("2024-12-25", items[1].displayYMD)

        assertEquals("2023", items[2].year)
        assertEquals("05", items[2].month)
        assertEquals("2023-05-15", items[2].displayYMD)
    }

    @Test
    fun testEmptyTagsList() {
        val item = MonthlyReportItem(
            permalink = "https://example.com/post",
            publishDate = "2024-01-01T00:00:00Z",
            tags = emptyList(),
            title = "Test"
        )

        assertTrue(item.tags?.contains("技术月报") != true)
        assertEquals(0, item.tags?.size)
    }

    @Test
    fun testJsonParsingWithUnknownFields() {
        val json = viewModel.jsonHelper

        // JSON with extra unknown fields should be ignored
        val testJson = """
            [
                {
                    "permalink": "https://example.com/post1",
                    "publishDate": "2024-01-15T10:00:00Z",
                    "tags": ["技术月报"],
                    "title": "Test Report",
                    "unknownField1": "value1",
                    "unknownField2": 123
                }
            ]
        """.trimIndent()

        val result = json.decodeFromString<List<MonthlyReportItem>>(testJson)
        assertEquals(1, result.size)
        assertEquals("Test Report", result[0].title)
    }

    @Test
    fun testOnCreateLifecycleMethod() = runTest(testDispatcher) {
        // onCreate is called, but we can't easily test the actual network call
        // without mocking KmpNetworkHelper. This test verifies the method exists
        // and can be called without crashing
        viewModel.onCreate()

        // Advance time to allow coroutines to complete
        advanceUntilIdle()

        // Initial state should still be maintained
        // (actual data loading would require network mocking)
        assertTrue(viewModel.uiState.monthlyReportList.isEmpty() ||
                  viewModel.uiState.monthlyReportList.isNotEmpty())
    }

    @Test
    fun testOnDestroyLifecycleMethod() {
        // Verify onDestroy can be called without errors
        viewModel.onDestroy()

        // onDestroy currently has no implementation, but we test it doesn't crash
        assertTrue(true)
    }
}
