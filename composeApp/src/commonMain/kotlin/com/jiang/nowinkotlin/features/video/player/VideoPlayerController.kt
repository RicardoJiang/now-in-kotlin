package com.jiang.nowinkotlin.features.video.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * 视频播放器控制器接口
 * 提供跨平台的视频播放控制能力
 */
interface VideoPlayerController {
    /** 是否正在播放 */
    val isPlaying: State<Boolean>

    /** 当前播放进度 (0.0 - 1.0) */
    val progress: State<Float>

    /** 当前播放时间（毫秒） */
    val currentPosition: State<Long>

    /** 视频总时长（毫秒） */
    val duration: State<Long>

    /** 是否正在缓冲 */
    val isBuffering: State<Boolean>

    /** 播放/暂停 */
    fun playPause()

    /** 跳转到指定位置 */
    fun seekTo(position: Long)

    /** 跳转到指定进度 (0.0 - 1.0) */
    fun seekToProgress(progress: Float)

    /** 释放资源 */
    fun release()
}

/**
 * 创建平台特定的视频播放器控制器
 */
@Composable
expect fun rememberVideoPlayerController(videoUrl: String): VideoPlayerController
