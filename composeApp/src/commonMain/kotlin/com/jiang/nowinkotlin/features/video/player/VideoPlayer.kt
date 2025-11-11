package com.jiang.nowinkotlin.features.video.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Import VideoPlayerController since it's used in PlatformVideoPlayerView
// Note: It's in the same package so technically not needed, but good for clarity

@Composable
expect fun VideoPlayerView(
    videoUrl: String,
    modifier: Modifier = Modifier,
    onLoadingChanged: (Boolean) -> Unit = {}
)

expect fun setScreenOrientation(orientation: ScreenOrientation)

expect fun setSystemBarsVisibility(visible: Boolean)

enum class ScreenOrientation {
    PORTRAIT,
    LANDSCAPE,
    UNSPECIFIED
}

@Composable
expect fun PlatformVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier = Modifier
)
