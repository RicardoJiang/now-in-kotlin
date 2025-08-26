package com.tencent.compose.sample.mainpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tencent.compose.sample.components.*
import com.tencent.compose.sample.theme.*
import org.jetbrains.compose.ui.tooling.preview.Preview

data class Episode(
    val id: String,
    val title: String,
    val episodeNumber: String,
    val date: String,
    val duration: String,
    val imageUrl: String,
    val tags: List<String>
)

// 示例数据
val sampleEpisodes = listOf(
    Episode(
        id = "1",
        title = "K2 编译器进展与 Kotlin 2.1 的稳定特性",
        episodeNumber = "EP 43",
        date = "2025-01-07",
        duration = "28:14",
        imageUrl = "https://picsum.photos/seed/ep1/200/200",
        tags = listOf("K2", "Gradle")
    ),
    Episode(
        id = "2",
        title = "Jetpack Compose 性能优化与常见陷阱",
        episodeNumber = "EP 42",
        date = "2024-12-24",
        duration = "36:21",
        imageUrl = "https://picsum.photos/seed/ep2/200/200",
        tags = listOf("Compose", "UI")
    ),
    Episode(
        id = "3",
        title = "Kotlin Multiplatform 实战与生态观察",
        episodeNumber = "EP 41",
        date = "2024-12-10",
        duration = "41:02",
        imageUrl = "https://picsum.photos/seed/ep3/200/200",
        tags = listOf("KMP", "Tooling")
    )
)

@Composable
fun HomeScreen(
    onEpisodeClick: (Episode) -> Unit,
    onPlayClick: (Episode) -> Unit,
    modifier: Modifier = Modifier
) {
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
                items(
                    items = sampleEpisodes,
                    key = { it.id }
                ) { episode ->
                    EpisodeCard(
                        title = episode.title,
                        episode = "${episode.episodeNumber} · ${episode.date}",
                        duration = episode.duration,
                        imageUrl = episode.imageUrl,
                        tags = episode.tags,
                        onClick = { onEpisodeClick(episode) },
                        onPlayClick = { onPlayClick(episode) },
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
                        androidx.compose.ui.graphics.Color.Transparent
                    )
                )
            )
            .padding(top = 24.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
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
                    color = androidx.compose.ui.graphics.Color.Transparent
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
                            imageVector = Icons.Default.Star,
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
            
            // 右侧：搜索按钮
            SmallIconButton(
                icon = Icons.Default.Search,
                onClick = { /* TODO: 实现搜索功能 */ },
                contentDescription = "搜索"
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CursronowinkotlinTheme {
        HomeScreen(
            onEpisodeClick = { },
            onPlayClick = { }
        )
    }
}
