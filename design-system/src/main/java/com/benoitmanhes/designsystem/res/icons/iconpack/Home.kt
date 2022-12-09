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

public val CTIconPack.Home: ImageVector
    get() {
        if (_iconHome != null) {
            return _iconHome!!
        }
        _iconHome = Builder(
            name = "IconHome", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(21.4f, 39.0f)
                horizontalLineTo(12.45f)
                curveTo(12.05f, 39.0f, 11.708f, 38.858f, 11.425f, 38.575f)
                curveTo(11.142f, 38.292f, 11.0f, 37.95f, 11.0f, 37.55f)
                verticalLineTo(23.2f)
                horizontalLineTo(8.35f)
                curveTo(7.983f, 23.2f, 7.742f, 23.042f, 7.625f, 22.725f)
                curveTo(7.508f, 22.408f, 7.583f, 22.133f, 7.85f, 21.9f)
                lineTo(23.05f, 8.2f)
                curveTo(23.317f, 7.967f, 23.633f, 7.85f, 24.0f, 7.85f)
                curveTo(24.367f, 7.85f, 24.667f, 7.95f, 24.9f, 8.15f)
                lineTo(33.25f, 15.55f)
                verticalLineTo(11.35f)
                curveTo(33.25f, 11.017f, 33.358f, 10.742f, 33.575f, 10.525f)
                curveTo(33.792f, 10.308f, 34.05f, 10.2f, 34.35f, 10.2f)
                horizontalLineTo(35.25f)
                curveTo(35.583f, 10.2f, 35.858f, 10.308f, 36.075f, 10.525f)
                curveTo(36.292f, 10.742f, 36.4f, 11.017f, 36.4f, 11.35f)
                verticalLineTo(18.45f)
                lineTo(40.15f, 21.9f)
                curveTo(40.417f, 22.133f, 40.492f, 22.408f, 40.375f, 22.725f)
                curveTo(40.258f, 23.042f, 40.017f, 23.2f, 39.65f, 23.2f)
                horizontalLineTo(37.0f)
                verticalLineTo(37.55f)
                curveTo(37.0f, 37.95f, 36.858f, 38.292f, 36.575f, 38.575f)
                curveTo(36.292f, 38.858f, 35.95f, 39.0f, 35.55f, 39.0f)
                horizontalLineTo(26.6f)
                verticalLineTo(27.75f)
                horizontalLineTo(21.4f)
                verticalLineTo(39.0f)
                close()
                moveTo(13.25f, 36.75f)
                horizontalLineTo(19.15f)
                verticalLineTo(26.95f)
                curveTo(19.15f, 26.55f, 19.283f, 26.208f, 19.55f, 25.925f)
                curveTo(19.817f, 25.642f, 20.167f, 25.5f, 20.6f, 25.5f)
                horizontalLineTo(27.4f)
                curveTo(27.833f, 25.5f, 28.183f, 25.642f, 28.45f, 25.925f)
                curveTo(28.717f, 26.208f, 28.85f, 26.55f, 28.85f, 26.95f)
                verticalLineTo(36.75f)
                horizontalLineTo(34.75f)
                verticalLineTo(20.05f)
                lineTo(24.0f, 10.35f)
                lineTo(13.25f, 20.05f)
                verticalLineTo(36.75f)
                close()
                moveTo(20.6f, 25.5f)
                horizontalLineTo(27.4f)
                horizontalLineTo(20.6f)
                close()
            }
        }
            .build()
        return _iconHome!!
    }

private var _iconHome: ImageVector? = null
