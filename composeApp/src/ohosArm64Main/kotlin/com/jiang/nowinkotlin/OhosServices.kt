package com.jiang.nowinkotlin

import com.tencent.tmm.knoi.annotation.ServiceConsumer

@ServiceConsumer
interface OhosServices {
    fun parseJson(json: String): Map<String, Any?>
}

@ServiceConsumer
interface SetScreenOrientation {
    fun setOrientation(isLandscape: Boolean)
}

@ServiceConsumer
interface OhosVideoAVPlayer {
    fun isPlaying(): Boolean
    fun isBuffering(): Boolean
    fun currentTime(): Long
    fun duration(): Long
    fun pause()
    fun play()
    fun seekTo(position: Long)
    fun release()
}