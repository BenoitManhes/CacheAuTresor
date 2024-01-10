package com.benoitmanhes.designsystem.res.icons.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.res.icons.CTIconPack

public val CTIconPack.Minus: ImageVector
    get() {
        if (_minus != null) {
            return _minus!!
        }
        _minus = Builder(
            name = "Minus", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF1C1B1F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(5.7455f, 25.4911f)
                    curveTo(5.251f, 25.4911f, 4.8364f, 25.3224f, 4.5019f, 24.9849f)
                    curveTo(4.1673f, 24.6474f, 4.0f, 24.2293f, 4.0f, 23.7305f)
                    curveTo(4.0f, 23.2316f, 4.1673f, 22.8185f, 4.5019f, 22.4912f)
                    curveTo(4.8364f, 22.1637f, 5.251f, 22.0f, 5.7455f, 22.0f)
                    horizontalLineTo(42.2545f)
                    curveTo(42.749f, 22.0f, 43.1636f, 22.1687f, 43.4981f, 22.5062f)
                    curveTo(43.8327f, 22.8436f, 44.0f, 23.2618f, 44.0f, 23.7606f)
                    curveTo(44.0f, 24.2594f, 43.8327f, 24.6725f, 43.4981f, 24.9999f)
                    curveTo(43.1636f, 25.3274f, 42.749f, 25.4911f, 42.2545f, 25.4911f)
                    horizontalLineTo(5.7455f)
                    close()
                }
            }
        }
            .build()
        return _minus!!
    }

private var _minus: ImageVector? = null
