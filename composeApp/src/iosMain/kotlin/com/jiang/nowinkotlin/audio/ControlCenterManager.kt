package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.data.Episode

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