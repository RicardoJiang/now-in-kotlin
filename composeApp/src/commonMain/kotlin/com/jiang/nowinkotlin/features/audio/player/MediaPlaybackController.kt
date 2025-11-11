package com.jiang.nowinkotlin.features.audio.player

import com.jiang.nowinkotlin.core.platform.PlatformContext
import com.jiang.nowinkotlin.shared.data.Episode

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