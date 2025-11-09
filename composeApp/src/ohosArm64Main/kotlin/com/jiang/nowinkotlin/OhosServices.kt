package com.jiang.nowinkotlin

import com.tencent.tmm.knoi.annotation.KNCallback
import com.tencent.tmm.knoi.annotation.KNExport
import com.tencent.tmm.knoi.annotation.ServiceConsumer
import com.tencent.tmm.knoi.type.JSValue

@ServiceConsumer
interface OhosServices {
    fun parseJson(json: String): Map<String, Any?>
}

@ServiceConsumer
interface SetScreenOrientation {
    fun setOrientation(isLandscape: Boolean)
}

@ServiceConsumer
interface OhosVideoAVPlayerFactory {
    fun createOhosVideoAVPlayer(): JSValue
}

@KNCallback
interface OhosVideoAVPlayer {
    fun setUpVideo(surfaceId: String, url: String)
    fun isPlaying(): Boolean
    fun isBuffering(): Boolean
    fun currentTime(): Long
    fun duration(): Long
    fun pause()
    fun play()
    fun seekTo(position: Long)
    fun release()
}