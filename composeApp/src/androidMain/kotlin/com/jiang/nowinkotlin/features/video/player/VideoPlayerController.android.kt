package com.jiang.nowinkotlin.features.video.player

import com.jiang.nowinkotlin.features.video.player.VideoPlayerController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

/**
 * Android 平台的视频播放器控制器实现
 * 使用 ExoPlayer
 */
class AndroidVideoPlayerController(
    val player: ExoPlayer
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

    init {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                _isBuffering.value = playbackState == Player.STATE_BUFFERING
            }
        })
    }

    fun updateProgress() {
        val currentPos = player.currentPosition
        val dur = player.duration

        _currentPosition.value = currentPos
        if (dur > 0) {
            _duration.value = dur
            _progress.value = currentPos.toFloat() / dur.toFloat()
        }
    }

    override fun playPause() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.play()
        }
    }

    override fun seekTo(position: Long) {
        player.seekTo(position)
    }

    override fun seekToProgress(progress: Float) {
        val duration = player.duration
        if (duration > 0) {
            val position = (duration * progress).toLong()
            player.seekTo(position)
        }
    }

    override fun release() {
        player.release()
    }
}

@Composable
actual fun rememberVideoPlayerController(videoUrl: String): VideoPlayerController {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            prepare()
            playWhenReady = true
        }
    }

    val controller = remember { AndroidVideoPlayerController(player) }

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
