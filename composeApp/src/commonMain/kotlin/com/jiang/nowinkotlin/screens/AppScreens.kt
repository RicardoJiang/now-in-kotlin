package com.jiang.nowinkotlin.screens

import androidx.compose.runtime.Composable
import com.jiang.nowinkotlin.mainpage.AudioPlayerScreen
import com.jiang.nowinkotlin.mainpage.MainScreen
import com.jiang.nowinkotlin.mainpage.SimpleWebViewScreen
import com.jiang.nowinkotlin.navigation.LocalNavigator
import com.jiang.nowinkotlin.navigation.Screen

/**
 * 主屏幕的 Screen 对象实现。
 * 这是一个单例对象，因为主屏幕没有参数。
 */
object AppMainScreen : Screen {
    @Composable
    override fun Content() {
        // 获取当前 CompositionLocal 中的 navigator 实例
        val navigator = LocalNavigator.current

        // 调用原始的 MainScreen Composable
        MainScreen(
            onEpisodeClick = { episodes, index ->
                navigator?.push(
                    AudioPlayerScreen(
                        episodes = episodes,
                        initialIndex = index
                    )
                )
            },
            onMonthlyReportClick = { report ->
                // 当月报被点击时，使用 navigator push 一个新的 WebViewScreen
                navigator?.push(
                    WebViewScreen(
                        title = report.title,
                        url = report.permalink
                    )
                )
            }
        )
    }
}

/**
 * WebView 屏幕的 Screen 类实现。
 * 这是一个 data class，因为它需要接收 title 和 url 参数。
 */
data class WebViewScreen(val title: String, val url: String) : Screen {
    @Composable
    override fun Content() {
        // 获取当前 CompositionLocal 中的 navigator 实例
        val navigator = LocalNavigator.current

        // 调用原始的 SimpleWebViewScreen Composable
        SimpleWebViewScreen(
            title = title,
            url = url,
            onBackClick = {
                // 当返回按钮被点击时，调用 navigator pop
                navigator?.pop()
            }
        )
    }
}
