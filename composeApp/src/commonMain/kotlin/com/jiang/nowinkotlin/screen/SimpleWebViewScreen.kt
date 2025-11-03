package com.jiang.nowinkotlin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.components.SmallIconButton
import com.jiang.nowinkotlin.theme.KotlinDark
import com.jiang.nowinkotlin.theme.TextPrimary
import com.jiang.nowinkotlin.webview.WebView
import com.jiang.nowinkotlin.webview.rememberWebViewState

@Composable
fun SimpleWebViewScreen(
    url: String,
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberWebViewState(url = url)
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(KotlinDark)
    ) {
        // 主内容区域
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 顶部导航栏
            TopNavigationBar(onBackClick, title)
            WebView(
                state = state,
                modifier = Modifier.fillMaxSize()
            )
        }

        // 加载指示器 - 当页面未完成加载时显示
        if (!state.isPageFinished) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun TopNavigationBar(onBackClick: () -> Unit, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SmallIconButton(
            onClick = onBackClick,
            icon = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
        Text(
            text = title,
            color = TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}