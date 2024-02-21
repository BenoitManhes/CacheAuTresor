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

internal val CTIconPack.Add: ImageVector
    get() {
        if (_add != null) {
            return _add!!
        }
        _add = Builder(
            name = "Add", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth =
            48.0f,
            viewportHeight = 48.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF1C1B1F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(21.6924f, 26.3076f)
                    horizontalLineTo(6.3077f)
                    curveTo(5.6539f, 26.3076f, 5.1058f, 26.0864f, 4.6635f, 25.6439f)
                    curveTo(4.2211f, 25.2014f, 4.0f, 24.6531f, 4.0f, 23.999f)
                    curveTo(4.0f, 23.3448f, 4.2211f, 22.7969f, 4.6635f, 22.3551f)
                    curveTo(5.1058f, 21.9133f, 5.6539f, 21.6924f, 6.3077f, 21.6924f)
                    horizontalLineTo(21.6924f)
                    verticalLineTo(6.3077f)
                    curveTo(21.6924f, 5.6539f, 21.9136f, 5.1058f, 22.3561f, 4.6635f)
                    curveTo(22.7986f, 4.2211f, 23.3469f, 4.0f, 24.001f, 4.0f)
                    curveTo(24.6552f, 4.0f, 25.2031f, 4.2211f, 25.6449f, 4.6635f)
                    curveTo(26.0867f, 5.1058f, 26.3076f, 5.6539f, 26.3076f, 6.3077f)
                    verticalLineTo(21.6924f)
                    horizontalLineTo(41.6923f)
                    curveTo(42.3461f, 21.6924f, 42.8942f, 21.9136f, 43.3365f, 22.3561f)
                    curveTo(43.7788f, 22.7986f, 44.0f, 23.3469f, 44.0f, 24.001f)
                    curveTo(44.0f, 24.6552f, 43.7788f, 25.2031f, 43.3365f, 25.6449f)
                    curveTo(42.8942f, 26.0867f, 42.3461f, 26.3076f, 41.6923f, 26.3076f)
                    horizontalLineTo(26.3076f)
                    verticalLineTo(41.6923f)
                    curveTo(26.3076f, 42.3461f, 26.0864f, 42.8942f, 25.6439f, 43.3365f)
                    curveTo(25.2014f, 43.7788f, 24.6531f, 44.0f, 23.999f, 44.0f)
                    curveTo(23.3448f, 44.0f, 22.7969f, 43.7788f, 22.3551f, 43.3365f)
                    curveTo(21.9133f, 42.8942f, 21.6924f, 42.3461f, 21.6924f, 41.6923f)
                    verticalLineTo(26.3076f)
                    close()
                }
            }
        }
            .build()
        return _add!!
    }

private var _add: ImageVector? = null
