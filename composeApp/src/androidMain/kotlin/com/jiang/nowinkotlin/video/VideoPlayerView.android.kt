package com.jiang.nowinkotlin.video

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView

// Global reference to activity for orientation changes
private var currentActivity: ComponentActivity? = null

fun setActivity(activity: ComponentActivity) {
    currentActivity = activity
}

fun clearActivity() {
    currentActivity = null
}

actual fun setScreenOrientation(orientation: ScreenOrientation) {
    currentActivity?.requestedOrientation = when (orientation) {
        ScreenOrientation.PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        ScreenOrientation.LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        ScreenOrientation.UNSPECIFIED -> ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}

/**
 * Android 平台的视频播放视图（不带控制器）
 */
@Composable
actual fun PlatformVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier
) {
    val androidController = controller as AndroidVideoPlayerController

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = androidController.player
                useController = false // 禁用原生控制器，使用我们的自定义控制器
            }
        },
        modifier = modifier
    )
}

@Composable
fun AndroidVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier = Modifier
) {
    PlatformVideoPlayerView(controller, modifier)
}

// 为了保持兼容性，保留旧的接口
@Composable
actual fun VideoPlayerView(
    videoUrl: String,
    modifier: Modifier,
    onLoadingChanged: (Boolean) -> Unit
) {
    val controller = rememberVideoPlayerController(videoUrl)

    DisposableEffect(controller) {
        onDispose {
            // onLoadingChanged will be handled by the controller's isBuffering state
        }
    }

    AndroidVideoPlayerView(controller = controller, modifier = modifier)
}
