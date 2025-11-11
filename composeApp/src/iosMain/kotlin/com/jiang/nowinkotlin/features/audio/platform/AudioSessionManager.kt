package com.jiang.nowinkotlin.features.audio.platform

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive

// Audio Session Manager
class AudioSessionManager {
    @OptIn(ExperimentalForeignApi::class)
    fun setupAudioSession() {
        try {
            val audioSession = AVAudioSession.sharedInstance()
            audioSession.setCategory(AVAudioSessionCategoryPlayback, null)
            audioSession.setActive(true, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}