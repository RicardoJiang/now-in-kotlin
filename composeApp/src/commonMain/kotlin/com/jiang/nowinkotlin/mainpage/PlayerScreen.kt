package com.jiang.nowinkotlin.mainpage

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.audio.KmpAudioPlayer
import com.jiang.nowinkotlin.components.AsyncImage
import com.jiang.nowinkotlin.components.SmallIconButton
import com.jiang.nowinkotlin.components.TagChip
import com.jiang.nowinkotlin.data.Episode
import com.jiang.nowinkotlin.icons.FastForward
import com.jiang.nowinkotlin.icons.FastRewind
import com.jiang.nowinkotlin.icons.Pause
import com.jiang.nowinkotlin.icons.SkipNext
import com.jiang.nowinkotlin.icons.SkipPrevious
import com.jiang.nowinkotlin.navigation.LocalNavigator
import com.jiang.nowinkotlin.navigation.Screen
import com.jiang.nowinkotlin.theme.KotlinDark
import com.jiang.nowinkotlin.theme.KotlinPrimary
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.SurfaceOverlay
import com.jiang.nowinkotlin.theme.SurfaceOverlay10
import com.jiang.nowinkotlin.theme.SurfaceOverlay15
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.theme.TextSecondary
import com.jiang.nowinkotlin.theme.TextTertiary
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.episode_cover
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class AudioPlayerScreen(
    val episodes: List<Episode>,
    val initialIndex: Int,
) : Screen {
    @Composable
    override fun Content() {
        // 获取当前 CompositionLocal 中的 navigator 实例
        val navigator = LocalNavigator.current

        PlayerScreen(
            episodes = episodes,
            initialIndex = initialIndex,
            onBackClick = {
                // 当返回按钮被点击时，调用 navigator pop
                navigator?.pop()
            }
        )
    }
}

@Composable
fun PlayerScreen(
    episodes: List<Episode>,
    initialIndex: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val playbackState by KmpAudioPlayer.playbackState.collectAsState()
    var currentEpisode by remember { mutableStateOf(episodes.get(initialIndex)) }

    LaunchedEffect(playbackState.currentIndex) {
        if (playbackState.currentIndex != -1) {
            currentEpisode = episodes.get(playbackState.currentIndex)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        KotlinPrimary.copy(alpha = 0.26f),
                        Color.Transparent
                    ),
                    center = Offset(0.5f, -0.1f),
                    radius = 1000f
                )
            )
            .background(KotlinDark)
    ) {
        // 顶部导航
        PlayerTopBar(onBackClick = onBackClick)

        // 内容区域
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // 封面和可视化
            AlbumCoverWithVisualizer(
                imageUrl = currentEpisode?.imageUrl ?: "",
                isPlaying = playbackState.isPlaying
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 标题和信息
            EpisodeInfo(episode = currentEpisode)

            Spacer(modifier = Modifier.height(10.dp))

            // 进度条
            PlayerProgressBar(
                progress = playbackState.progress,
                currentTime = playbackState.currentTime,
                totalTime = currentEpisode?.duration ?: "",
                onSeek = {
                    KmpAudioPlayer.playbackController.seekTo((playbackState.duration * it).toLong())
                }
            )

            // 播放控制
            PlayerControls(
                isPlaying = playbackState.isPlaying,
                onPlayPauseClick = {
                    if (playbackState.isPlaying) {
                        KmpAudioPlayer.playbackController.pause()
                    } else {
                        KmpAudioPlayer.playbackController.play()
                    }
                },
                onPreviousClick = {
                    KmpAudioPlayer.playbackController.previous()
                },
                onNextClick = {
                    KmpAudioPlayer.playbackController.next()
                },
                onFastRewind = {
                    val targetPosition = playbackState.position - 5000
                    KmpAudioPlayer.playbackController.seekTo(maxOf(targetPosition, 0))
                },
                onFastForward = {
                    val targetPosition = playbackState.position + 5000
                    KmpAudioPlayer.playbackController.seekTo(
                        minOf(
                            targetPosition,
                            playbackState.duration
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 节目介绍
            EpisodeDescription(episode = currentEpisode)

            Spacer(modifier = Modifier.height(24.dp)) // 底部留白
        }
    }
}

@Composable
private fun PlayerTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallIconButton(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = onBackClick,
            contentDescription = "返回"
        )

        Text(
            text = "Kotlin 炉边漫谈",
            fontSize = 14.sp,
            color = TextSecondary
        )

        SmallIconButton(
            icon = Icons.Default.Star,
            onClick = { /* TODO: 更多选项 */ },
            contentDescription = "更多选项",
            modifier = Modifier.alpha(0f)
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AlbumCoverWithVisualizer(
    imageUrl: String,
    isPlaying: Boolean
) {
    Box(
        modifier = Modifier.size(288.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        // 封面图片
        AsyncImage(
            url = imageUrl,
            placeHodler = Res.drawable.episode_cover,
            contentDescription = "Album cover",
            modifier = Modifier
                .size(288.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )
//        Image(
//            bitmap = rememberLocalImage(Res.drawable.episode_cover),
//            contentDescription = "Album cover",
//            modifier = Modifier
//                .size(288.dp)
//                .clip(RoundedCornerShape(24.dp)),
//            contentScale = ContentScale.Crop
//        )

        // 渐变遮罩
        Box(
            modifier = Modifier
                .size(288.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.6f)
                        ),
                        startY = 200f
                    )
                )
        )

        // 音频可视化器
        if (isPlaying) {
            AudioVisualizer(
                modifier = Modifier
                    .padding(12.dp)
                    .size(32.dp)
            )
        }
    }
}

@Composable
private fun AudioVisualizer(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "audio_visualizer")

    val bar1 by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bar1"
    )

    val bar2 by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(620, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bar2"
    )

    val bar3 by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(480, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bar3"
    )

    val bar4 by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(550, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bar4"
    )

    Canvas(modifier = modifier) {
        val barWidth = size.width / 6
        val maxHeight = size.height

        // 绘制4个音频条
        listOf(bar1, bar2, bar3, bar4).forEachIndexed { index, height ->
            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = listOf(KotlinSecondary, KotlinPrimary)
                ),
                topLeft = Offset(
                    x = index * barWidth * 1.2f,
                    y = maxHeight - (maxHeight * height)
                ),
                size = Size(
                    width = barWidth * 0.8f,
                    height = maxHeight * height
                ),
                cornerRadius = CornerRadius(barWidth * 0.4f)
            )
        }
    }
}

@Composable
private fun EpisodeInfo(
    episode: Episode?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = episode?.title ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )

        Text(
            text = "${episode?.episodeNumber} · 炉边漫谈 · ${episode?.duration}",
            fontSize = 13.sp,
            color = TextSecondary,
            modifier = Modifier.padding(top = 4.dp)
        )

        if (episode?.tags?.isNotEmpty() == true) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 12.dp)
            ) {
                episode.tags.forEach { tag ->
                    TagChip(
                        text = tag,
                        isHighlighted = tag.contains("K2") || tag.contains("KMP")
                    )
                }
            }
        }
    }
}

@Composable
private fun PlayerProgressBar(
    progress: Float,
    currentTime: String,
    totalTime: String,
    onSeek: (Float) -> Unit
) {
    var isUserDrag by remember { mutableStateOf(false) }
    var currentProgress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(progress) {
        if (!isUserDrag) {
            currentProgress = progress
        }
    }

    Box {
        Slider(
            value = currentProgress,
            onValueChange = { value ->
                if (isUserDrag) {
                    currentProgress = value
                }
            },
            onValueChangeFinished = {
                if (isUserDrag) {
                    onSeek(currentProgress)
                    isUserDrag = false
                }
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp).pointerInput(isUserDrag) {
                awaitPointerEventScope {
                    try {
                        awaitFirstDown(requireUnconsumed = false)
                        isUserDrag = true
                    } catch (_: Exception) {
                    }
                }
            },
            colors = SliderDefaults.colors(
                thumbColor = KotlinPrimary,
                activeTrackColor = KotlinPrimary,
                inactiveTrackColor = SurfaceOverlay15
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 6.dp, top = 36.dp, 6.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentTime,
                fontSize = 11.sp,
                color = TextTertiary
            )
            Text(
                text = totalTime,
                fontSize = 11.sp,
                color = TextTertiary
            )
        }
    }
}

@Composable
private fun PlayerControls(
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onFastRewind: () -> Unit,
    onFastForward: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // 播放控制区域
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 快退
            IconButton(
                onClick = onFastRewind,
                modifier = Modifier.size(36.dp)
            ) {
                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape,
                    color = SurfaceOverlay10
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FastRewind,
                            contentDescription = "快退",
                            tint = TextPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // 上一首
            IconButton(
                onClick = onPreviousClick,
                modifier = Modifier.size(48.dp)
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = SurfaceOverlay10
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SkipPrevious,
                            contentDescription = "上一首",
                            tint = TextPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // 播放/暂停按钮
            PlayPauseButton(
                isPlaying = isPlaying,
                onClick = onPlayPauseClick
            )

            // 下一首
            IconButton(
                onClick = onNextClick,
                modifier = Modifier.size(48.dp)
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = SurfaceOverlay10
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SkipNext,
                            contentDescription = "下一首",
                            tint = TextPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // 快进
            IconButton(
                onClick = onFastForward,
                modifier = Modifier.size(36.dp)
            ) {
                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape,
                    color = SurfaceOverlay10
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FastForward,
                            contentDescription = "快进",
                            tint = TextPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayPauseButton(
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Surface(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            color = Color.Transparent
        ) {
            Canvas(modifier = Modifier.size(64.dp)) {
                drawCircle(
                    brush = Brush.linearGradient(
                        colors = listOf(KotlinPrimary, KotlinSecondary)
                    )
                )
            }
            Box(
                modifier = Modifier.size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "暂停" else "播放",
                    tint = TextPrimary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
private fun EpisodeDescription(episode: Episode?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = SurfaceOverlay
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "节目介绍",
                fontSize = 14.sp,
                color = TextSecondary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "《Kotlin 炉边漫谈》",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = episode?.displayDescription ?: "",
                fontSize = 13.sp,
                color = TextSecondary,
                lineHeight = 20.sp
            )
        }
    }
}