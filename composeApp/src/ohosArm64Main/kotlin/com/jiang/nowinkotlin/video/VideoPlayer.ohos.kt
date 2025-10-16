package com.jiang.nowinkotlin.video

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.napi.js
import com.jiang.nowinkotlin.ArkUIView
import com.jiang.nowinkotlin.SetScreenOrientation
import com.tencent.tmm.knoi.definder.getService
import kotlinx.cinterop.ExperimentalForeignApi

actual fun setScreenOrientation(orientation: ScreenOrientation) {
    getService<SetScreenOrientation>("SetScreenOrientation").setOrientation(orientation == ScreenOrientation.LANDSCAPE)
}

/**
 * iOS 平台的视频播放视图（不带控制器）
 */
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier
) {
    val ohosController = controller as OHOSVideoPlayerController

    ArkUIView(
        name = "harmonyVideo",
        modifier = modifier,
        parameter = js {
            "url"(ohosController.videoUrl)
        },
    )
}

@Composable
fun OhosVideoPlayerView(
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

    OhosVideoPlayerView(controller = controller, modifier = modifier)
}
