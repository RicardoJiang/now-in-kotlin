package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.data.Episode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val TIME_UNSET = Long.MIN_VALUE + 1
const val CURRENT_INDEX_UNSET = -1

enum class PlayingStatus {
    PLAYING,
    PAUSED,
    BUFFERING,
    IDLE,
    ENDED
}

data class PlaybackState(
    val episode: Episode? = null,
    val playingStatus: PlayingStatus = PlayingStatus.IDLE,
    val currentIndex: Int = CURRENT_INDEX_UNSET,
    val hasPrevious: Boolean = false,
    val hasNext: Boolean = false,
    val position: Long = TIME_UNSET,
    val duration: Long = TIME_UNSET,
) {

    private fun getFormatTime(timeSeconds: Long): String {
        val hour = timeSeconds / 3600
        val minute = (timeSeconds % 3600) / 60
        val second = timeSeconds % 60
        var result = ""
        if (hour > 0) {
            result += if (hour < 10) {
                "0$hour"
            } else {
                hour
            }
            result += ":"
        }
        result += if (minute < 10) {
            "0$minute"
        } else {
            minute
        }
        result += ":"
        result += if (second < 10) {
            "0$second"
        } else {
            second
        }
        return result
    }

    val isPlaying: Boolean
        get() = playingStatus == PlayingStatus.PLAYING

    val totalTime: String
        get() = getFormatTime(duration / 1000)

    val currentTime: String
        get() = getFormatTime(position / 1000)

    val progress: Float
        get() {
            if (position <= 0) {
                return 0f
            }
            return position * 1.0f / duration
        }
}

internal object PlaybackStateManager {
    private val _playbackState = MutableStateFlow(PlaybackState())
    val flow: StateFlow<PlaybackState> = _playbackState.asStateFlow()

    var playbackState: PlaybackState
        get() = _playbackState.value
        set(value) {
            _playbackState.update { value }
        }
}