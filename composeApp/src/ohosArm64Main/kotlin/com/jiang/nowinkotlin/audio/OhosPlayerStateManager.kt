package com.jiang.nowinkotlin.audio

import kotlinx.cinterop.ExperimentalForeignApi


@OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)
class OhosPlayerStateManager() {
    private val player by lazy {
        println("here getOhosAVPlayerApi")
        getOhosAVPlayerApi()
    }
//    private val playbackTimeObserverManager = MusicPlaybackTimeObserverManager(player)
//    private val musicCompleteTimeObserverManager = MusicCompleteTimeObserverManager(player)


    fun getCurrentPosition(): Long = player.currentTime()
    fun getDuration(): Long = player.duration()

    fun initializePlayer(positionMs: Long) {
        player.pause()
        player.seekTo(positionMs)
    }

    fun play() = player.play()
    fun pause() = player.pause()

    fun stop() {
        player.pause()
//        playbackTimeObserverManager.cleanup()
//        musicCompleteTimeObserverManager.cleanup()
    }

    fun setPosition(positionMs: Long) {
        player.seekTo(positionMs)
    }

    fun prepareAndPlay(
        index: Int,
        title: String,
        audioUrl: String,
        imageUrl: String,
        playImmediately: Boolean
    ) {
        player.prepareAndPlay(index, title, audioUrl, imageUrl, playImmediately)
    }

//    fun setupPlaybackTimeObserver(onTimeUpdated: () -> Unit) {
//        playbackTimeObserverManager.setup(onTimeUpdated)
//    }
//
//    fun setupMusicCompleteTimeObserver(onMusicCompleted: () -> Unit) {
//        musicCompleteTimeObserverManager.setup(onMusicCompleted)
//    }


    fun cleanup() {
//        musicCompleteTimeObserverManager.cleanup()
    }
}