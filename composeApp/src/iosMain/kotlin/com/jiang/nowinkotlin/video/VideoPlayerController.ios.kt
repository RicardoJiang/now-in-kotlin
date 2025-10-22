package com.jiang.nowinkotlin.video

import androidx.compose.runtime.*
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.*
import platform.CoreMedia.CMTimeGetSeconds
import platform.Foundation.NSURL
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import platform.CoreMedia.CMTimeMake

/**
 * iOS 平台的视频播放器控制器实现
 * 使用 AVPlayer
 */
@OptIn(ExperimentalForeignApi::class)
class IOSVideoPlayerController(
    val player: AVPlayer
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
        val currentTime = CMTimeGetSeconds(player.currentTime())
        val durationTime = player.currentItem?.duration?.let { CMTimeGetSeconds(it) } ?: 0.0

        if (currentTime.isFinite() && durationTime.isFinite() && durationTime > 0) {
            _currentPosition.value = (currentTime * 1000).toLong()
            _duration.value = (durationTime * 1000).toLong()
            _progress.value = (currentTime / durationTime).toFloat()
        }

        // 更新播放状态
        _isPlaying.value = player.rate > 0f

        // 更新缓冲状态
        val timeControlStatus = player.timeControlStatus
        _isBuffering.value = timeControlStatus == AVPlayerTimeControlStatusWaitingToPlayAtSpecifiedRate
    }

    override fun playPause() {
        if (player.rate > 0f) {
            player.pause()
            _isPlaying.value = false
        } else {
            player.play()
            _isPlaying.value = true
        }
    }

    override fun seekTo(position: Long) {
        val timeInSeconds = position / 1000.0
        val cmTime = CMTimeMake(
            value = (timeInSeconds * 1000).toLong(),
            timescale = 1000
        )
        player.seekToTime(cmTime)
    }

    override fun seekToProgress(progress: Float) {
        val durationTime = player.currentItem?.duration?.let { CMTimeGetSeconds(it) } ?: 0.0
        if (durationTime.isFinite() && durationTime > 0) {
            val position = (durationTime * progress * 1000).toLong()
            seekTo(position)
        }
    }

    override fun release() {
        player.pause()
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberVideoPlayerController(videoUrl: String): VideoPlayerController {
    val player = remember {
        AVPlayer(uRL = NSURL.URLWithString(videoUrl)!!)
    }

    val controller = remember { IOSVideoPlayerController(player) }

    // 自动播放
    LaunchedEffect(player) {
        player.play()
    }

    // 定期更新进度
    LaunchedEffect(player) {
        while (isActive) {
            controller.updateProgress()
            delay(500) // 每 500ms 更新一次
        }
    }

    DisposableEffect(player) {
        onDispose {
            controller.release()
        }
    }

    return controller
}
