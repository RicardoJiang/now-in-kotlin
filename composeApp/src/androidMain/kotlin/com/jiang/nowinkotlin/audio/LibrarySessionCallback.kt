package com.jiang.nowinkotlin.audio

import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaLibraryService.MediaLibrarySession
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSession.ConnectionResult.AcceptedResultBuilder
import androidx.media3.session.SessionCommand
import com.google.common.util.concurrent.ListenableFuture

@OptIn(UnstableApi::class)
internal class LibrarySessionCallback(
): MediaLibrarySession.Callback {
    override fun onPlaybackResumption(
        mediaSession: MediaSession,
        controller: MediaSession.ControllerInfo
    ): ListenableFuture<MediaSession.MediaItemsWithStartPosition> {
        return super.onPlaybackResumption(mediaSession, controller)
    }

    override fun onConnect(session: MediaSession, controller: MediaSession.ControllerInfo): MediaSession.ConnectionResult {
        val sessionCommands = MediaSession.ConnectionResult.DEFAULT_SESSION_AND_LIBRARY_COMMANDS.buildUpon()
            .remove(SessionCommand.COMMAND_CODE_SESSION_SET_RATING)
            .remove(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)
            .remove(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)
            .remove(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)
            .remove(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT)
            .build()

        val playerCommands = MediaSession.ConnectionResult.DEFAULT_PLAYER_COMMANDS.buildUpon().build()

        return when {
            session.isMediaNotificationController(controller) ->
                AcceptedResultBuilder(session)
                    .setAvailablePlayerCommands(playerCommands)
                    .setAvailableSessionCommands(sessionCommands)
                    .build()

            else -> AcceptedResultBuilder(session).build()
        }
    }
}