package com.jiang.nowinkotlin.features.monthlyreport.viewmodel

import com.jiang.nowinkotlin.features.monthlyreport.data.MonthlyReportRepository
import com.jiang.nowinkotlin.shared.data.MonthlyReportItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * FakeRepository 用于测试
 * 提供可控的测试数据和行为
 */
class FakeMonthlyReportRepository : MonthlyReportRepository {
    var shouldThrowError = false
    var errorMessage = "Network error"
    var mockData: List<MonthlyReportItem> = emptyList()
    var fetchCallCount = 0

    override suspend fun fetchMonthlyReports(): List<MonthlyReportItem> {
        fetchCallCount++
        if (shouldThrowError) {
            throw Exception(errorMessage)
        }
        return mockData
    }

    fun reset() {
        shouldThrowError = false
        errorMessage = "Network error"
        mockData = emptyList()
        fetchCallCount = 0
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MonthlyReportViewModelTest {
    private lateinit var viewModel: MonthlyReportViewModel
    private lateinit var fakeRepository: FakeMonthlyReportRepository
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        // 设置测试调度器为 Main 调度器
        Dispatchers.setMain(testDispatcher)

        fakeRepository = FakeMonthlyReportRepository()
        // 创建一个测试 CoroutineScope
        val testScope = TestScope(testDispatcher)
        viewModel = MonthlyReportViewModel(testScope, fakeRepository)
    }

    @AfterTest
    fun tearDown() {
        // 重置 Main 调度器
        Dispatchers.resetMain()
    }

    // ==================== ViewModel 基础测试 ====================

    @Test
    fun testInitialState() {
        // 验证初始状态
        assertEquals(0, viewModel.uiState.monthlyReportList.size)
        assertFalse(viewModel.uiState.isLoading)
        assertNull(viewModel.uiState.error)
    }

    @Test
    fun testOnCreateSuccessfullyLoadsData() = runTest(testDispatcher) {
        // 准备测试数据
        val mockReports = listOf(
            MonthlyReportItem(
                permalink = "https://example.com/post1",
                publishDate = "2024-01-01T00:00:00Z",
                tags = listOf("技术月报"),
                title = "January 2024 Report"
            ),
            MonthlyReportItem(
                permalink = "https://example.com/post2",
                publishDate = "2024-02-01T00:00:00Z",
                tags = listOf("技术月报"),
                title = "February 2024 Report"
            )
        )
        fakeRepository.mockData = mockReports

        // 初始状态验证
        assertFalse(viewModel.uiState.isLoading)
        assertEquals(0, viewModel.uiState.monthlyReportList.size)

        // 执行
        viewModel.onCreate()

        // 等待异步任务完成
        advanceUntilIdle()

        // 验证最终状态
        assertEquals(2, viewModel.uiState.monthlyReportList.size)
        assertEquals("January 2024 Report", viewModel.uiState.monthlyReportList[0].title)
        assertEquals("February 2024 Report", viewModel.uiState.monthlyReportList[1].title)
        assertFalse(viewModel.uiState.isLoading)
        assertNull(viewModel.uiState.error)
        assertEquals(1, fakeRepository.fetchCallCount)
    }

    @Test
    fun testOnCreateHandlesEmptyData() = runTest(testDispatcher) {
        // 准备空数据
        fakeRepository.mockData = emptyList()

        // 执行
        viewModel.onCreate()
        advanceUntilIdle()

        // 验证
        assertTrue(viewModel.uiState.monthlyReportList.isEmpty())
        assertFalse(viewModel.uiState.isLoading)
        assertNull(viewModel.uiState.error)
    }

    @Test
    fun testOnCreateHandlesNetworkError() = runTest(testDispatcher) {
        // 配置 Repository 抛出异常
        fakeRepository.shouldThrowError = true
        fakeRepository.errorMessage = "Network connection failed"

        // 执行
        viewModel.onCreate()
        advanceUntilIdle()

        // 验证错误状态
        assertTrue(viewModel.uiState.monthlyReportList.isEmpty())
        assertFalse(viewModel.uiState.isLoading)
        assertNotNull(viewModel.uiState.error)
        assertEquals("Network connection failed", viewModel.uiState.error)
    }

    @Test
    fun testMultipleOnCreateCalls() = runTest(testDispatcher) {
        fakeRepository.mockData = listOf(
            MonthlyReportItem(
                permalink = "https://example.com/post1",
                publishDate = "2024-01-01T00:00:00Z",
                tags = listOf("技术月报"),
                title = "Report 1"
            )
        )

        // 第一次调用
        viewModel.onCreate()
        advanceUntilIdle()
        assertEquals(1, fakeRepository.fetchCallCount)

        // 第二次调用
        viewModel.onCreate()
        advanceUntilIdle()
        assertEquals(2, fakeRepository.fetchCallCount)
    }

    @Test
    fun testOnDestroyCanBeCalledWithoutCrashing() {
        // 验证 onDestroy 可以安全调用
        viewModel.onDestroy()
        // 隐式验证：没有抛出异常
    }

    // 注意：数据模型测试已移至 MonthlyReportItemTest
    // 注意：过滤逻辑测试已移至 MonthlyReportRepositoryImplTest

    @Test
    fun testUIStateStructure() {
        // 测试 UIState 数据类的创建和属性
        val testReports = listOf(
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

        val uiState = MonthlyReportUIState(
            monthlyReportList = testReports,
            isLoading = true,
            error = "Test error"
        )

        assertEquals(2, uiState.monthlyReportList.size)
        assertEquals("Test Report 1", uiState.monthlyReportList[0].title)
        assertEquals("Test Report 2", uiState.monthlyReportList[1].title)
        assertTrue(uiState.isLoading)
        assertEquals("Test error", uiState.error)
    }

    @Test
    fun testUIStateCopy() {
        // 测试 UIState 的 copy 功能
        val initialState = MonthlyReportUIState()
        val newReports = listOf(
            MonthlyReportItem(
                permalink = "https://example.com/post1",
                publishDate = "2024-01-01T00:00:00Z",
                tags = listOf("技术月报"),
                title = "Test Report"
            )
        )

        val updatedState = initialState.copy(monthlyReportList = newReports)

        assertEquals(1, updatedState.monthlyReportList.size)
        assertFalse(updatedState.isLoading)
        assertNull(updatedState.error)
    }

    @Test
    fun testLoadingStateAfterCompletion() = runTest(testDispatcher) {
        // 测试加载完成后的状态
        fakeRepository.mockData = listOf(
            MonthlyReportItem(
                permalink = "https://example.com/post1",
                publishDate = "2024-01-01T00:00:00Z",
                tags = listOf("技术月报"),
                title = "Report"
            )
        )

        // 初始状态
        assertFalse(viewModel.uiState.isLoading)

        // 开始加载
        viewModel.onCreate()

        // 加载完成
        advanceUntilIdle()
        assertFalse(viewModel.uiState.isLoading)
        assertEquals(1, viewModel.uiState.monthlyReportList.size)
    }
}
