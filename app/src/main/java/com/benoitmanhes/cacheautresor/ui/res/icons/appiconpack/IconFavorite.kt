package com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack

public val AppIconPack.IconFavorite: ImageVector
    get() {
        if (_iconFavorite != null) {
            return _iconFavorite!!
        }
        _iconFavorite = Builder(
            name = "IconFavorite", defaultWidth = 48.0.dp,
            defaultHeight =
            48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
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
                moveTo(22.7f, 13.85f)
                curveTo(21.8f, 12.2167f, 20.6167f, 10.8833f, 19.15f, 9.85f)
                curveTo(17.6833f, 8.8167f, 16.1333f, 8.3f, 14.5f, 8.3f)
                curveTo(12.3f, 8.3f, 10.5f, 9.0083f, 9.1f, 10.425f)
                curveTo(7.7f, 11.8417f, 7.0f, 13.65f, 7.0f, 15.85f)
                curveTo(7.0f, 17.7833f, 7.65f, 19.8083f, 8.95f, 21.925f)
                curveTo(10.25f, 24.0417f, 11.8f, 26.1f, 13.6f, 28.1f)
                curveTo(15.4f, 30.1f, 17.2667f, 31.9583f, 19.2f, 33.675f)
                curveTo(21.1333f, 35.3917f, 22.7333f, 36.8333f, 24.0f, 38.0f)
                curveTo(25.2667f, 36.8667f, 26.8667f, 35.4333f, 28.8f, 33.7f)
                curveTo(30.7333f, 31.9667f, 32.6f, 30.0917f, 34.4f, 28.075f)
                curveTo(36.2f, 26.0583f, 37.75f, 23.9917f, 39.05f, 21.875f)
                curveTo(40.35f, 19.7583f, 41.0f, 17.75f, 41.0f, 15.85f)
                curveTo(41.0f, 13.65f, 40.2917f, 11.8417f, 38.875f, 10.425f)
                curveTo(37.4583f, 9.0083f, 35.6667f, 8.3f, 33.5f, 8.3f)
                curveTo(31.8333f, 8.3f, 30.275f, 8.8083f, 28.825f, 9.825f)
                curveTo(27.375f, 10.8417f, 26.1667f, 12.1833f, 25.2f, 13.85f)
                curveTo(25.0333f, 14.1167f, 24.85f, 14.3083f, 24.65f, 14.425f)
                curveTo(24.45f, 14.5417f, 24.2167f, 14.6f, 23.95f, 14.6f)
                curveTo(23.6833f, 14.6f, 23.4417f, 14.5417f, 23.225f, 14.425f)
                curveTo(23.0083f, 14.3083f, 22.8333f, 14.1167f, 22.7f, 13.85f)
                close()
            }
        }
            .build()
        return _iconFavorite!!
    }

private var _iconFavorite: ImageVector? = null
