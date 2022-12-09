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

val CTIconPack.Compass: ImageVector
    get() {
        if (_iconCompass != null) {
            return _iconCompass!!
        }
        _iconCompass = Builder(
            name = "IconCompass", defaultWidth = 48.0.dp,
            defaultHeight =
            48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(22.0f, 22.7f)
                lineTo(15.9f, 39.5f)
                lineTo(15.75f, 39.75f)
                lineTo(14.5f, 40.95f)
                curveTo(14.2667f, 41.1833f, 14.0167f, 41.2417f, 13.75f, 41.125f)
                curveTo(13.4833f, 41.0083f, 13.3167f, 40.8f, 13.25f, 40.5f)
                lineTo(13.0f, 38.9f)
                curveTo(13.0f, 38.9333f, 13.0167f, 38.8167f, 13.05f, 38.55f)
                lineTo(19.35f, 21.2f)
                curveTo(19.7167f, 21.5667f, 20.125f, 21.8667f, 20.575f, 22.1f)
                curveTo(21.025f, 22.3333f, 21.5f, 22.5333f, 22.0f, 22.7f)
                verticalLineTo(22.7f)
                close()
                moveTo(25.9f, 22.75f)
                curveTo(26.4f, 22.5833f, 26.8833f, 22.3833f, 27.35f, 22.15f)
                curveTo(27.8167f, 21.9167f, 28.2333f, 21.6167f, 28.6f, 21.25f)
                lineTo(34.95f, 38.55f)
                curveTo(35.0167f, 38.6833f, 35.0167f, 38.8f, 34.95f, 38.9f)
                lineTo(34.75f, 40.5f)
                curveTo(34.6833f, 40.8f, 34.5167f, 41.0083f, 34.25f, 41.125f)
                curveTo(33.9833f, 41.2417f, 33.7333f, 41.1833f, 33.5f, 40.95f)
                lineTo(32.25f, 39.75f)
                curveTo(32.15f, 39.65f, 32.1f, 39.5667f, 32.1f, 39.5f)
                lineTo(25.9f, 22.75f)
                close()
                moveTo(24.0f, 22.0f)
                curveTo(22.3333f, 22.0f, 20.9167f, 21.4167f, 19.75f, 20.25f)
                curveTo(18.5833f, 19.0833f, 18.0f, 17.6667f, 18.0f, 16.0f)
                curveTo(18.0f, 14.6f, 18.4083f, 13.3583f, 19.225f, 12.275f)
                curveTo(20.0417f, 11.1917f, 21.1333f, 10.5f, 22.5f, 10.2f)
                verticalLineTo(7.5f)
                curveTo(22.5f, 7.0667f, 22.6417f, 6.7083f, 22.925f, 6.425f)
                curveTo(23.2083f, 6.1417f, 23.5667f, 6.0f, 24.0f, 6.0f)
                curveTo(24.4333f, 6.0f, 24.7917f, 6.1417f, 25.075f, 6.425f)
                curveTo(25.3583f, 6.7083f, 25.5f, 7.0667f, 25.5f, 7.5f)
                verticalLineTo(10.2f)
                curveTo(26.8667f, 10.5f, 27.9583f, 11.1917f, 28.775f, 12.275f)
                curveTo(29.5917f, 13.3583f, 30.0f, 14.6f, 30.0f, 16.0f)
                curveTo(30.0f, 17.6667f, 29.4167f, 19.0833f, 28.25f, 20.25f)
                curveTo(27.0833f, 21.4167f, 25.6667f, 22.0f, 24.0f, 22.0f)
                close()
                moveTo(24.0f, 19.0f)
                curveTo(24.8333f, 19.0f, 25.5417f, 18.7083f, 26.125f, 18.125f)
                curveTo(26.7083f, 17.5417f, 27.0f, 16.8333f, 27.0f, 16.0f)
                curveTo(27.0f, 15.1667f, 26.7083f, 14.4583f, 26.125f, 13.875f)
                curveTo(25.5417f, 13.2917f, 24.8333f, 13.0f, 24.0f, 13.0f)
                curveTo(23.1667f, 13.0f, 22.4583f, 13.2917f, 21.875f, 13.875f)
                curveTo(21.2917f, 14.4583f, 21.0f, 15.1667f, 21.0f, 16.0f)
                curveTo(21.0f, 16.8333f, 21.2917f, 17.5417f, 21.875f, 18.125f)
                curveTo(22.4583f, 18.7083f, 23.1667f, 19.0f, 24.0f, 19.0f)
                close()
            }
        }
            .build()
        return _iconCompass!!
    }

private var _iconCompass: ImageVector? = null
