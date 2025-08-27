package com.jiang.nowinkotlin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.jiang.nowinkotlin.theme.KotlinPrimary
import com.jiang.nowinkotlin.theme.KotlinSecondary
import com.jiang.nowinkotlin.theme.SurfaceOverlay
import com.jiang.nowinkotlin.theme.SurfaceOverlay10
import com.jiang.nowinkotlin.theme.TextDisabled
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.theme.TextSecondary

/**
 * 主按钮 - 使用 Kotlin 品牌紫色
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = KotlinPrimary,
            contentColor = TextPrimary,
            disabledBackgroundColor = SurfaceOverlay,
            disabledContentColor = TextDisabled
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}

/**
 * 次按钮 - 使用边框样式，Kotlin 蓝青色
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = KotlinSecondary,
            disabledContentColor = TextDisabled
        ),
        border = BorderStroke(1.dp, KotlinSecondary.copy(alpha = 0.6f)),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}

/**
 * 幽灵按钮 - 透明背景
 */
@Composable
fun GhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = SurfaceOverlay10,
            contentColor = TextPrimary,
            disabledContentColor = TextDisabled
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}

/**
 * 圆形图标按钮 - 带渐变背景（播放按钮）
 */
@Composable
fun GradientIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp
) {
    Surface(
        modifier = modifier
            .size(size)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = Color.Transparent
    ) {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            val gradient = Brush.linearGradient(
                colors = listOf(KotlinPrimary, KotlinSecondary)
            )
            drawCircle(gradient)
        }
        Box(
            modifier = Modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * 小型图标按钮 - 透明背景
 */
@Composable
fun SmallIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(36.dp)
    ) {
        Surface(
            modifier = Modifier.size(36.dp),
            shape = RoundedCornerShape(8.dp),
            color = SurfaceOverlay10
        ) {
            Box(
                modifier = Modifier.size(36.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}