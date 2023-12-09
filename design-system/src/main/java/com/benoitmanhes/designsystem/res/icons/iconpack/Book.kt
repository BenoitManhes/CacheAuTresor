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

val CTIconPack.Book: ImageVector
    get() {
        if (_book != null) {
            return _book!!
        }
        _book = Builder(
            name = "Book",
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
                moveTo(11.9999f, 31.6886f)
                curveTo(12.4538f, 31.3783f, 12.9403f, 31.1475f, 13.4595f, 30.9962f)
                curveTo(13.9788f, 30.8449f, 14.5307f, 30.7693f, 15.1154f, 30.7693f)
                horizontalLineTo(16.3846f)
                verticalLineTo(8.0f)
                horizontalLineTo(15.5f)
                curveTo(14.5384f, 8.0f, 13.7147f, 8.343f, 13.0288f, 9.0289f)
                curveTo(12.3429f, 9.7148f, 11.9999f, 10.5385f, 11.9999f, 11.5001f)
                verticalLineTo(31.6886f)
                close()
                moveTo(19.3845f, 30.7693f)
                horizontalLineTo(36.0f)
                verticalLineTo(8.6155f)
                curveTo(36.0f, 8.436f, 35.9422f, 8.2885f, 35.8268f, 8.1731f)
                curveTo(35.7114f, 8.0577f, 35.564f, 8.0f, 35.3845f, 8.0f)
                horizontalLineTo(19.3845f)
                verticalLineTo(30.7693f)
                close()
                moveTo(15.1154f, 43.0f)
                curveTo(13.4256f, 43.0f, 11.984f, 42.4032f, 10.7904f, 41.2096f)
                curveTo(9.5968f, 40.016f, 9.0f, 38.5743f, 9.0f, 36.8846f)
                verticalLineTo(11.5001f)
                curveTo(9.0f, 9.6949f, 9.632f, 8.1603f, 10.8961f, 6.8962f)
                curveTo(12.1602f, 5.6321f, 13.6949f, 5.0001f, 15.5f, 5.0001f)
                horizontalLineTo(35.3845f)
                curveTo(36.3819f, 5.0001f, 37.2339f, 5.3533f, 37.9403f, 6.0597f)
                curveTo(38.6467f, 6.7661f, 38.9999f, 7.618f, 38.9999f, 8.6155f)
                verticalLineTo(32.8962f)
                curveTo(38.9999f, 33.1372f, 38.9076f, 33.3532f, 38.7229f, 33.5442f)
                curveTo(38.5383f, 33.7352f, 38.2396f, 33.9192f, 37.8268f, 34.0961f)
                curveTo(37.2448f, 34.3038f, 36.7755f, 34.6564f, 36.4191f, 35.1538f)
                curveTo(36.0627f, 35.6513f, 35.8845f, 36.2282f, 35.8845f, 36.8846f)
                curveTo(35.8845f, 37.5282f, 36.0595f, 38.1039f, 36.4095f, 38.6116f)
                curveTo(36.7595f, 39.1193f, 37.2255f, 39.4731f, 37.8076f, 39.6731f)
                curveTo(38.1845f, 39.8218f, 38.4774f, 40.0295f, 38.6864f, 40.2962f)
                curveTo(38.8954f, 40.5628f, 38.9999f, 40.8513f, 38.9999f, 41.1615f)
                verticalLineTo(41.4692f)
                curveTo(38.9999f, 41.8949f, 38.8563f, 42.2564f, 38.5691f, 42.5538f)
                curveTo(38.2819f, 42.8512f, 37.9255f, 43.0f, 37.4999f, 43.0f)
                horizontalLineTo(15.1154f)
                close()
                moveTo(15.1154f, 40.0f)
                horizontalLineTo(33.8038f)
                curveTo(33.5141f, 39.5462f, 33.2884f, 39.0648f, 33.1269f, 38.5558f)
                curveTo(32.9653f, 38.0468f, 32.8846f, 37.4897f, 32.8846f, 36.8846f)
                curveTo(32.8846f, 36.3128f, 32.9602f, 35.7641f, 33.1115f, 35.2385f)
                curveTo(33.2628f, 34.7128f, 33.4935f, 34.2231f, 33.8038f, 33.7692f)
                horizontalLineTo(15.1154f)
                curveTo(14.2231f, 33.7692f, 13.4807f, 34.0737f, 12.8884f, 34.6827f)
                curveTo(12.2961f, 35.2917f, 11.9999f, 36.0256f, 11.9999f, 36.8846f)
                curveTo(11.9999f, 37.7769f, 12.2961f, 38.5192f, 12.8884f, 39.1116f)
                curveTo(13.4807f, 39.7039f, 14.2231f, 40.0f, 15.1154f, 40.0f)
                close()
            }
        }
            .build()
        return _book!!
    }

private var _book: ImageVector? = null
