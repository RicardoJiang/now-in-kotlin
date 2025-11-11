package com.jiang.nowinkotlin.features.audio.platform

import com.jiang.nowinkotlin.features.audio.player.PlaybackState
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import com.jiang.nowinkotlin.features.audio.player.PlaybackStateManager
import com.jiang.nowinkotlin.features.audio.player.PlayingStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

internal class PlaybackStateHandler(
    private val scope: CoroutineScope,
    private val playbackStateManager: PlaybackStateManager,
    private val mainDispatcher: CoroutineDispatcher
) : Player.Listener {

    private lateinit var player: Player
    private var job: Job? = null

    fun attachTo(player: Player) {
        this.player = player
        player.addListener(this)

        job?.cancel()
        job = scope.launch(mainDispatcher) {
            playbackStateManager.flow
                .map { it.playingStatus }
                .collectLatest { playingStatus ->
                    if (playingStatus != PlayingStatus.IDLE && playingStatus != PlayingStatus.ENDED) {
                        while (true) {
                            updatePlayState()
                            delay(400.milliseconds)
                        }
                    }
                }
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        updatePlayState()
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        updatePlayState()
    }

    override fun onPositionDiscontinuity(
        oldPosition: Player.PositionInfo,
        newPosition: Player.PositionInfo,
        reason: Int
    ) {
        updatePlayState()
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        updatePlayState()
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
        updatePlayState()
    }

    override fun onEvents(player: Player, events: Player.Events) {
        if (events.contains(Player.EVENT_POSITION_DISCONTINUITY)) {
            onPositionChanged()
        }
        super.onEvents(player, events)
    }

    fun onPositionChanged() { // seekTo()
        updatePlayState()
    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
        updatePlayState()
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
        updatePlayState()
    }

    override fun onTimelineChanged(timeline: Timeline, reason: Int) {
        updatePlayState()
    }

    private fun updatePlayState() {
        playbackStateManager.playbackState = PlaybackState(
            episode = PlayerServiceLocator.episodeList.getOrNull(player.currentMediaItemIndex),
            playingStatus = player.playingStatus,
            currentIndex = player.currentMediaItemIndex,
            hasPrevious = player.hasPreviousMediaItem(),
            hasNext = player.hasNextMediaItem(),
            position = player.contentPosition,
            duration = player.duration
        )
    }
}