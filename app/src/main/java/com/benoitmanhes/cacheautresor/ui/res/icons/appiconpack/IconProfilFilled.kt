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

public val AppIconPack.IconProfileFilled: ImageVector
    get() {
        if (_iconProfilFilled != null) {
            return _iconProfilFilled!!
        }
        _iconProfilFilled = Builder(
            name = "IconProfilFilled", defaultWidth = 48.0.dp,
            defaultHeight =
            48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.1f, 35.25f)
                curveTo(13.2f, 33.9167f, 15.275f, 32.9083f, 17.325f, 32.225f)
                curveTo(19.375f, 31.5417f, 21.6f, 31.2f, 24.0f, 31.2f)
                curveTo(26.4f, 31.2f, 28.6333f, 31.5417f, 30.7f, 32.225f)
                curveTo(32.7667f, 32.9083f, 34.85f, 33.9167f, 36.95f, 35.25f)
                curveTo(38.4167f, 33.45f, 39.4583f, 31.6333f, 40.075f, 29.8f)
                curveTo(40.6917f, 27.9667f, 41.0f, 26.0333f, 41.0f, 24.0f)
                curveTo(41.0f, 19.1667f, 39.375f, 15.125f, 36.125f, 11.875f)
                curveTo(32.875f, 8.625f, 28.8333f, 7.0f, 24.0f, 7.0f)
                curveTo(19.1667f, 7.0f, 15.125f, 8.625f, 11.875f, 11.875f)
                curveTo(8.625f, 15.125f, 7.0f, 19.1667f, 7.0f, 24.0f)
                curveTo(7.0f, 26.0333f, 7.3167f, 27.9667f, 7.95f, 29.8f)
                curveTo(8.5833f, 31.6333f, 9.6333f, 33.45f, 11.1f, 35.25f)
                close()
                moveTo(24.0f, 25.5f)
                curveTo(22.0667f, 25.5f, 20.4417f, 24.8417f, 19.125f, 23.525f)
                curveTo(17.8083f, 22.2083f, 17.15f, 20.5833f, 17.15f, 18.65f)
                curveTo(17.15f, 16.7167f, 17.8083f, 15.0917f, 19.125f, 13.775f)
                curveTo(20.4417f, 12.4583f, 22.0667f, 11.8f, 24.0f, 11.8f)
                curveTo(25.9333f, 11.8f, 27.5583f, 12.4583f, 28.875f, 13.775f)
                curveTo(30.1917f, 15.0917f, 30.85f, 16.7167f, 30.85f, 18.65f)
                curveTo(30.85f, 20.5833f, 30.1917f, 22.2083f, 28.875f, 23.525f)
                curveTo(27.5583f, 24.8417f, 25.9333f, 25.5f, 24.0f, 25.5f)
                close()
                moveTo(24.0f, 44.0f)
                curveTo(21.2f, 44.0f, 18.5833f, 43.475f, 16.15f, 42.425f)
                curveTo(13.7167f, 41.375f, 11.6f, 39.9417f, 9.8f, 38.125f)
                curveTo(8.0f, 36.3083f, 6.5833f, 34.1833f, 5.55f, 31.75f)
                curveTo(4.5167f, 29.3167f, 4.0f, 26.7167f, 4.0f, 23.95f)
                curveTo(4.0f, 21.2167f, 4.525f, 18.6333f, 5.575f, 16.2f)
                curveTo(6.625f, 13.7667f, 8.0583f, 11.65f, 9.875f, 9.85f)
                curveTo(11.6917f, 8.05f, 13.8167f, 6.625f, 16.25f, 5.575f)
                curveTo(18.6833f, 4.525f, 21.2833f, 4.0f, 24.05f, 4.0f)
                curveTo(26.7833f, 4.0f, 29.3667f, 4.525f, 31.8f, 5.575f)
                curveTo(34.2333f, 6.625f, 36.35f, 8.05f, 38.15f, 9.85f)
                curveTo(39.95f, 11.65f, 41.375f, 13.7667f, 42.425f, 16.2f)
                curveTo(43.475f, 18.6333f, 44.0f, 21.2333f, 44.0f, 24.0f)
                curveTo(44.0f, 26.7333f, 43.475f, 29.3167f, 42.425f, 31.75f)
                curveTo(41.375f, 34.1833f, 39.95f, 36.3083f, 38.15f, 38.125f)
                curveTo(36.35f, 39.9417f, 34.2333f, 41.375f, 31.8f, 42.425f)
                curveTo(29.3667f, 43.475f, 26.7667f, 44.0f, 24.0f, 44.0f)
                close()
            }
        }
            .build()
        return _iconProfilFilled!!
    }

private var _iconProfilFilled: ImageVector? = null
