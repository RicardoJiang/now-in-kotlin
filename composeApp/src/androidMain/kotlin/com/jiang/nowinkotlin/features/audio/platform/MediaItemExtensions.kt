package com.jiang.nowinkotlin.features.audio.platform

import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.jiang.nowinkotlin.features.audio.player.PlayingStatus
import com.jiang.nowinkotlin.shared.data.Episode

@OptIn(UnstableApi::class)
internal fun Episode.asMediaItem(): MediaItem = MediaItem.Builder()
    .setUri(audioUrl)
    .setCustomCacheKey(id)
    .setMimeType(MimeTypes.AUDIO_MPEG)
    .setMediaId(id)
    .setMediaMetadata(
        MediaMetadata
            .Builder()
            .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
            .setArtist("Kotlin 炉边漫谈")
            .setTitle(title)
            .setArtworkUri(Uri.parse(imageUrl))
            .setIsPlayable(true)
            .setIsBrowsable(true)
            .build()
    ).build()

val Player.playingStatus: PlayingStatus
    get() = when {
        playbackState == Player.STATE_READY && playWhenReady -> PlayingStatus.PLAYING
        playbackState == Player.STATE_READY && !playWhenReady -> PlayingStatus.PAUSED
        playbackState == Player.STATE_BUFFERING -> PlayingStatus.BUFFERING
        playbackState == Player.STATE_IDLE -> PlayingStatus.IDLE
        playbackState == Player.STATE_ENDED -> PlayingStatus.ENDED
        else -> PlayingStatus.IDLE
    }

internal val MediaItem.isNetworkSource: Boolean
    get() {
        val uriScheme = this.localConfiguration?.uri?.scheme?.lowercase()
        return uriScheme == "http" || uriScheme == "https"
    }