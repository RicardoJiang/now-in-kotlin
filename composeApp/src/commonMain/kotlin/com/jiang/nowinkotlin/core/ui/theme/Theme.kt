package com.jiang.nowinkotlin.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = KotlinPrimary,
    secondary = KotlinSecondary,
    background = KotlinDark,
    surface = KotlinSurface,
    onPrimary = TextPrimary,
    onSecondary = KotlinDark,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = SemanticError,
    onError = TextPrimary
)

// 应用主要使用深色主题，但保留浅色主题配置以备用
private val LightColorPalette = lightColors(
    primary = KotlinPrimary,
    secondary = KotlinSecondary,
    background = Color.White,
    surface = Color(0xFFF8F9FA),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
)

@Composable
fun CursronowinkotlinTheme(
    // 应用默认使用深色主题，符合设计稿要求
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}