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

internal val CTIconPack.FavoriteFilled: ImageVector
    get() {
        if (_iconFavoriteFilled != null) {
            return _iconFavoriteFilled!!
        }
        _iconFavoriteFilled = Builder(
            name = "IconFavoriteFilled",
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
                moveTo(21.95f, 40.2f)
                lineTo(19.3f, 37.75f)
                curveTo(15.1667f, 33.9167f, 11.5833f, 30.2583f, 8.55f, 26.775f)
                curveTo(5.5167f, 23.2917f, 4.0f, 19.65f, 4.0f, 15.85f)
                curveTo(4.0f, 12.85f, 5.0083f, 10.3417f, 7.025f, 8.325f)
                curveTo(9.0417f, 6.3083f, 11.5333f, 5.3f, 14.5f, 5.3f)
                curveTo(16.2f, 5.3f, 17.8833f, 5.7083f, 19.55f, 6.525f)
                curveTo(21.2167f, 7.3417f, 22.7f, 8.6833f, 24.0f, 10.55f)
                curveTo(25.4667f, 8.6833f, 26.9833f, 7.3417f, 28.55f, 6.525f)
                curveTo(30.1167f, 5.7083f, 31.7667f, 5.3f, 33.5f, 5.3f)
                curveTo(36.4667f, 5.3f, 38.9583f, 6.3083f, 40.975f, 8.325f)
                curveTo(42.9917f, 10.3417f, 44.0f, 12.85f, 44.0f, 15.85f)
                curveTo(44.0f, 19.65f, 42.4833f, 23.2917f, 39.45f, 26.775f)
                curveTo(36.4167f, 30.2583f, 32.8333f, 33.9167f, 28.7f, 37.75f)
                lineTo(26.05f, 40.2f)
                curveTo(25.4833f, 40.7333f, 24.8f, 41.0f, 24.0f, 41.0f)
                curveTo(23.2f, 41.0f, 22.5167f, 40.7333f, 21.95f, 40.2f)
                close()
            }
        }
            .build()
        return _iconFavoriteFilled!!
    }

private var _iconFavoriteFilled: ImageVector? = null
