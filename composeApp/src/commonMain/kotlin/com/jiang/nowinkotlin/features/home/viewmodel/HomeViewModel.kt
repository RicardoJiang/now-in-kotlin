package com.jiang.nowinkotlin.features.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jiang.nowinkotlin.shared.data.Episode
import com.jiang.nowinkotlin.shared.data.EpisodeModel
import com.jiang.nowinkotlin.core.platform.readJson
import com.jiang.nowinkotlin.shared.viewmodel.LifecycleAware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

internal data class HomeUIState(val episodeList: List<Episode> = emptyList())

internal class HomeViewModel(val scope: CoroutineScope) : LifecycleAware {
    var uiState by mutableStateOf(HomeUIState())
        private set // 只允许类内部修改

    val jsonHelper by lazy {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    override fun onCreate() {
        scope.launch {
            val json = readJson("files/kotlin-stove.json")
            val list = jsonHelper.decodeFromString<List<EpisodeModel>>(json)
            val episodeList = list.mapIndexed { index, item ->
                Episode(
                    index = index,
                    size = list.size,
                    episodeTitle = item.title,
                    pubDate = item.pubDate,
                    episodeDuration = item.audioResource.duration,
                    imageUrl = item.thumbnail,
                    audioUrl = item.audioResource.link,
                    description = item.description,
                    tags = emptyList()
                )
            }
            uiState = uiState.copy(episodeList = episodeList)
        }
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}