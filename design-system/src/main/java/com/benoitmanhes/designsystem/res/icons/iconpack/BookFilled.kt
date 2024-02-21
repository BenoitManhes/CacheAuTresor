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

internal val CTIconPack.BookFilled: ImageVector
    get() {
        if (_bookFilled != null) {
            return _bookFilled!!
        }
        _bookFilled = Builder(
            name = "BookFilled",
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
                moveTo(15.1154f, 43.0f)
                curveTo(13.4264f, 43.0f, 11.9849f, 42.403f, 10.791f, 41.209f)
                curveTo(9.597f, 40.0151f, 9.0f, 38.5736f, 9.0f, 36.8846f)
                verticalLineTo(11.5001f)
                curveTo(9.0f, 9.6949f, 9.632f, 8.1603f, 10.8961f, 6.8962f)
                curveTo(12.1602f, 5.6321f, 13.6949f, 5.0001f, 15.5f, 5.0001f)
                horizontalLineTo(35.3845f)
                curveTo(36.3787f, 5.0001f, 37.2298f, 5.3541f, 37.9378f, 6.0621f)
                curveTo(38.6459f, 6.7701f, 38.9999f, 7.6212f, 38.9999f, 8.6155f)
                verticalLineTo(32.8962f)
                curveTo(38.9999f, 33.1335f, 38.9076f, 33.3486f, 38.7229f, 33.5415f)
                curveTo(38.5383f, 33.7343f, 38.2396f, 33.9192f, 37.8268f, 34.0961f)
                curveTo(37.2448f, 34.3038f, 36.7755f, 34.6573f, 36.4191f, 35.1565f)
                curveTo(36.0627f, 35.6557f, 35.8845f, 36.2318f, 35.8845f, 36.8846f)
                curveTo(35.8845f, 37.5256f, 36.0595f, 38.1006f, 36.4095f, 38.6096f)
                curveTo(36.7595f, 39.1186f, 37.2255f, 39.4731f, 37.8076f, 39.6731f)
                curveTo(38.1845f, 39.8218f, 38.4774f, 40.0304f, 38.6864f, 40.2988f)
                curveTo(38.8954f, 40.5672f, 38.9999f, 40.8548f, 38.9999f, 41.1615f)
                verticalLineTo(41.4692f)
                curveTo(38.9999f, 41.8924f, 38.8562f, 42.2533f, 38.5687f, 42.5519f)
                curveTo(38.2812f, 42.8506f, 37.9249f, 43.0f, 37.4999f, 43.0f)
                horizontalLineTo(15.1154f)
                close()
                moveTo(17.8852f, 30.7693f)
                curveTo(18.3104f, 30.7693f, 18.6666f, 30.6256f, 18.9537f, 30.3381f)
                curveTo(19.2409f, 30.0506f, 19.3845f, 29.6943f, 19.3845f, 29.2693f)
                verticalLineTo(9.5f)
                curveTo(19.3845f, 9.075f, 19.2407f, 8.7188f, 18.9531f, 8.4313f)
                curveTo(18.6655f, 8.1438f, 18.3091f, 8.0f, 17.8839f, 8.0f)
                curveTo(17.4587f, 8.0f, 17.1025f, 8.1438f, 16.8154f, 8.4313f)
                curveTo(16.5282f, 8.7188f, 16.3846f, 9.075f, 16.3846f, 9.5f)
                verticalLineTo(29.2693f)
                curveTo(16.3846f, 29.6943f, 16.5284f, 30.0506f, 16.816f, 30.3381f)
                curveTo(17.1036f, 30.6256f, 17.46f, 30.7693f, 17.8852f, 30.7693f)
                close()
                moveTo(15.1154f, 40.0f)
                horizontalLineTo(33.8038f)
                curveTo(33.5141f, 39.5462f, 33.2884f, 39.0634f, 33.1269f, 38.5518f)
                curveTo(32.9653f, 38.0402f, 32.8846f, 37.4844f, 32.8846f, 36.8846f)
                curveTo(32.8846f, 36.3128f, 32.9602f, 35.7641f, 33.1115f, 35.2385f)
                curveTo(33.2628f, 34.7128f, 33.4935f, 34.2231f, 33.8038f, 33.7692f)
                horizontalLineTo(15.1154f)
                curveTo(14.2231f, 33.7692f, 13.4807f, 34.0721f, 12.8884f, 34.6779f)
                curveTo(12.2961f, 35.2837f, 11.9999f, 36.0192f, 11.9999f, 36.8846f)
                curveTo(11.9999f, 37.7769f, 12.2961f, 38.5192f, 12.8884f, 39.1116f)
                curveTo(13.4807f, 39.7039f, 14.2231f, 40.0f, 15.1154f, 40.0f)
                close()
            }
        }
            .build()
        return _bookFilled!!
    }

private var _bookFilled: ImageVector? = null
