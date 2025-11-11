package com.jiang.nowinkotlin.features.audio.platform

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerTimeControlStatusPaused
import platform.AVFoundation.AVPlayerTimeControlStatusPlaying
import platform.AVFoundation.AVPlayerTimeControlStatusWaitingToPlayAtSpecifiedRate
import platform.AVFoundation.reasonForWaitingToPlay
import platform.AVFoundation.timeControlStatus
import platform.Foundation.NSKeyValueObservingOptionNew
import platform.Foundation.addObserver
import platform.Foundation.removeObserver
import platform.darwin.NSObject
import platform.foundation.NSKeyValueObservingProtocol

@OptIn(ExperimentalForeignApi::class)
class PlaybackStateObserverManager(
    private val player: AVPlayer,
    private val coroutineScope: CoroutineScope,
    private val onPlaybackStateChanged: () -> Unit
) {
    private var timeControlStatusObserver = createTimeControlStatusObserver()

    private fun createTimeControlStatusObserver() = object : NSObject(), NSKeyValueObservingProtocol {
        override fun observeValueForKeyPath(
            keyPath: String?,
            ofObject: Any?,
            change: Map<Any?, *>?,
            context: COpaquePointer?
        ) {
            when (player.timeControlStatus) {
                AVPlayerTimeControlStatusWaitingToPlayAtSpecifiedRate -> {

                }
                AVPlayerTimeControlStatusPlaying -> {

                }
                AVPlayerTimeControlStatusPaused -> {

                }
            }
            onPlaybackStateChanged()
        }
    }

    fun startObserving() {
        player.addObserver(
            observer = timeControlStatusObserver,
            forKeyPath = "timeControlStatus",
            options = NSKeyValueObservingOptionNew,
            context = null
        )
    }

    fun stopObserving() {
        player.removeObserver(
            observer = timeControlStatusObserver,
            forKeyPath = "timeControlStatus"
        )
    }
}