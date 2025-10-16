package com.jiang.nowinkotlin

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
internal object NowInKotlinContextProvider {
    lateinit var context: Context

    fun initContext(context: Context) {
        this.context = context
    }
}