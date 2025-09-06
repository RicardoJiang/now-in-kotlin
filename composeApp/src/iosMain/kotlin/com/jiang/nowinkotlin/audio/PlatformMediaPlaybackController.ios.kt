@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.PlatformContext
import com.jiang.nowinkotlin.data.Episode
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import platform.AVFoundation.AVKeyValueStatusLoaded
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.AVURLAssetPreferPreciseDurationAndTimingKey
import platform.AVFoundation.currentItem
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
internal class PlatformMediaPlaybackController(
    private val playbackStateManager: PlaybackStateManager
) : MediaPlaybackController {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val playerStateManager = IosPlayerStateManager(scope)
    private val playlistManager = PlaylistManager()
    private val audioSessionManager = AudioSessionManager()
    private val eventManager = PlaybackStateObserverManager(
        player = playerStateManager.player,
        onPlaybackStateChanged = ::updatePlaybackState,
        coroutineScope = scope
    )
    private val controlCenterManager = ControlCenterManager(
        commandCenter = MediaCommandCenter(
            commandHandler = this.getMediaControlHandler()
        ),
        infoCenter = MediaInfoCenter(
            player = playerStateManager.player,
            coroutineScope = scope
        )
    )


    private var isLoading = false
    private var initialized = false

    override fun prepare(musics: List<Episode>, index: Int, positionMs: Long) {
        if (initialized == false) {
            initController()
        }

        playlistManager.updatePlaylist(musics, index)
        playerStateManager.initializePlayer(positionMs)
        playlistManager.getCurrentMusic()?.let { setMusic(it, false) }
    }

    override fun playMusics(musics: List<Episode>, startIndex: Int) {
        if (initialized == false) {
            initController()
        }

        playlistManager.updatePlaylist(musics, startIndex)
        playerStateManager.initializePlayer(0)
        playlistManager.getCurrentMusic()?.let { setMusic(it, true) }
    }

    private fun setMusic(music: Episode, playImmediately: Boolean = true) {
        scope.launch {
            prepareMusic()
            playMusic(music, playImmediately)
        }
    }

    private fun prepareMusic() {
        isLoading = true
        updatePlaybackState()
        cleanupCurrentItem()
    }

    private fun cleanupCurrentItem() {
        playerStateManager.player.replaceCurrentItemWithPlayerItem(null)
        playerStateManager.player.currentItem?.let {
            playerStateManager.cleanup()
        }
    }

    private fun playMusic(music: Episode, playImmediately: Boolean) {
        val nsUrl = NSURL(string = music.audioUrl)
        val streamingAsset = createStreamingAsset(nsUrl)

        prepareAndPlay(streamingAsset, music, playImmediately)

    }

    private fun createStreamingAsset(nsUrl: NSURL) = AVURLAsset(
        nsUrl, mapOf(AVURLAssetPreferPreciseDurationAndTimingKey to true)
    )

    private fun prepareAndPlay(
        asset: AVURLAsset,
        music: Episode,
        playImmediately: Boolean,
    ) {
        asset.loadValuesAsynchronouslyForKeys(keys = listOf("playable")) {
            if (asset.statusOfValueForKey("playable", null) == AVKeyValueStatusLoaded) {
                val newItem = AVPlayerItem(asset)

                playerStateManager.player.replaceCurrentItemWithPlayerItem(newItem)
                updatePlaybackState()

                playerStateManager.setupMusicStatusObserver(
                    item = newItem,
                    onReadyToPlay = {
                        isLoading = false
                        if (playImmediately) {
                            playerStateManager.play()
                        }
                    },
                    onPlaybackStateChanged = ::updatePlaybackState
                )
            } else {
                next()
            }
        }
    }

    private fun onMusicCompleted() {
        playlistManager.getNextIndex()?.let { nextIndex ->
            playlistManager.setCurrentIndex(nextIndex)
            playlistManager.getCurrentMusic()?.let { setMusic(it) }
        }
    }

    override fun play() = playerStateManager.play()
    override fun pause() = playerStateManager.pause()
    override fun seekTo(positionMs: Long) = playerStateManager.setPosition(positionMs)

    override fun previous() {
        val currentPlayingTime = playerStateManager.getCurrentPosition()

        when {
            currentPlayingTime > 5 -> seekTo(0)
            else -> playlistManager.getPreviousIndex()?.let {
                playlistManager.setCurrentIndex(it)
                playlistManager.getCurrentMusic()?.let { music -> setMusic(music) }
            } ?: seekTo(0)
        }
    }

    override fun next() {
        playlistManager.getNextIndex()?.let { nextIndex ->

            playlistManager.setCurrentIndex(nextIndex)
            playlistManager.getCurrentMusic()?.let { setMusic(it) }
        }
    }

    override fun skipTo(musicIndex: Int) {
        if (musicIndex != playlistManager.currentIndex) {
            playlistManager.setCurrentIndex(musicIndex)
            playlistManager.getCurrentMusic()?.let { setMusic(it) }
        }
    }

    override fun stop() {
        releaseController()
        updatePlaybackState()
    }

    private fun initController() {
        audioSessionManager.setupAudioSession()
        controlCenterManager.start()
        eventManager.startObserving()
        playerStateManager.setupPlaybackTimeObserver { updatePlaybackState() }
        playerStateManager.setupMusicCompleteTimeObserver(
            onMusicCompleted = ::onMusicCompleted
        )
        initialized = true
    }

    private fun releaseController() {
        playerStateManager.stop()
        playlistManager.clear()
        eventManager.stopObserving()
        controlCenterManager.release()
        initialized = false
    }

    override fun release() {
        stop()
    }

    private fun updatePlaybackState() {
        val currentSeconds = playerStateManager.getCurrentPosition()
        val durationSeconds = playerStateManager.getDuration()
        val state = PlaybackState(
            episode = playlistManager.getCurrentMusic(),
            playingStatus = if (isLoading) PlayingStatus.BUFFERING else playerStateManager.currentPlaybackStatus,
            currentIndex = playlistManager.currentIndex,
            hasPrevious = playlistManager.hasPrevious(),
            hasNext = playlistManager.hasNext(),
            position = currentSeconds * 1000,
            duration = durationSeconds * 1000,
        )
        playbackStateManager.playbackState = state

        playlistManager.getCurrentMusic()?.let { music ->
            controlCenterManager.updatePlaybackState(music, state)
        }
    }
}

internal actual fun createKmpAudioPlayer(context: PlatformContext): MediaPlaybackController {
    return PlatformMediaPlaybackController(PlaybackStateManager)
}


private fun PlatformMediaPlaybackController.getMediaControlHandler(): MediaCommandHandler =
    object : MediaCommandHandler {
        override fun onPlay() {
            play()
        }

        override fun onPause() {
            pause()
        }

        override fun onNext() {
            next()
        }

        override fun onPrevious() {
            previous()
        }

        override fun onSeek(positionMs: Long) {
            seekTo(positionMs)
        }
    }

