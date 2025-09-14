package com.jiang.nowinkotlin.screen

import androidx.compose.runtime.Immutable

@Immutable
internal data class NavItem(
    val name: String,
    val params: Map<String, Any>
)