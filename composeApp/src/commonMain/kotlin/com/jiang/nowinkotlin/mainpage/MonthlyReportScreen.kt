package com.jiang.nowinkotlin.mainpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.data.MonthlyReportItem
import com.jiang.nowinkotlin.rememberLocalImage
import com.jiang.nowinkotlin.theme.KotlinAccent
import com.jiang.nowinkotlin.theme.KotlinDark
import com.jiang.nowinkotlin.theme.KotlinPrimary
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.theme.TextTertiary
import com.jiang.nowinkotlin.viewmodel.MonthlyReportViewModel
import com.jiang.nowinkotlin.viewmodel.rememberLifecycleAware
import nowinkotlin.composeapp.generated.resources.Res
import nowinkotlin.composeapp.generated.resources.kotlin_monthly
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun MonthlyReportScreen(
    modifier: Modifier = Modifier,
    onMonthlyReportClick: (MonthlyReportItem) -> Unit
) {
    val monthlyReportViewModel = rememberLifecycleAware { scope ->
        MonthlyReportViewModel(scope)
    }
    val uiState = monthlyReportViewModel.uiState

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KotlinDark)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // 顶部导航栏
            item {
                MonthlyTopBar()
            }

            // 头图部分
            item {
                HeroSection()
            }

            // 技术月报列表
            itemsIndexed(uiState.monthlyReportList) { index, report ->
                MonthlyReportItem(
                    index = index,
                    report = report,
                    onClick = onMonthlyReportClick
                )
            }
        }
    }
}

@Composable
private fun MonthlyTopBar() {
    Row(
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
            .padding(top = 24.dp, bottom = 12.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧：品牌图标和标题
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 品牌图标
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(KotlinPrimary, KotlinAccent, KotlinSecondary)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📰",
                    fontSize = 14.sp
                )
            }

            // 标题信息
            Column {
                Text(
                    text = "Now in Kotlin",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                Text(
                    text = "技术月报",
                    fontSize = 11.sp,
                    color = TextTertiary
                )
            }
        }

        // 右侧：日历图标
//        SmallIconButton(
//            icon = Icons.Default.CalendarToday,
//            onClick = { /* TODO: 日历功能 */ },
//            contentDescription = "日历"
//        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        // 背景图片
        Image(
            bitmap = rememberLocalImage(Res.drawable.kotlin_monthly),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().aspectRatio(1280f / 600f)
        )

        // 渐变覆盖层
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1280f / 600f)
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

        // 内容信息
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "栏目",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Kotlin 技术月报",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            // 标签
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TagChip(
                    text = "每月更新",
                    backgroundColor = KotlinSecondary.copy(alpha = 0.2f),
                    textColor = KotlinSecondary
                )
                TagChip(
                    text = "来自 北京 KUG",
                    backgroundColor = Color.White.copy(alpha = 0.15f),
                    textColor = Color.White
                )
            }
        }
    }
}

@Composable
private fun MonthlyReportItem(
    index: Int,
    report: MonthlyReportItem,
    onClick: (MonthlyReportItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onClick(report) }
            .background(
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 年/月份图标
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    brush = getReportIconBrush(report.year),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = report.year.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = report.month.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        // 内容信息
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = report.displayYMD,
                fontSize = 13.sp,
                color = TextTertiary
            )

            Text(
                text = report.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )

            Text(
                text = "北京 KUG · 技术月报",
                fontSize = 12.sp,
                color = TextTertiary
            )

            // 标签
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                if (index == 0) {
                    TagChip(
                        text = "最新",
                        backgroundColor = KotlinSecondary.copy(alpha = 0.15f),
                        textColor = KotlinSecondary
                    )
                }
                TagChip(
                    text = report.year,
                    backgroundColor = Color.White.copy(alpha = 0.1f),
                    textColor = Color.White
                )
            }
        }

        // 右箭头
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "查看详情",
            modifier = Modifier.size(24.dp),
            tint = Color.White.copy(alpha = 0.8f)
        )
    }
}

public val Icons.Filled.ChevronRight: ImageVector
    get() {
        if (_chevronRight != null) {
            return _chevronRight!!
        }
        _chevronRight = materialIcon(name = "Filled.ChevronRight") {
            materialPath {
                moveTo(10.0f, 6.0f)
                lineTo(8.59f, 7.41f)
                lineTo(13.17f, 12.0f)
                lineToRelative(-4.58f, 4.59f)
                lineTo(10.0f, 18.0f)
                lineToRelative(6.0f, -6.0f)
                close()
            }
        }
        return _chevronRight!!
    }

private var _chevronRight: ImageVector? = null


@Composable
private fun TagChip(
    text: String,
    backgroundColor: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            color = textColor
        )
    }
}

// 辅助函数：根据年份获取图标渐变色
private fun getReportIconBrush(year: String): Brush {
    return when (year) {
        "2025" -> Brush.linearGradient(
            colors = listOf(KotlinPrimary, KotlinSecondary)
        )

        "2024" -> Brush.linearGradient(
            colors = listOf(KotlinAccent, KotlinPrimary)
        )

        else -> Brush.linearGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.2f),
                Color.White.copy(alpha = 0.1f)
            )
        )
    }
}