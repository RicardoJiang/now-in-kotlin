package com.jiang.nowinkotlin.audio

import com.tencent.tmm.knoi.annotation.ServiceConsumer
import com.tencent.tmm.knoi.annotation.ServiceProvider

@ServiceConsumer
interface OhosAVPlayer {
    fun play()

    fun pause()

    fun seekTo(positionMs: Long)

    fun prepareAndPlay(
        index: Int,
        title: String,
        audioUrl: String,
        imageUrl: String,
        playImmediately: Boolean
    )

    fun currentTime(): Long

    fun duration(): Long
}

@ServiceProvider(singleton = true)
open class OhosStateListener {
    fun updatePlaybackState(isPlaying: Boolean, positionMs: Long, duration: Long) {
        val state = PlaybackState(
            episode = PlaylistManager.getCurrentMusic(),
            playingStatus = if (isPlaying) PlayingStatus.PLAYING else PlayingStatus.PAUSED,
            currentIndex = PlaylistManager.currentIndex,
            hasPrevious = PlaylistManager.hasPrevious(),
            hasNext = PlaylistManager.hasNext(),
            position = positionMs,
            duration = duration,
        )
        PlaybackStateManager.playbackState = state
    }
}

@ServiceProvider(singleton = true)
open class MediaCommandHandler {
    fun onNext() {
        KmpAudioPlayer.playbackController.next()
    }

    fun onPrevious() {
        KmpAudioPlayer.playbackController.previous()
    }
}