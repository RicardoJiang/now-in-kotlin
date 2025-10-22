package com.jiang.nowinkotlin.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.components.SmallIconButton
import com.jiang.nowinkotlin.navigation.LocalNavigator
import com.jiang.nowinkotlin.navigation.Screen

data class VideoPlayerScreen(
    val video: Video
) : Screen {
    @Composable
    override fun Content() {
        // 获取当前 CompositionLocal 中的 navigator 实例
        val navigator = LocalNavigator.current

        VideoPlayerScreen(
            video = video,
            onBack = {
                // 当返回按钮被点击时，调用 navigator pop
                navigator?.pop()
            }
        )
    }
}

@Composable
fun VideoPlayerScreen(video: Video, onBack: () -> Unit) {

    // 创建视频播放器控制器 - 使用 video.url 作为 key 确保只在 URL 改变时重新创建
    val controller = rememberVideoPlayerController(videoUrl = video.url)
    val isBuffering by controller.isBuffering

    // 延迟设置屏幕方向，让视频播放器先初始化
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100) // 延迟 100ms
        setScreenOrientation(ScreenOrientation.LANDSCAPE)
    }

    DisposableEffect(Unit) {
        onDispose {
            setScreenOrientation(ScreenOrientation.UNSPECIFIED)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // 视频播放视图（使用平台特定的 expect/actual 实现）
        PlatformVideoPlayerView(
            controller = controller,
            modifier = Modifier.fillMaxSize()
        )

        // 顶部返回按钮区域（带渐变背景）
        SmallIconButton(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = onBack,
            contentDescription = "返回",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // 自定义播放控制 UI（跨平台共享）
        VideoPlayerControls(
            controller = controller,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )

        // 加载指示器
        if (isBuffering) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

/**
 * 平台特定的视频播放视图
 * 由各平台实现具体的渲染逻辑
 */
@Composable
expect fun PlatformVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier = Modifier
)
