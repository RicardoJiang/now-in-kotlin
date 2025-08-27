package com.jiang.nowinkotlin.mainpage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.jiang.nowinkotlin.components.KotlinBottomNavigation
import com.jiang.nowinkotlin.theme.KotlinDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(onEpisodeClick: (Episode) -> Unit) {
    val pages = listOf("home", "monthly")
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { pageIndex ->
                when (pages[pageIndex]) {
                    "home" -> HomeScreen(
                        onEpisodeClick = onEpisodeClick,
                        onPlayClick = onEpisodeClick,
                        // onNavigateToTab is removed
                    )
                    "monthly" -> MonthlyReportScreen(
                        // onNavigateToTab is removed
                    )
                }
            }

            // Bottom Navigation
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = KotlinDark.copy(alpha = 0.95f)
            ) {
                KotlinBottomNavigation(
                    currentRoute = pages[pagerState.currentPage],
                    onNavigate = { route ->
                        scope.launch {
                            val pageIndex = pages.indexOf(route)
                            if (pageIndex != -1) {
                                pagerState.animateScrollToPage(pageIndex)
                            }
                        }
                    }
                )
            }
        }
    }
}