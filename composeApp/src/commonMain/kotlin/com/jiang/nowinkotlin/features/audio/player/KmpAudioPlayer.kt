package com.jiang.nowinkotlin.features.audio.player

import com.jiang.nowinkotlin.core.platform.PlatformContext
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