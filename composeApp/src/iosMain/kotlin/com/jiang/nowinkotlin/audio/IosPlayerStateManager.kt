package com.jiang.nowinkotlin.audio

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemStatusFailed
import platform.AVFoundation.AVPlayerTimeControlStatusWaitingToPlayAtSpecifiedRate
import platform.AVFoundation.currentItem
import platform.AVFoundation.currentTime
import platform.AVFoundation.duration
import platform.AVFoundation.isPlaybackBufferEmpty
import platform.AVFoundation.isPlaybackLikelyToKeepUp
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.rate
import platform.AVFoundation.seekToTime
import platform.AVFoundation.timeControlStatus
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeGetSeconds
import platform.CoreMedia.CMTimeMake
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.darwin.NSEC_PER_SEC

@OptIn(ExperimentalForeignApi::class)
fun CValue<CMTime>.toSeconds(): Long = CMTimeGetSeconds(this).toLong()

@OptIn(ExperimentalForeignApi::class)
fun CValue<CMTime>.toMilliSeconds(): Long = (CMTimeGetSeconds(this) * 1000).toLong()

@OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)
class IosPlayerStateManager(coroutineScope: CoroutineScope) {
    val player = AVPlayer()
    private val playbackTimeObserverManager = MusicPlaybackTimeObserverManager(player)
    private val musicCompleteTimeObserverManager = MusicCompleteTimeObserverManager(player)
    private val musicStatusObserverManager = MusicStatusObserverManager(coroutineScope)

    val currentPlaybackStatus: PlayingStatus
        get() = when {
            player.rate > 0 -> PlayingStatus.PLAYING
            player.currentItem == null -> PlayingStatus.IDLE
            player.currentItem?.let { item ->
                !item.isPlaybackLikelyToKeepUp() ||
                        item.isPlaybackBufferEmpty() ||
                        player.timeControlStatus == AVPlayerTimeControlStatusWaitingToPlayAtSpecifiedRate
            } == true -> PlayingStatus.BUFFERING
            player.currentItem?.status == AVPlayerItemStatusFailed -> PlayingStatus.IDLE
            else -> PlayingStatus.PAUSED
        }

    fun getCurrentPosition(): Long = player.currentItem?.currentTime()?.toSeconds() ?: 0
    fun getDuration(): Long = player.currentItem?.duration?.toSeconds() ?: 0

    fun initializePlayer(positionMs: Long) {
        player.pause()
        player.currentItem?.seekToTime(
            CMTimeMakeWithSeconds(positionMs.div(1000.0), NSEC_PER_SEC.toInt())
        )
    }

    fun play() = player.play()
    fun pause() = player.pause()

    fun stop() {
        player.pause()
        playbackTimeObserverManager.cleanup()
        musicStatusObserverManager.cleanup()
        musicCompleteTimeObserverManager.cleanup()
    }

    private var isSeeking = false
    fun setPosition(positionMs: Long) {
        val time = CMTimeMake(value = positionMs, timescale = 1000)
        isSeeking = true
        player.seekToTime(time){
            if (it) {
                isSeeking = false
            }
        }
    }

    fun setSpeed(speed: Float) {
        player.rate = speed
    }

    fun setupPlaybackTimeObserver(onTimeUpdated: () -> Unit) {
        playbackTimeObserverManager.setup {
            if (!isSeeking) {
                onTimeUpdated()
            }
        }
    }

    fun setupMusicCompleteTimeObserver(onMusicCompleted: () -> Unit) {
        musicCompleteTimeObserverManager.setup(onMusicCompleted)
    }

    fun setupMusicStatusObserver(
        item: AVPlayerItem,
        onReadyToPlay: () -> Unit,
        onPlaybackStateChanged: () -> Unit
    ) {
        musicStatusObserverManager.observeItemStatus(item, onReadyToPlay, onPlaybackStateChanged)
    }

    fun cleanup() {
//        musicCompleteTimeObserverManager.cleanup()
        musicStatusObserverManager.cleanup()
        isSeeking = false
    }
}