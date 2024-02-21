package com.benoitmanhes.designsystem.res.icons.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.res.icons.CTIconPack

internal val CTIconPack.Flag: ImageVector
    get() {
        if (_flag != null) {
            return _flag!!
        }
        _flag = Builder(
            name = "Flag",
            defaultWidth = 48.0.dp,
            defaultHeight = 48.0.dp,
            viewportWidth = 48.0f,
            viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.4912f, 42.0f)
                curveTo(11.0637f, 42.0f, 10.7083f, 41.8563f, 10.425f, 41.5688f)
                curveTo(10.1417f, 41.2812f, 10.0f, 40.925f, 10.0f, 40.5f)
                verticalLineTo(9.5f)
                curveTo(10.0f, 9.075f, 10.1437f, 8.7188f, 10.4313f, 8.4312f)
                curveTo(10.7188f, 8.1438f, 11.075f, 8.0f, 11.5f, 8.0f)
                horizontalLineTo(25.95f)
                curveTo(26.3f, 8.0f, 26.6125f, 8.1083f, 26.8875f, 8.325f)
                curveTo(27.1625f, 8.5417f, 27.3333f, 8.8333f, 27.4f, 9.2f)
                lineTo(28.1f, 12.3f)
                horizontalLineTo(38.5f)
                curveTo(38.925f, 12.3f, 39.2812f, 12.4437f, 39.5688f, 12.7313f)
                curveTo(39.8563f, 13.0188f, 40.0f, 13.375f, 40.0f, 13.8f)
                verticalLineTo(29.3f)
                curveTo(40.0f, 29.725f, 39.8563f, 30.0813f, 39.5688f, 30.3687f)
                curveTo(39.2812f, 30.6562f, 38.925f, 30.8f, 38.5f, 30.8f)
                horizontalLineTo(28.4f)
                curveTo(28.05f, 30.8f, 27.7375f, 30.6917f, 27.4625f, 30.475f)
                curveTo(27.1875f, 30.2583f, 27.0167f, 29.9833f, 26.95f, 29.65f)
                lineTo(26.25f, 26.55f)
                horizontalLineTo(13.0f)
                verticalLineTo(40.5f)
                curveTo(13.0f, 40.925f, 12.8554f, 41.2812f, 12.5662f, 41.5688f)
                curveTo(12.277f, 41.8563f, 11.9187f, 42.0f, 11.4912f, 42.0f)
                close()
            }
        }
            .build()
        return _flag!!
    }

private var _flag: ImageVector? = null
