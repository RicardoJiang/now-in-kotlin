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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.components.EpisodeCard
import com.jiang.nowinkotlin.components.HeroCard
import com.jiang.nowinkotlin.components.SmallIconButton
import com.jiang.nowinkotlin.theme.KotlinAccent
import com.jiang.nowinkotlin.theme.KotlinDark
import com.jiang.nowinkotlin.theme.KotlinPrimary
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.theme.TextTertiary

data class Episode(
    val id: String,
    val title: String,
    val episodeNumber: String,
    val date: String,
    val duration: String,
    val imageUrl: String,
    val audioUrl: String,
    val tags: List<String>
)

// 示例数据
val sampleEpisodes = listOf(
    Episode(
        id = "1",
        title = "K2 编译器进展与 Kotlin 2.2 的稳定特性",
        episodeNumber = "EP 43",
        date = "2025-01-07",
        duration = "28:14",
        imageUrl = "https://picsum.photos/seed/ep1/200/200",
        tags = listOf("K2", "Gradle"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/cly32eiou08b901t22mj04ten/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fcly32eiov08ba01t22t4515zk.mp3?v=1719843528113"
    ),
    Episode(
        id = "2",
        title = "Jetpack Compose 性能优化与常见陷阱",
        episodeNumber = "EP 42",
        date = "2024-12-24",
        duration = "36:21",
        imageUrl = "https://picsum.photos/seed/ep2/200/200",
        tags = listOf("Compose", "UI"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/clqnuemqc04fc01w6e18a6w5p/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fclqnuemqc04fd01w66ws842wu.mp3?v=1703685643339"
    ),
    Episode(
        id = "3",
        title = "Kotlin Multiplatform 实战与生态观察",
        episodeNumber = "EP 41",
        date = "2024-12-10",
        duration = "41:02",
        imageUrl = "https://podcast.kotlin.tips/images/episodes/cover-episode17.png",
        tags = listOf("KMP", "Tooling"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/clnzno7yx06xe01y1f5xva9rv/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fclnzno7yx06xf01y1395lcy1i.mp3?v=1697869578770"
    ),
    Episode(
        id = "4",
        title = "K2 编译器进展与 Kotlin 2.2 的稳定特性",
        episodeNumber = "EP 43",
        date = "2025-01-07",
        duration = "28:14",
        imageUrl = "https://picsum.photos/seed/ep1/200/200",
        tags = listOf("K2", "Gradle"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/cly32eiou08b901t22mj04ten/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fcly32eiov08ba01t22t4515zk.mp3?v=1719843528113"
    ),
    Episode(
        id = "5",
        title = "Jetpack Compose 性能优化与常见陷阱",
        episodeNumber = "EP 42",
        date = "2024-12-24",
        duration = "36:21",
        imageUrl = "https://picsum.photos/seed/ep2/200/200",
        tags = listOf("Compose", "UI"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/clqnuemqc04fc01w6e18a6w5p/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fclqnuemqc04fd01w66ws842wu.mp3?v=1703685643339"
    ),
    Episode(
        id = "6",
        title = "Kotlin Multiplatform 实战与生态观察",
        episodeNumber = "EP 41",
        date = "2024-12-10",
        duration = "41:02",
        imageUrl = "https://podcast.kotlin.tips/images/episodes/cover-episode17.png",
        tags = listOf("KMP", "Tooling"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/clnzno7yx06xe01y1f5xva9rv/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fclnzno7yx06xf01y1395lcy1i.mp3?v=1697869578770"
    ),
    Episode(
        id = "7",
        title = "K2 编译器进展与 Kotlin 2.2 的稳定特性",
        episodeNumber = "EP 43",
        date = "2025-01-07",
        duration = "28:14",
        imageUrl = "https://picsum.photos/seed/ep1/200/200",
        tags = listOf("K2", "Gradle"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/cly32eiou08b901t22mj04ten/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fcly32eiov08ba01t22t4515zk.mp3?v=1719843528113"
    ),
    Episode(
        id = "8",
        title = "Jetpack Compose 性能优化与常见陷阱",
        episodeNumber = "EP 42",
        date = "2024-12-24",
        duration = "36:21",
        imageUrl = "https://picsum.photos/seed/ep2/200/200",
        tags = listOf("Compose", "UI"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/clqnuemqc04fc01w6e18a6w5p/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fclqnuemqc04fd01w66ws842wu.mp3?v=1703685643339"
    ),
    Episode(
        id = "9",
        title = "Kotlin Multiplatform 实战与生态观察",
        episodeNumber = "EP 41",
        date = "2024-12-10",
        duration = "41:02",
        imageUrl = "https://podcast.kotlin.tips/images/episodes/cover-episode17.png",
        tags = listOf("KMP", "Tooling"),
        audioUrl = "https://m.cdn.firstory.me/track/cl3lg86nt00ql01vr0tr42zh3/clnzno7yx06xe01y1f5xva9rv/https%3A%2F%2Fd3mww1g1pfq2pt.cloudfront.net%2FRecord%2Fcl3lg86nt00ql01vr0tr42zh3%2Fclnzno7yx06xf01y1395lcy1i.mp3?v=1697869578770"
    ),
)

@Composable
fun HomeScreen(
    onEpisodeClick: (List<Episode>, Int) -> Unit,
    onPlayClick: (List<Episode>, Int) -> Unit,
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
                itemsIndexed(
                    items = sampleEpisodes,
                    key = { index, it -> it.id }
                ) { index, episode ->
                    EpisodeCard(
                        title = episode.title,
                        episode = "${episode.episodeNumber} · ${episode.date}",
                        duration = episode.duration,
                        imageUrl = episode.imageUrl,
                        tags = episode.tags,
                        onClick = { onEpisodeClick(sampleEpisodes, index) },
                        onPlayClick = { onPlayClick(sampleEpisodes, index) },
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
