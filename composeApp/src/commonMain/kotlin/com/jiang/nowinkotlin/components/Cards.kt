package com.jiang.nowinkotlin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.rememberLocalImage
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.SurfaceOverlay
import com.jiang.nowinkotlin.theme.SurfaceOverlay15
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.theme.TextSecondary
import com.jiang.nowinkotlin.theme.TextTertiary
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.kotlin_stove
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * 播客卡片组件
 * 显示播客封面、标题、时长、标签等信息
 */
@Composable
fun EpisodeCard(
    title: String,
    episode: String,
    duration: String,
    imageUrl: String,
    tags: List<String> = emptyList(),
    onClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = SurfaceOverlay
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 播客封面
//            AsyncImage(
//                model = imageUrl,
//                contentDescription = "Episode cover",
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(RoundedCornerShape(12.dp)),
//                contentScale = ContentScale.Crop
//            )

            // 内容区域
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                // 集数和日期
                Text(
                    text = episode,
                    fontSize = 11.sp,
                    color = TextTertiary,
                    lineHeight = 16.sp
                )

                // 标题
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )

                // 时长信息
                Text(
                    text = "时长 $duration · 炉边漫谈",
                    fontSize = 12.sp,
                    color = TextTertiary,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 标签
                if (tags.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tags.take(2).forEach { tag ->
                            TagChip(
                                text = tag,
                                isHighlighted = tag.contains("K2") || tag.contains("KMP")
                            )
                        }
                    }
                }
            }

            // 播放按钮
            GradientIconButton(
                icon = Icons.Default.PlayArrow,
                onClick = onPlayClick,
                size = 36.dp
            )
        }
    }
}

/**
 * 头图卡片组件
 * 用于首页顶部展示主要内容
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeroCard(
    title: String,
    subtitle: String,
    tags: List<String> = emptyList(),
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable{
              onClick()
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 背景图片
            Image(
                bitmap = rememberLocalImage(Res.drawable.kotlin_stove),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().aspectRatio(2306f / 1208f)
            )

            // 渐变遮罩
            // 渐变覆盖层
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2306f / 1208f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            // 内容
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 16.sp
                )

                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    lineHeight = 28.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                if (tags.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        tags.forEach { tag ->
                            TagChip(
                                text = tag,
                                isHighlighted = tag.contains("每周更新")
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * 标签芯片组件
 */
@Composable
fun TagChip(
    text: String,
    isHighlighted: Boolean = false,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isHighlighted) {
        KotlinSecondary.copy(alpha = 0.15f)
    } else {
        SurfaceOverlay15
    }

    val textColor = if (isHighlighted) {
        KotlinSecondary
    } else {
        TextPrimary
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}