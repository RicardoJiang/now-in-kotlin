package com.jiang.nowinkotlin.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jiang.nowinkotlin.data.Episode
import com.jiang.nowinkotlin.parseKotlinEpisodeList
import com.jiang.nowinkotlin.readJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal data class HomeUIState(val episodeList: List<Episode> = emptyList())

internal class HomeViewModel(val scope: CoroutineScope) : LifecycleAware {
    var uiState by mutableStateOf(HomeUIState())
        private set // 只允许类内部修改

    override fun onCreate() {
        scope.launch {
            val json = readJson("files/kotlin-stove.json")
            val episodeList = parseKotlinEpisodeList(json)
            uiState = uiState.copy(episodeList = episodeList)
        }
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}