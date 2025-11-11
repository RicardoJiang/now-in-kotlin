package com.jiang.nowinkotlin.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jiang.nowinkotlin.features.audio.player.KmpAudioPlayer
import com.jiang.nowinkotlin.features.audio.ui.AudioPlayerScreen
import com.jiang.nowinkotlin.features.home.ui.MainScreen
import com.jiang.nowinkotlin.features.webview.ui.SimpleWebViewScreen
import com.jiang.nowinkotlin.core.navigation.LocalNavigator
import com.jiang.nowinkotlin.core.navigation.Screen
import com.jiang.nowinkotlin.features.video.player.Video
import com.jiang.nowinkotlin.features.video.ui.VideoPlayerScreen

/**
 * 主屏幕的 Screen 对象实现。
 * 这是一个单例对象，因为主屏幕没有参数。
 */
object AppMainScreen : Screen {
    @Composable
    override fun Content() {
        // 获取当前 CompositionLocal 中的 navigator 实例
        val navigator = LocalNavigator.current
        val playbackState by KmpAudioPlayer.playbackState.collectAsState()

        // 调用原始的 MainScreen Composable
        MainScreen(
            onEpisodeClick = { episodes, index ->
                if (playbackState.currentIndex != index || playbackState.isPlaying.not()) {
                    KmpAudioPlayer.playbackController.skipTo(index, true)
                }
                navigator?.push(
                    AudioPlayerScreen(
                        episodes = episodes,
                        initialIndex = index
                    )
                )
            },
            onPlayClick = { episodes, index ->
                val videoIndex = (episodes.size - index)
                navigator?.push(
                    VideoPlayerScreen(
                        Video(
                            index,
                            episodes[index].title,
                            "https://nowinkotlin.top/ep$videoIndex.mp4"
                        )
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
