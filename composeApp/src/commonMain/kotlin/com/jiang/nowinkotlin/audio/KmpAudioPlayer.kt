package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.PlatformContext
import kotlinx.coroutines.flow.StateFlow

object KmpAudioPlayer {

    private lateinit var appContext: PlatformContext

    fun init(context: PlatformContext) {
        if (!::appContext.isInitialized) {
            appContext = context
        }
    }

    val playbackController: MediaPlaybackController by lazy {
        createKmpAudioPlayer(appContext)
    }
    val playbackState: StateFlow<PlaybackState> = PlaybackStateManager.flow
}