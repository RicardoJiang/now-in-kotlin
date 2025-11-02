package com.jiang.nowinkotlin.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.jiang.nowinkotlin.components.KotlinBottomNavigation
import com.jiang.nowinkotlin.data.Episode
import com.jiang.nowinkotlin.data.MonthlyReportItem
import com.jiang.nowinkotlin.theme.KotlinDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    onEpisodeClick: (List<Episode>, Int) -> Unit,
    onPlayClick: (List<Episode>, Int) -> Unit,
    onMonthlyReportClick: (MonthlyReportItem) -> Unit
) {
    val pages = listOf("home", "monthly")
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                beyondBoundsPageCount = 1
            ) { pageIndex ->
                when (pages[pageIndex]) {
                    "home" -> HomeScreen(
                        onEpisodeClick = onEpisodeClick,
                        onPlayClick = onPlayClick,
                        // onNavigateToTab is removed
                    )

                    "monthly" -> MonthlyReportScreen(
                        onMonthlyReportClick = onMonthlyReportClick
                    )
                }
            }

            // Bottom Navigation
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
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