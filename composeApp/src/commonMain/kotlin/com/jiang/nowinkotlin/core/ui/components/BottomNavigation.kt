package com.jiang.nowinkotlin.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiang.nowinkotlin.shared.icons.Newspaper
import com.jiang.nowinkotlin.shared.icons.Whatshot
import com.jiang.nowinkotlin.core.ui.theme.KotlinDark
import com.jiang.nowinkotlin.core.ui.theme.KotlinSecondary
import com.jiang.nowinkotlin.core.ui.theme.TextPrimary

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "炉边漫谈",
        icon = Icons.Default.Whatshot,
        route = "home"
    ),
    BottomNavItem(
        title = "技术月报",
        icon = Icons.Default.Newspaper,
        route = "monthly"
    )
)

@Composable
fun KotlinBottomNavigation(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(KotlinDark.copy(alpha = 0.9f))
            .padding(horizontal = 32.dp, vertical = 4.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavItems.forEach { item ->
                BottomNavItemContent(
                    item = item,
                    isSelected = currentRoute == item.route,
                    onClick = { onNavigate(item.route) }
                )
            }
        }
    }
}

@Composable
private fun BottomNavItemContent(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconColor = if (isSelected) KotlinSecondary else TextPrimary.copy(alpha = 0.7f)
    val textColor = if (isSelected) KotlinSecondary else TextPrimary.copy(alpha = 0.7f)
    
    Column(
        modifier = modifier
            .selectable(
                selected = isSelected,
                onClick = onClick
            )
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        
        Text(
            text = item.title,
            fontSize = 11.sp,
            color = textColor,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}
