package com.jiang.nowinkotlin.features.audio.platform

import com.jiang.nowinkotlin.features.audio.player.PlaybackState
import com.jiang.nowinkotlin.shared.data.Episode

internal class ControlCenterManager(
    private val commandCenter: MediaCommandCenter,
    private val infoCenter: MediaInfoCenter
) {
    fun start() {
        commandCenter.setupCommands()
        infoCenter.setupInitInfo()
    }

    fun updatePlaybackState(music: Episode, playbackState: PlaybackState) = infoCenter.updateInfo(music ,playbackState)

    fun release() {
        commandCenter.cleanup()
        infoCenter.cleanup()
    }
}