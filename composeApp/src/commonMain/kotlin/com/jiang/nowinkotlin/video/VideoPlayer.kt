package com.jiang.nowinkotlin.video

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayerView(
    videoUrl: String,
    modifier: Modifier = Modifier,
    onLoadingChanged: (Boolean) -> Unit = {}
)

expect fun setScreenOrientation(orientation: ScreenOrientation)

enum class ScreenOrientation {
    PORTRAIT,
    LANDSCAPE,
    UNSPECIFIED
}
