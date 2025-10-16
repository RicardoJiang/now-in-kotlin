package com.jiang.nowinkotlin.video

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.icons.Pause
import com.jiang.nowinkotlin.theme.KotlinPrimary
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.SurfaceOverlay15
import com.jiang.nowinkotlin.theme.TextPrimary

/**
 * 视频播放器控制 UI 组件（优化版）
 * 包含播放/暂停按钮、进度条和时间显示
 */
@Composable
fun VideoPlayerControls(
    controller: VideoPlayerController,
    modifier: Modifier = Modifier
) {
    val isPlaying by controller.isPlaying
    val progress by controller.progress
    val currentPosition by controller.currentPosition
    val duration by controller.duration

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.3f),
                        Color.Black.copy(alpha = 0.7f)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 进度条区域
            EnhancedProgressBar(
                progress = progress,
                onSeek = { newProgress ->
                    controller.seekToProgress(newProgress)
                }
            )

            // 底部控制区域：播放按钮 + 时间显示
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 播放/暂停按钮（左侧）
                CompactPlayPauseButton(
                    isPlaying = isPlaying,
                    onClick = { controller.playPause() }
                )

                // 当前时间
                Text(
                    text = formatTime(currentPosition),
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.body2
                )

                // 分隔符
                Text(
                    text = "/",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.body2
                )

                // 总时长
                Text(
                    text = formatTime(duration),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2
                )

                // 右侧空间（可以添加更多按钮）
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

/**
 * 增强的进度条组件
 */
@Composable
private fun EnhancedProgressBar(
    progress: Float,
    onSeek: (Float) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Slider(
            value = progress.coerceIn(0f, 1f),
            onValueChange = onSeek,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = KotlinPrimary,
                activeTrackColor = KotlinPrimary, // Netflix 红色
                inactiveTrackColor = SurfaceOverlay15
            )
        )
    }
}

/**
 * 紧凑的播放/暂停按钮（缩小版）
 */
@Composable
private fun CompactPlayPauseButton(
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Surface(
            modifier = Modifier.size(30.dp),
            shape = CircleShape,
            color = Color.Transparent
        ) {
            Canvas(modifier = Modifier.size(30.dp)) {
                drawCircle(
                    brush = Brush.linearGradient(
                        colors = listOf(KotlinPrimary, KotlinSecondary)
                    )
                )
            }
            Box(
                modifier = Modifier.size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "暂停" else "播放",
                    tint = TextPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

/**
 * 格式化时间（毫秒 -> MM:SS 或 HH:MM:SS）
 */
private fun formatTime(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return if (hours > 0) {
        "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    } else {
        "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    }
}
