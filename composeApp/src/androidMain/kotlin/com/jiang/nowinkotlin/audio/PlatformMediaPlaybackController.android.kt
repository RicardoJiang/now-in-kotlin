package com.jiang.nowinkotlin.audio

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.jiang.nowinkotlin.PlatformContext
import com.jiang.nowinkotlin.data.Episode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.guava.asDeferred
import kotlinx.coroutines.launch

internal class PlatformMediaPlaybackController(
    private val context: Context,
) : MediaPlaybackController {
    private var controllerDeferred: Deferred<MediaController> = newControllerAsync()

    private fun newControllerAsync() = MediaController
        .Builder(context, SessionToken(context, ComponentName(context, PlaybackService::class.java)))
        .buildAsync()
        .asDeferred()

    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val activeControllerDeferred: Deferred<MediaController>
        get() {
            if (controllerDeferred.isCompleted) {
                val completedController = controllerDeferred.getCompleted()
                if (!completedController.isConnected) {
                    completedController.release()
                    controllerDeferred = newControllerAsync()
                }
            }
            return controllerDeferred
        }

    override fun seekTo(positionMs: Long) = executeAfterPrepare { controller ->
        controller.seekTo(positionMs)
    }

    override fun previous() = executeAfterPrepare { controller ->
        controller.seekToPrevious()
    }

    override fun next() = executeAfterPrepare { controller ->
        controller.seekToNext()
    }

    override fun play() = executeAfterPrepare { controller ->
        controller.play()
    }

    override fun pause() = executeAfterPrepare { controller ->
        controller.pause()
    }

    override fun skipTo(musicIndex: Int, playImmediately: Boolean) = executeAfterPrepare { controller ->
        controller.seekToDefaultPosition(musicIndex)
        if (playImmediately) {
            controller.play()
        }
    }

    override fun prepare(musics: List<Episode>, index: Int, positionMs: Long) = executeAfterPrepare { controller ->
        PlayerServiceLocator.episodeList = musics
        controller.repeatMode = Player.REPEAT_MODE_ALL
        controller.setMediaItems(musics.map { it.asMediaItem() }, index, positionMs)
        controller.prepare()
    }

    override fun stop() = executeAfterPrepare { controller ->
        controller.stop()
        controller.release()
    }

    override fun release() = executeAfterPrepare { controller ->
        controller.release()
    }

    private inline fun executeAfterPrepare(crossinline action: suspend (MediaController) -> Unit) {
        scope.launch {
            val controller = activeControllerDeferred.await()
            action(controller)
        }
    }
}

internal actual fun createKmpAudioPlayer(context: PlatformContext): MediaPlaybackController {
    return PlatformMediaPlaybackController(context)
}
