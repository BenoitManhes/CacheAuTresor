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

public val CTIconPack.PositionCurrent: ImageVector
    get() {
        if (_currentlocation != null) {
            return _currentlocation!!
        }
        _currentlocation = Builder(
            name = "Current location", defaultWidth = 48.0.dp, defaultHeight =
        48.0.dp, viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(24.0f, 44.0f)
                curveTo(23.6043f, 44.0f, 23.277f, 43.8706f, 23.0183f, 43.6119f)
                curveTo(22.7595f, 43.3531f, 22.6301f, 43.0259f, 22.6301f, 42.6301f)
                verticalLineTo(40.5753f)
                curveTo(18.4597f, 40.1492f, 14.9893f, 38.551f, 12.2192f, 35.7808f)
                curveTo(9.449f, 33.0107f, 7.8508f, 29.5403f, 7.4247f, 25.3699f)
                horizontalLineTo(5.3699f)
                curveTo(4.9741f, 25.3699f, 4.6469f, 25.2405f, 4.3881f, 24.9817f)
                curveTo(4.1294f, 24.723f, 4.0f, 24.3957f, 4.0f, 24.0f)
                curveTo(4.0f, 23.6043f, 4.1294f, 23.277f, 4.3881f, 23.0183f)
                curveTo(4.6469f, 22.7595f, 4.9741f, 22.6301f, 5.3699f, 22.6301f)
                horizontalLineTo(7.4247f)
                curveTo(7.8508f, 18.4597f, 9.449f, 14.9893f, 12.2192f, 12.2192f)
                curveTo(14.9893f, 9.449f, 18.4597f, 7.8508f, 22.6301f, 7.4247f)
                verticalLineTo(5.3699f)
                curveTo(22.6301f, 4.9741f, 22.7595f, 4.6469f, 23.0183f, 4.3881f)
                curveTo(23.277f, 4.1294f, 23.6043f, 4.0f, 24.0f, 4.0f)
                curveTo(24.3957f, 4.0f, 24.723f, 4.1294f, 24.9817f, 4.3881f)
                curveTo(25.2405f, 4.6469f, 25.3699f, 4.9741f, 25.3699f, 5.3699f)
                verticalLineTo(7.4247f)
                curveTo(29.5403f, 7.8508f, 33.0107f, 9.449f, 35.7808f, 12.2192f)
                curveTo(38.551f, 14.9893f, 40.1492f, 18.4597f, 40.5753f, 22.6301f)
                horizontalLineTo(42.6301f)
                curveTo(43.0259f, 22.6301f, 43.3531f, 22.7595f, 43.6119f, 23.0183f)
                curveTo(43.8706f, 23.277f, 44.0f, 23.6043f, 44.0f, 24.0f)
                curveTo(44.0f, 24.3957f, 43.8706f, 24.723f, 43.6119f, 24.9817f)
                curveTo(43.3531f, 25.2405f, 43.0259f, 25.3699f, 42.6301f, 25.3699f)
                horizontalLineTo(40.5753f)
                curveTo(40.1492f, 29.5403f, 38.551f, 33.0107f, 35.7808f, 35.7808f)
                curveTo(33.0107f, 38.551f, 29.5403f, 40.1492f, 25.3699f, 40.5753f)
                verticalLineTo(42.6301f)
                curveTo(25.3699f, 43.0259f, 25.2405f, 43.3531f, 24.9817f, 43.6119f)
                curveTo(24.723f, 43.8706f, 24.3957f, 44.0f, 24.0f, 44.0f)
                close()
                moveTo(24.0f, 37.8813f)
                curveTo(27.8052f, 37.8813f, 31.07f, 36.519f, 33.7945f, 33.7945f)
                curveTo(36.519f, 31.07f, 37.8813f, 27.8052f, 37.8813f, 24.0f)
                curveTo(37.8813f, 20.1948f, 36.519f, 16.93f, 33.7945f, 14.2055f)
                curveTo(31.07f, 11.481f, 27.8052f, 10.1187f, 24.0f, 10.1187f)
                curveTo(20.1948f, 10.1187f, 16.93f, 11.481f, 14.2055f, 14.2055f)
                curveTo(11.481f, 16.93f, 10.1187f, 20.1948f, 10.1187f, 24.0f)
                curveTo(10.1187f, 27.8052f, 11.481f, 31.07f, 14.2055f, 33.7945f)
                curveTo(16.93f, 36.519f, 20.1948f, 37.8813f, 24.0f, 37.8813f)
                close()
                moveTo(24.0f, 30.8493f)
                curveTo(22.0822f, 30.8493f, 20.4612f, 30.1872f, 19.137f, 28.863f)
                curveTo(17.8128f, 27.5388f, 17.1507f, 25.9178f, 17.1507f, 24.0f)
                curveTo(17.1507f, 22.0822f, 17.8128f, 20.4612f, 19.137f, 19.137f)
                curveTo(20.4612f, 17.8128f, 22.0822f, 17.1507f, 24.0f, 17.1507f)
                curveTo(25.9178f, 17.1507f, 27.5388f, 17.8128f, 28.863f, 19.137f)
                curveTo(30.1872f, 20.4612f, 30.8493f, 22.0822f, 30.8493f, 24.0f)
                curveTo(30.8493f, 25.9178f, 30.1872f, 27.5388f, 28.863f, 28.863f)
                curveTo(27.5388f, 30.1872f, 25.9178f, 30.8493f, 24.0f, 30.8493f)
                close()
            }
        }
            .build()
        return _currentlocation!!
    }

private var _currentlocation: ImageVector? = null
