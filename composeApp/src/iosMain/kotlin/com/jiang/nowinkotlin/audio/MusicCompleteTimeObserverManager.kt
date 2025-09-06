package com.jiang.nowinkotlin.audio

import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.currentItem
import platform.AVFoundation.removeTimeObserver
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue.Companion.mainQueue

class MusicCompleteTimeObserverManager(private val player: AVPlayer) {
    private var observer: Any? = null

    fun setup(onMusicCompleted: () -> Unit) {
        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = player.currentItem,
            queue = mainQueue
        ) {
            onMusicCompleted()
        }
    }

    fun cleanup() {
        observer?.let {
            player.removeTimeObserver(it)
            observer = null
        }
    }
}