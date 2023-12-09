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

public val CTIconPack.Chevron: ImageVector
    get() {
        if (_chevron != null) {
            return _chevron!!
        }
        _chevron = Builder(
            name = "Chevron",
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
                moveTo(25.8923f, 24.0f)
                lineTo(17.7462f, 15.8538f)
                curveTo(17.4692f, 15.5769f, 17.3276f, 15.2288f, 17.3212f, 14.8096f)
                curveTo(17.3147f, 14.3904f, 17.4564f, 14.0359f, 17.7462f, 13.7462f)
                curveTo(18.0359f, 13.4564f, 18.3872f, 13.3115f, 18.8f, 13.3115f)
                curveTo(19.2128f, 13.3115f, 19.564f, 13.4564f, 19.8538f, 13.7462f)
                lineTo(28.8422f, 22.7346f)
                curveTo(29.0294f, 22.9218f, 29.1615f, 23.1192f, 29.2384f, 23.3269f)
                curveTo(29.3153f, 23.5346f, 29.3538f, 23.7589f, 29.3538f, 24.0f)
                curveTo(29.3538f, 24.241f, 29.3153f, 24.4654f, 29.2384f, 24.673f)
                curveTo(29.1615f, 24.8807f, 29.0294f, 25.0782f, 28.8422f, 25.2653f)
                lineTo(19.8538f, 34.2538f)
                curveTo(19.5769f, 34.5307f, 19.2288f, 34.6724f, 18.8096f, 34.6788f)
                curveTo(18.3904f, 34.6852f, 18.0359f, 34.5435f, 17.7462f, 34.2538f)
                curveTo(17.4564f, 33.964f, 17.3115f, 33.6128f, 17.3115f, 33.2f)
                curveTo(17.3115f, 32.7872f, 17.4564f, 32.4359f, 17.7462f, 32.1462f)
                lineTo(25.8923f, 24.0f)
                close()
            }
        }
            .build()
        return _chevron!!
    }

private var _chevron: ImageVector? = null
