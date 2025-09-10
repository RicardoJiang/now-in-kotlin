package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.PlatformContext
import com.jiang.nowinkotlin.data.Episode

interface MediaPlaybackController {
    fun seekTo(positionMs: Long)

    fun previous()

    fun next()

    fun play()

    fun pause()

    fun skipTo(musicIndex: Int, playImmediately: Boolean)

    fun prepare(musics: List<Episode>, index: Int, positionMs: Long)

    fun stop()

    fun release()
}

internal expect fun createKmpAudioPlayer(context: PlatformContext): MediaPlaybackController