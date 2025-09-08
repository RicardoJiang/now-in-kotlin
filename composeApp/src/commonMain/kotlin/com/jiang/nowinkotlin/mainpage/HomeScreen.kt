package com.jiang.nowinkotlin.mainpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.audio.KmpAudioPlayer
import com.jiang.nowinkotlin.audio.PlaybackState
import com.jiang.nowinkotlin.components.EpisodeCard
import com.jiang.nowinkotlin.components.HeroCard
import com.jiang.nowinkotlin.components.SmallIconButton
import com.jiang.nowinkotlin.data.Episode
import com.jiang.nowinkotlin.icons.Whatshot
import com.jiang.nowinkotlin.theme.KotlinAccent
import com.jiang.nowinkotlin.theme.KotlinDark
import com.jiang.nowinkotlin.theme.KotlinPrimary
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.theme.TextTertiary
import com.jiang.nowinkotlin.viewmodel.HomeViewModel
import com.jiang.nowinkotlin.viewmodel.rememberLifecycleAware

@Composable
fun HomeScreen(
    onEpisodeClick: (List<Episode>, Int) -> Unit,
    onPlayClick: (List<Episode>, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeViewModel = rememberLifecycleAware { scope ->
        HomeViewModel(scope)
    }
    val uiState = homeViewModel.uiState


    LaunchedEffect(uiState.episodeList) {
        if (uiState.episodeList.isNotEmpty()) {
            KmpAudioPlayer.playbackController.prepare(uiState.episodeList, 0, 0)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KotlinDark)
    ) {
        // 主内容区域
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 顶部导航栏
            TopNavigationBar()

            // 内容区域
            LazyColumn(
                modifier = Modifier.weight(1f), // 使用 weight 而不是 fillMaxSize
                contentPadding = PaddingValues(bottom = 80.dp), // 为底部导航留空间
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // 头图
                    HeroCard(
                        title = "炉边漫谈 · Kotlin 最新播客",
                        subtitle = "栏目",
                        tags = listOf("不定期更新", "来自 podcast.kotlin.tips"),
                        onClick = { },
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }

                // 播客列表
                itemsIndexed(
                    items = uiState.episodeList,
                    key = { index, it -> it.id }
                ) { index, episode ->
                    EpisodeCard(
                        title = episode.title,
                        episode = "${episode.episodeNumber} · ${episode.date}",
                        duration = episode.duration,
                        imageUrl = episode.imageUrl,
                        tags = episode.tags,
                        onClick = { onEpisodeClick(uiState.episodeList, index) },
                        onPlayClick = { onPlayClick(uiState.episodeList, index) },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TopNavigationBar() {
    // 渐变背景
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        KotlinDark,
                        Color.Transparent
                    )
                )
            )
            .padding(top = 16.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧：Logo 和标题
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Logo
                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Transparent
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        KotlinPrimary,
                                        KotlinAccent,
                                        KotlinSecondary
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Whatshot,
                            contentDescription = "Now in Kotlin Logo",
                            tint = TextPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // 标题
                Column {
                    Text(
                        text = "Now in Kotlin",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )
                    Text(
                        text = "炉边漫谈",
                        fontSize = 11.sp,
                        color = TextTertiary
                    )
                }
            }
        }
    }
}
