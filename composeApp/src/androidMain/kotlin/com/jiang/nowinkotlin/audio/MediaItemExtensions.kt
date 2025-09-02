package com.jiang.nowinkotlin.audio

import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import com.jiang.nowinkotlin.mainpage.Episode

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