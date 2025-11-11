package com.jiang.nowinkotlin.features.audio.platform

import platform.MediaPlayer.MPChangePlaybackPositionCommandEvent
import platform.MediaPlayer.MPRemoteCommandCenter
import platform.MediaPlayer.MPRemoteCommandHandlerStatusSuccess

interface MediaCommandHandler {
    fun onPlay()
    fun onPause()
    fun onNext()
    fun onPrevious()
    fun onSeek(positionMs: Long)
}

internal class MediaCommandCenter(
    private val commandHandler: MediaCommandHandler
) {
    private val commandCenter = MPRemoteCommandCenter.sharedCommandCenter()

    fun setupCommands() {
        // Play Command
        commandCenter.playCommand.enabled = true
        commandCenter.playCommand.addTargetWithHandler { _ ->
            commandHandler.onPlay()
            MPRemoteCommandHandlerStatusSuccess
        }

        // Pause Command
        commandCenter.pauseCommand.enabled = true
        commandCenter.pauseCommand.addTargetWithHandler { _ ->
            commandHandler.onPause()
            MPRemoteCommandHandlerStatusSuccess
        }

        // Next Track Command
        commandCenter.nextTrackCommand.enabled = true
        commandCenter.nextTrackCommand.addTargetWithHandler { _ ->
            commandHandler.onNext()
            MPRemoteCommandHandlerStatusSuccess
        }

        // Previous Track Command
        commandCenter.previousTrackCommand.enabled = true
        commandCenter.previousTrackCommand.addTargetWithHandler { _ ->
            commandHandler.onPrevious()
            MPRemoteCommandHandlerStatusSuccess
        }

        // Seek Command
        commandCenter.changePlaybackPositionCommand.enabled = true
        commandCenter.changePlaybackPositionCommand.addTargetWithHandler { event ->
            val positionMs = ((event as MPChangePlaybackPositionCommandEvent).positionTime * 1000).toLong()
            commandHandler.onSeek(positionMs)
            MPRemoteCommandHandlerStatusSuccess
        }
    }

    fun cleanup() {
        commandCenter.playCommand.removeTarget(null)
        commandCenter.pauseCommand.removeTarget(null)
        commandCenter.nextTrackCommand.removeTarget(null)
        commandCenter.previousTrackCommand.removeTarget(null)
        commandCenter.changePlaybackPositionCommand.removeTarget(null)
    }
}