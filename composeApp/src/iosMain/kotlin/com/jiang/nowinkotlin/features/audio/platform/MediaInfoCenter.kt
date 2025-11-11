package com.jiang.nowinkotlin.features.audio.platform

import com.jiang.nowinkotlin.features.audio.player.PlaybackState
import com.jiang.nowinkotlin.shared.data.Episode
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.rate
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.MediaPlayer.MPMediaItemArtwork
import platform.MediaPlayer.MPMediaItemPropertyArtist
import platform.MediaPlayer.MPMediaItemPropertyArtwork
import platform.MediaPlayer.MPMediaItemPropertyPersistentID
import platform.MediaPlayer.MPMediaItemPropertyPlaybackDuration
import platform.MediaPlayer.MPMediaItemPropertyTitle
import platform.MediaPlayer.MPNowPlayingInfoCenter
import platform.MediaPlayer.MPNowPlayingInfoPropertyDefaultPlaybackRate
import platform.MediaPlayer.MPNowPlayingInfoPropertyElapsedPlaybackTime
import platform.MediaPlayer.MPNowPlayingInfoPropertyIsLiveStream
import platform.MediaPlayer.MPNowPlayingInfoPropertyPlaybackRate
import platform.UIKit.UIImage

internal class MediaInfoCenter(
    private val player: AVPlayer,
    private val coroutineScope: CoroutineScope
) {
    private val nowPlayingInfoCenter = MPNowPlayingInfoCenter.defaultCenter()
    private var settingArtworkJob: Job? = null

    fun setupInitInfo() {
        nowPlayingInfoCenter.nowPlayingInfo = mutableMapOf<Any?, Any?>().apply {
            put(MPNowPlayingInfoPropertyIsLiveStream, false)
            put(MPNowPlayingInfoPropertyPlaybackRate, 1.0)
        }
    }

    fun updateInfo(music: Episode, playbackState: PlaybackState) {
        val nowPlayingInfo = nowPlayingInfoCenter.nowPlayingInfo?.toMutableMap() ?: mutableMapOf()

        if (isNewMusic(music.id, nowPlayingInfo)) {
            nowPlayingInfo.apply {
                put(MPMediaItemPropertyPersistentID, music.id.hashCode())
                music.imageUrl?.let {
                    setArtwork(it)
                }
                put(MPMediaItemPropertyTitle, music.title)
                put(MPMediaItemPropertyArtist, "炉边漫谈")
                put(MPNowPlayingInfoPropertyDefaultPlaybackRate, 1.0f)
            }
        }
        nowPlayingInfo.apply {
            put(MPMediaItemPropertyPlaybackDuration, playbackState.duration / 1000.0)
            put(MPNowPlayingInfoPropertyElapsedPlaybackTime, playbackState.position / 1000.0)
            put(MPNowPlayingInfoPropertyPlaybackRate, player.rate)
        }

        nowPlayingInfoCenter.nowPlayingInfo = nowPlayingInfo
    }

    fun cleanup() {
        settingArtworkJob?.cancel()
        nowPlayingInfoCenter.nowPlayingInfo = null
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setArtwork(coverUrl: String) {
        settingArtworkJob?.cancel()

        settingArtworkJob = coroutineScope.launch {
            try {
                val image = loadImage(coverUrl)
                val artwork = MPMediaItemArtwork(boundsSize = CGSizeMake(600.0, 600.0)) { _ ->
                    image
                }

                val currentInfo = nowPlayingInfoCenter.nowPlayingInfo?.toMutableMap()
                    ?: mutableMapOf()
                currentInfo[MPMediaItemPropertyArtwork] = artwork
                nowPlayingInfoCenter.nowPlayingInfo = currentInfo
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isNewMusic(musicId: String, nowPlayingInfo: MutableMap<Any?, Any?>): Boolean {
        val updateMusicId = musicId.hashCode()
        val currentMusicId = nowPlayingInfo[MPMediaItemPropertyPersistentID]
        return currentMusicId != updateMusicId
    }

    private suspend fun loadImage(url: String): UIImage? {
        return withContext(Dispatchers.IO) {
            val nsUrl = NSURL.URLWithString(url)
            nsUrl?.let { url ->
                NSData.dataWithContentsOfURL(url)
            }?.let { data ->
                UIImage.imageWithData(data)
            }
        }
    }
}