package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.data.Episode

interface MediaPlaybackController {
    fun seekTo(positionMs: Long)

    fun previous()

    fun next()

    fun play()

    fun pause()

    fun prepare(musics: List<Episode>, index: Int, positionMs: Long)

    fun playMusics(musics: List<Episode>, startIndex: Int = 0)

    fun stop()

    fun release()
}