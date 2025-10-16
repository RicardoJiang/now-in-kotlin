package com.jiang.nowinkotlin.video

import androidx.compose.runtime.*
import com.jiang.nowinkotlin.OhosVideoAVPlayer
import com.tencent.tmm.knoi.definder.getService
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

/**
 * iOS 平台的视频播放器控制器实现
 * 使用 AVPlayer
 */
@OptIn(ExperimentalForeignApi::class)
class OHOSVideoPlayerController(
    val videoUrl: String,
    val player: OhosVideoAVPlayer
) : VideoPlayerController {

    private val _isPlaying = mutableStateOf(false)
    override val isPlaying: State<Boolean> = _isPlaying

    private val _progress = mutableStateOf(0f)
    override val progress: State<Float> = _progress

    private val _currentPosition = mutableStateOf(0L)
    override val currentPosition: State<Long> = _currentPosition

    private val _duration = mutableStateOf(0L)
    override val duration: State<Long> = _duration

    private val _isBuffering = mutableStateOf(true)
    override val isBuffering: State<Boolean> = _isBuffering

    fun updateProgress() {
        val currentTime = player.currentTime()
        val durationTime = player.duration()

        if (durationTime > 0) {
            _currentPosition.value = currentTime
            _duration.value = durationTime
            _progress.value = (currentTime * 1.0f / durationTime)
        }

        // 更新播放状态
        _isPlaying.value = player.isPlaying()

        // 更新缓冲状态
        _isBuffering.value = player.isBuffering()
    }

    override fun playPause() {
        if (player.isPlaying()) {
            player.pause()
            _isPlaying.value = false
        } else {
            player.play()
            _isPlaying.value = true
        }
    }

    override fun seekTo(position: Long) {
        player.seekTo(position)
    }

    override fun seekToProgress(progress: Float) {
        val durationTime = player.duration()
        if (durationTime > 0) {
            val position = (durationTime * progress).toLong()
            seekTo(position)
        }
    }

    override fun release() {
        player.release()
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberVideoPlayerController(videoUrl: String): VideoPlayerController {

    val player: OhosVideoAVPlayer = remember {
        getService("OhosVideoAVPlayer")
    }

    val controller = remember { OHOSVideoPlayerController(videoUrl, player) }

    // 定期更新进度
    LaunchedEffect(Unit) {
        while (isActive) {
            controller.updateProgress()
            delay(500) // 每 500ms 更新一次
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            controller.release()
        }
    }

    return controller
}
