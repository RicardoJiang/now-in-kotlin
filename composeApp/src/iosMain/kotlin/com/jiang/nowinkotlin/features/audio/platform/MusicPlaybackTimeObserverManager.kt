package com.jiang.nowinkotlin.features.audio.platform

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.addPeriodicTimeObserverForInterval
import platform.AVFoundation.removeTimeObserver
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.darwin.NSEC_PER_SEC

@OptIn(ExperimentalForeignApi::class)
class MusicPlaybackTimeObserverManager(private val player: AVPlayer) {
    private var periodicTimeObserver: Any? = null

    fun setup(onTimeUpdated: () -> Unit) {
        if (periodicTimeObserver != null) return

        val interval = CMTimeMakeWithSeconds(0.4, NSEC_PER_SEC.toInt())
        periodicTimeObserver = player.addPeriodicTimeObserverForInterval(interval, null) { _: CValue<CMTime> ->
            onTimeUpdated()
        }
    }

    fun cleanup() {
        periodicTimeObserver?.let {
            player.removeTimeObserver(it)
            periodicTimeObserver = null
        }
    }
}

