package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.PlatformContext
import com.jiang.nowinkotlin.data.Episode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class PlatformMediaPlaybackController : MediaPlaybackController {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val playerStateManager by lazy {
        OhosPlayerStateManager()
    }

    override fun seekTo(positionMs: Long) {
        playerStateManager.setPosition(positionMs)
    }

    override fun previous() {
        val currentPlayingTime = playerStateManager.getCurrentPosition()

        when {
            currentPlayingTime > 5000 -> seekTo(0)
            else -> PlaylistManager.getPreviousIndex()?.let {
                PlaylistManager.setCurrentIndex(it)
                PlaylistManager.getCurrentMusic()?.let { music -> setMusic(music) }
            } ?: seekTo(0)
        }
    }

    private fun setMusic(music: Episode, playImmediately: Boolean = true) {
        playMusic(music, playImmediately)
    }

    override fun next() {
        PlaylistManager.getNextIndex()?.let { nextIndex ->
            PlaylistManager.setCurrentIndex(nextIndex)
            PlaylistManager.getCurrentMusic()?.let { setMusic(it) }
        }
    }

    override fun play() {
        playerStateManager.play()
    }

    override fun pause() {
        playerStateManager.pause()
    }

    override fun skipTo(musicIndex: Int) {
        if (musicIndex != PlaylistManager.currentIndex) {
            PlaylistManager.setCurrentIndex(musicIndex)
            PlaylistManager.getCurrentMusic()?.let { setMusic(it) }
        }
    }

    private var initialized = false

    override fun prepare(
        musics: List<Episode>,
        index: Int,
        positionMs: Long
    ) {
        if (initialized == false) {
            initController()
        }

        PlaylistManager.updatePlaylist(musics, index)
        playerStateManager.initializePlayer(positionMs)
        PlaylistManager.getCurrentMusic()?.let { setMusic(it, false) }
    }

    private fun initController() {
//        audioSessionManager.setupAudioSession()
//        controlCenterManager.start()
//        eventManager.startObserving()
//        playerStateManager.setupPlaybackTimeObserver { updatePlaybackState() }
//        playerStateManager.setupMusicCompleteTimeObserver(
//            onMusicCompleted = ::onMusicCompleted
//        )
        initialized = true
    }

    override fun stop() {
        playerStateManager.stop()
    }

    override fun release() {
        stop()
    }

    private fun playMusic(music: Episode, playImmediately: Boolean) {
        playerStateManager.prepareAndPlay(
            music.index,
            music.title,
            music.audioUrl,
            music.imageUrl,
            playImmediately
        )

    }
}

internal actual fun createKmpAudioPlayer(context: PlatformContext): MediaPlaybackController {
    return PlatformMediaPlaybackController()
}
