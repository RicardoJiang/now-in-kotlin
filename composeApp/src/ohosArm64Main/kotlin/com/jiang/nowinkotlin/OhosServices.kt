package com.jiang.nowinkotlin

import com.tencent.tmm.knoi.annotation.ServiceConsumer

@ServiceConsumer
interface OhosServices {
    fun parseJson(json: String): Map<String, Any?>
}