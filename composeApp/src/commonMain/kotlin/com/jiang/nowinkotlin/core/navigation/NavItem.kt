package com.jiang.nowinkotlin.core.navigation

import androidx.compose.runtime.Immutable

@Immutable
internal data class NavItem(
    val name: String,
    val params: Map<String, Any>
)