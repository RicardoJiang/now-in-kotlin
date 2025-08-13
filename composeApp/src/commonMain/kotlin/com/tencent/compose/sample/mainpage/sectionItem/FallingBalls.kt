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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
internal fun FallingBalls() {
    val game = remember { Game() }
    val density = LocalDensity.current
    Column {
        Text(
            "Catch balls!${if (game.finished) " Game over!" else ""}",
            fontSize = 20.sp,
            color = Color(218, 120, 91)
        )
        Text(
            "Score: ${game.score} Time: ${game.elapsed / 1_000_000} Blocks: ${game.numBlocks.toInt()}",
            fontSize = 20.sp
        )
        Row {
            if (!game.started) {
                Slider(
                    value = game.numBlocks / 20f,
                    onValueChange = { game.numBlocks = (it * 20f).coerceAtLeast(1f) },
                    modifier = Modifier.width(250.dp)
                )
            }
            Button(
                onClick = {
                    game.started = !game.started
                    if (game.started) {
                        game.start()
                    }
                }
            ) {
                Text(if (game.started) "Stop" else "Start", fontSize = 25.sp)
            }
        }
        if (game.started) {
            Box(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .onSizeChanged {
                        with(density) {
                            game.width = it.width.toDp()
                            game.height = it.height.toDp()
                        }
                    }
            ) {
                game.pieces.forEachIndexed { index, piece -> Piece(index, piece) }
            }
        }

        LaunchedEffect(Unit) {
            while (true) {
                var previousTimeNanos = withFrameNanos { it }
                withFrameNanos {
                    if (game.started && !game.paused && !game.finished) {
                        game.update((it - previousTimeNanos).coerceAtLeast(0))
                        previousTimeNanos = it
                    }
                }
            }
        }
    }
}

@Composable
internal fun Piece(index: Int, piece: PieceData) {
    val boxSize = 40.dp
    Box(
        Modifier
            .offset(boxSize * index * 5 / 3, piece.position.dp)
            .shadow(30.dp)
            .clip(CircleShape)
    ) {
        Box(
            Modifier
                .size(boxSize, boxSize)
                .background(if (piece.clicked) Color.Gray else piece.color)
                .clickable(onClick = { piece.click() })
        )
    }
}

data class PieceData(val game: Game, val velocity: Float, val color: Color) {
    var clicked by mutableStateOf(false)
    var position by mutableStateOf(0f)

    fun update(dt: Long) {
        if (clicked) return
        val delta = (dt / 1E8 * velocity).toFloat()
        position = if (position < game.height.value) position + delta else 0f
    }

    fun click() {
        if (!clicked && !game.paused) {
            clicked = true
            game.clicked(this)
        }
    }
}

class Game {
    private val colors = arrayOf(
        Color.Red, Color.Blue, Color.Cyan,
        Color.Magenta, Color.Yellow, Color.Black
    )

    var width by mutableStateOf(0.dp)
    var height by mutableStateOf(0.dp)

    var pieces = mutableStateListOf<PieceData>()
        private set

    var elapsed by mutableStateOf(0L)
    var score by mutableStateOf(0)
    private var clicked by mutableStateOf(0)

    var started by mutableStateOf(false)
    var paused by mutableStateOf(false)
    var finished by mutableStateOf(false)

    var numBlocks by mutableStateOf(5f)

    fun start() {
        clicked = 0
        started = true
        finished = false
        paused = false
        pieces.clear()
        repeat(numBlocks.toInt()) { index ->
            pieces.add(
                PieceData(
                    this,
                    index * 1.5f + 5f,
                    colors[index % colors.size]
                ).also { piece ->
                    piece.position = Random.nextDouble(0.0, 100.0).toFloat()
                })
        }
    }

    fun update(deltaTimeNanos: Long) {
        elapsed += deltaTimeNanos
        pieces.forEach { it.update(deltaTimeNanos) }
    }

    fun clicked(piece: PieceData) {
        score += piece.velocity.toInt()
        clicked++
        if (clicked == numBlocks.toInt()) {
            finished = true
        }
    }
}
