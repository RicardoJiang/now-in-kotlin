package com.jiang.nowinkotlin.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Icons.Filled.SkipPrevious: ImageVector
    get() {
        if (_skipPrevious != null) {
            return _skipPrevious!!
        }
        _skipPrevious = materialIcon(name = "Filled.SkipPrevious") {
            materialPath {
                moveTo(6.0f, 6.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(12.0f)
                lineTo(6.0f, 18.0f)
                close()
                moveTo(9.5f, 12.0f)
                lineToRelative(8.5f, 6.0f)
                lineTo(18.0f, 6.0f)
                close()
            }
        }
        return _skipPrevious!!
    }

private var _skipPrevious: ImageVector? = null


public val Icons.Filled.SkipNext: ImageVector
    get() {
        if (_skipNext != null) {
            return _skipNext!!
        }
        _skipNext = materialIcon(name = "Filled.SkipNext") {
            materialPath {
                moveTo(6.0f, 18.0f)
                lineToRelative(8.5f, -6.0f)
                lineTo(6.0f, 6.0f)
                verticalLineToRelative(12.0f)
                close()
                moveTo(16.0f, 6.0f)
                verticalLineToRelative(12.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(6.0f)
                horizontalLineToRelative(-2.0f)
                close()
            }
        }
        return _skipNext!!
    }

private var _skipNext: ImageVector? = null

public val Icons.Filled.Pause: ImageVector
    get() {
        if (_pause != null) {
            return _pause!!
        }
        _pause = materialIcon(name = "Filled.Pause") {
            materialPath {
                moveTo(6.0f, 19.0f)
                horizontalLineToRelative(4.0f)
                lineTo(10.0f, 5.0f)
                lineTo(6.0f, 5.0f)
                verticalLineToRelative(14.0f)
                close()
                moveTo(14.0f, 5.0f)
                verticalLineToRelative(14.0f)
                horizontalLineToRelative(4.0f)
                lineTo(18.0f, 5.0f)
                horizontalLineToRelative(-4.0f)
                close()
            }
        }
        return _pause!!
    }

private var _pause: ImageVector? = null

public val Icons.Filled.FastForward: ImageVector
    get() {
        if (_fastForward != null) {
            return _fastForward!!
        }
        _fastForward = materialIcon(name = "Filled.FastForward") {
            materialPath {
                moveTo(4.0f, 18.0f)
                lineToRelative(8.5f, -6.0f)
                lineTo(4.0f, 6.0f)
                verticalLineToRelative(12.0f)
                close()
                moveTo(13.0f, 6.0f)
                verticalLineToRelative(12.0f)
                lineToRelative(8.5f, -6.0f)
                lineTo(13.0f, 6.0f)
                close()
            }
        }
        return _fastForward!!
    }

private var _fastForward: ImageVector? = null

public val Icons.Filled.FastRewind: ImageVector
    get() {
        if (_fastRewind != null) {
            return _fastRewind!!
        }
        _fastRewind = materialIcon(name = "Filled.FastRewind") {
            materialPath {
                moveTo(11.0f, 18.0f)
                lineTo(11.0f, 6.0f)
                lineToRelative(-8.5f, 6.0f)
                lineToRelative(8.5f, 6.0f)
                close()
                moveTo(11.5f, 12.0f)
                lineToRelative(8.5f, 6.0f)
                lineTo(20.0f, 6.0f)
                lineToRelative(-8.5f, 6.0f)
                close()
            }
        }
        return _fastRewind!!
    }

private var _fastRewind: ImageVector? = null