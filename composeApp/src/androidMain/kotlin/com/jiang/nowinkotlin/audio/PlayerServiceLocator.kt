package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.data.Episode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object PlayerServiceLocator {

    internal var episodeList = listOf<Episode>()
    private val coroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

   internal val playbackStateHandler by lazy {
        PlaybackStateHandler(
            coroutineScope,
            PlaybackStateManager,
            Dispatchers.Main
        )
    }

    internal val sessionCallback by lazy {
        LibrarySessionCallback()
    }
}