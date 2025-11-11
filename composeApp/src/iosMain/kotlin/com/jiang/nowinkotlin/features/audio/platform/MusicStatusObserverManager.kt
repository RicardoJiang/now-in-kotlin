package com.jiang.nowinkotlin.features.audio.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemStatus
import platform.AVFoundation.AVPlayerStatusFailed
import platform.AVFoundation.AVPlayerStatusReadyToPlay
import platform.AVFoundation.AVPlayerStatusUnknown
import platform.Foundation.NSKeyValueObservingOptionInitial
import platform.Foundation.NSKeyValueObservingOptionNew

// 음악 아이템 상태 감시
class MusicStatusObserverManager(
    private val coroutineScope: CoroutineScope
) {
    private var currentItemStatusJob: Job? = null

    fun observeItemStatus(item: AVPlayerItem, onReadyToPlay: () -> Unit, onPlaybackStateChanged: () -> Unit) {
        currentItemStatusJob?.cancel()

        currentItemStatusJob = coroutineScope.launch {
            item.observeKeyValueAsFlow<AVPlayerItemStatus>(
                "status",
                NSKeyValueObservingOptionInitial or NSKeyValueObservingOptionNew
            ).collect { status ->
                when (status) {
                    AVPlayerStatusUnknown -> {
                    }
                    AVPlayerStatusReadyToPlay -> {
                        onReadyToPlay()
                    }
                    AVPlayerStatusFailed -> {
                    }
                }
                onPlaybackStateChanged()
            }
        }
    }

    fun cleanup() {
        currentItemStatusJob?.cancel()
        currentItemStatusJob = null
    }
}