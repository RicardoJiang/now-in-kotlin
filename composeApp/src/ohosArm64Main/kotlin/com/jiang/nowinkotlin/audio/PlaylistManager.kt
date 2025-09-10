package com.jiang.nowinkotlin.audio

import com.jiang.nowinkotlin.data.Episode

// Playlist Manager
object PlaylistManager {
    private val playlist = mutableListOf<Episode>()
    var currentIndex = -1
        private set

    fun getCurrentMusic(): Episode? = playlist.getOrNull(currentIndex)
    fun getPlaylist(): List<Episode> = playlist.toList()

    fun updatePlaylist(musics: List<Episode>, startIndex: Int) {
        playlist.clear()
        playlist.addAll(musics)
//        currentIndex = startIndex.coerceIn(0, playlist.lastIndex)
    }

    fun getNextIndex(): Int? = when {
        playlist.isEmpty() -> null
        else -> when {
            currentIndex == playlist.lastIndex -> 0
            else -> currentIndex + 1
        }
    }

    fun getPreviousIndex(): Int? = when {
        currentIndex > 0 -> currentIndex - 1
        else -> playlist.lastIndex
    }

    fun setCurrentIndex(index: Int) {
        if (index in 0..playlist.lastIndex) {
            currentIndex = index
        }
    }

    fun clear() {
        playlist.clear()
        currentIndex = 0
    }

    fun hasNext(): Boolean {
        return currentIndex < playlist.lastIndex
    }

    fun hasPrevious(): Boolean = currentIndex > 0
}