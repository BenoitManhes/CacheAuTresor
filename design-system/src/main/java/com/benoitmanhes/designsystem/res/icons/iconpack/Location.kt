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

internal val CTIconPack.Location: ImageVector
    get() {
        if (_location != null) {
            return _location!!
        }
        _location = Builder(
            name = "Location",
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
                moveTo(24.0f, 23.5f)
                curveTo(24.9667f, 23.5f, 25.7917f, 23.1583f, 26.475f, 22.475f)
                curveTo(27.1583f, 21.7917f, 27.5f, 20.9667f, 27.5f, 20.0f)
                curveTo(27.5f, 19.0333f, 27.1583f, 18.2083f, 26.475f, 17.525f)
                curveTo(25.7917f, 16.8417f, 24.9667f, 16.5f, 24.0f, 16.5f)
                curveTo(23.0333f, 16.5f, 22.2083f, 16.8417f, 21.525f, 17.525f)
                curveTo(20.8417f, 18.2083f, 20.5f, 19.0333f, 20.5f, 20.0f)
                curveTo(20.5f, 20.9667f, 20.8417f, 21.7917f, 21.525f, 22.475f)
                curveTo(22.2083f, 23.1583f, 23.0333f, 23.5f, 24.0f, 23.5f)
                close()
                moveTo(24.0f, 40.05f)
                curveTo(28.4333f, 36.0167f, 31.7083f, 32.3583f, 33.825f, 29.075f)
                curveTo(35.9417f, 25.7917f, 37.0f, 22.9f, 37.0f, 20.4f)
                curveTo(37.0f, 16.4667f, 35.7417f, 13.25f, 33.225f, 10.75f)
                curveTo(30.7083f, 8.25f, 27.6333f, 7.0f, 24.0f, 7.0f)
                curveTo(20.3667f, 7.0f, 17.2917f, 8.25f, 14.775f, 10.75f)
                curveTo(12.2583f, 13.25f, 11.0f, 16.4667f, 11.0f, 20.4f)
                curveTo(11.0f, 22.9f, 12.0833f, 25.7917f, 14.25f, 29.075f)
                curveTo(16.4167f, 32.3583f, 19.6667f, 36.0167f, 24.0f, 40.05f)
                close()
                moveTo(24.05f, 43.4f)
                curveTo(23.8167f, 43.4f, 23.6f, 43.3667f, 23.4f, 43.3f)
                curveTo(23.2f, 43.2333f, 23.0167f, 43.1333f, 22.85f, 43.0f)
                curveTo(17.8833f, 38.6333f, 14.1667f, 34.5833f, 11.7f, 30.85f)
                curveTo(9.2333f, 27.1167f, 8.0f, 23.6333f, 8.0f, 20.4f)
                curveTo(8.0f, 15.4f, 9.6083f, 11.4167f, 12.825f, 8.45f)
                curveTo(16.0417f, 5.4833f, 19.7667f, 4.0f, 24.0f, 4.0f)
                curveTo(28.2333f, 4.0f, 31.9583f, 5.4833f, 35.175f, 8.45f)
                curveTo(38.3917f, 11.4167f, 40.0f, 15.4f, 40.0f, 20.4f)
                curveTo(40.0f, 23.6333f, 38.7667f, 27.1167f, 36.3f, 30.85f)
                curveTo(33.8333f, 34.5833f, 30.1167f, 38.6333f, 25.15f, 43.0f)
                curveTo(24.9833f, 43.1333f, 24.8083f, 43.2333f, 24.625f, 43.3f)
                curveTo(24.4417f, 43.3667f, 24.25f, 43.4f, 24.05f, 43.4f)
                close()
            }
        }
            .build()
        return _location!!
    }

private var _location: ImageVector? = null
