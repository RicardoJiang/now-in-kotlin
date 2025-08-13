/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.compose.sample.mainpage.sectionItem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.tencent.compose.sample.mainpage.sectionItem.BouncingBall.Companion.setCount
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random

private fun Modifier.noRippleClickable(onClick: (Offset) -> Unit): Modifier =
    composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
        }.pointerInput(Unit) {
            detectTapGestures(onTap = {
                println("tap offset = $it")
                onClick(it)
            })
        }
    }

private var areaWidth = 0
private var areaHeight = 0

@Composable
internal fun BouncingBallsApp(initialBallsCount: Int = 10) {
    val items = remember {
        val list = mutableStateListOf<BouncingBall>()
        list.addAll(generateSequence {
            BouncingBall.createBouncingBall()
        }.take(initialBallsCount))
        list
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .border(width = 1.dp, color = Color.Black)
            .noRippleClickable {
                items += BouncingBall.createBouncingBall(offset = it)
            }
    ) {
        areaWidth = maxWidth.value.roundToInt()
        areaHeight = maxHeight.value.roundToInt()
        Balls(items)
        Column {
            Row {
                Text("count: ${items.size}")
                Spacer(Modifier.width(16.dp))
                Button(onClick = { items.setCount(10) }) { Text("10") }
                Spacer(Modifier.width(16.dp))
                Button(onClick = { items.setCount(items.size + 10) }) { Text("+10") }
                Spacer(Modifier.width(16.dp))
                Button(onClick = { items.setCount(items.size + 100) }) { Text("+100") }
            }
        }
    }

    LaunchedEffect(Unit) {
        var lastTime = 0L
        var dt = 0L

        while (true) {
            withFrameNanos { time ->
                dt = time - lastTime
                if (lastTime == 0L) {
                    dt = 0
                }
                lastTime = time
                items.forEach {
                    it.recalculate(areaWidth, areaHeight, dt.toFloat())
                }
            }
        }
    }
}

@Composable
private fun Balls(items: List<BouncingBall>) {
    items.forEachIndexed { ix, ball ->
        key(ix) {
            Box(
                modifier = Modifier
                    .offset(
                        x = (ball.circle.x.value - ball.circle.r).dp,
                        y = (ball.circle.y.value - ball.circle.r).dp
                    ).size((2 * ball.circle.r).dp)
                    .background(ball.color, CircleShape)
            )
        }
    }
}

private class Circle(
    var x: MutableState<Float>,
    var y: MutableState<Float>,
    val r: Float
) {
    constructor(x: Float, y: Float, r: Float) : this(
        mutableStateOf(x), mutableStateOf(y), r
    )

    fun moveCircle(s: Float, angle: Float, width: Int, height: Int, r: Float) {
        x.value = (x.value + s * sin(angle)).coerceAtLeast(r).coerceAtMost(width.toFloat() - r)
        y.value = (y.value + s * cos(angle)).coerceAtLeast(r).coerceAtMost(height.toFloat() - r)
    }
}

private fun calculatePosition(circle: Circle, boundingWidth: Int, boundingHeight: Int): Position {
    val southmost = circle.y.value + circle.r
    val northmost = circle.y.value - circle.r
    val westmost = circle.x.value - circle.r
    val eastmost = circle.x.value + circle.r

    return when {
        southmost >= boundingHeight -> Position.TOUCHES_SOUTH
        northmost <= 0 -> Position.TOUCHES_NORTH
        eastmost >= boundingWidth -> Position.TOUCHES_EAST
        westmost <= 0 -> Position.TOUCHES_WEST
        else -> Position.INSIDE
    }
}

private enum class Position {
    INSIDE,
    TOUCHES_SOUTH,
    TOUCHES_NORTH,
    TOUCHES_WEST,
    TOUCHES_EAST
}

private class BouncingBall(
    val circle: Circle,
    val velocity: Float,
    var angle: Double,
    val color: Color = Color.Red
) {
    fun recalculate(width: Int, height: Int, dt: Float) {
        val position = calculatePosition(circle, width, height)

        val dtMillis = dt / 1000000

        when (position) {
            Position.TOUCHES_SOUTH -> angle = PI - angle
            Position.TOUCHES_EAST -> angle = -angle
            Position.TOUCHES_WEST -> angle = -angle
            Position.TOUCHES_NORTH -> angle = PI - angle
            Position.INSIDE -> angle
        }

        circle.moveCircle(
            velocity * (dtMillis.coerceAtMost(500f) / 1000),
            angle.toFloat(),
            width,
            height,
            circle.r
        )
    }

    companion object {
        private val random = Random(100)
        private val angles = listOf(PI / 4, -PI / 3, 3 * PI / 4, -PI / 6, -1.1 * PI)
        private val colors = listOf(Color.Red, Color.Black, Color.Green, Color.Magenta)

        private fun randomOffset(): Offset {
            return Offset(
                x = random.nextInt(100, 700).toFloat(),
                y = random.nextInt(100, 500).toFloat()
            )
        }

        fun createBouncingBall(offset: Offset = randomOffset()): BouncingBall {
            return BouncingBall(
                circle = Circle(x = offset.x, y = offset.y, r = random.nextInt(10, 50).toFloat()),
                velocity = random.nextInt(100, 200).toFloat(),
                angle = angles.random(),
                color = colors.random().copy(alpha = max(0.3f, random.nextFloat()))
            )
        }

        fun SnapshotStateList<BouncingBall>.setCount(count: Int) {
            when {
                size > count -> removeRange(count - 1, lastIndex)
                size < count -> addAll(generateSequence { createBouncingBall() }.take(count - size))
            }
        }
    }
}