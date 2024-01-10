package com.benoitmanhes.designsystem.res.icons.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.res.icons.CTIconPack

public val CTIconPack.Alert: ImageVector
    get() {
        if (_alert != null) {
            return _alert!!
        }
        _alert = Builder(
            name = "Alert", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF1C1B1F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(24.0f, 43.0845f)
                    curveTo(23.6462f, 43.0845f, 23.2956f, 43.0123f, 22.9483f, 42.8678f)
                    curveTo(22.601f, 42.7233f, 22.2836f, 42.5186f, 21.9962f, 42.2537f)
                    lineTo(5.7463f, 26.0038f)
                    curveTo(5.4814f, 25.7164f, 5.2767f, 25.399f, 5.1322f, 25.0517f)
                    curveTo(4.9878f, 24.7044f, 4.9155f, 24.3538f, 4.9155f, 24.0f)
                    curveTo(4.9155f, 23.6462f, 4.9862f, 23.293f, 5.1276f, 22.9406f)
                    curveTo(5.2691f, 22.5882f, 5.4753f, 22.267f, 5.7463f, 21.9769f)
                    lineTo(21.9962f, 5.7463f)
                    curveTo(22.2836f, 5.4573f, 22.601f, 5.2466f, 22.9483f, 5.1142f)
                    curveTo(23.2956f, 4.9817f, 23.6462f, 4.9155f, 24.0f, 4.9155f)
                    curveTo(24.3539f, 4.9155f, 24.707f, 4.9833f, 25.0594f, 5.1188f)
                    curveTo(25.4118f, 5.2543f, 25.733f, 5.4634f, 26.0231f, 5.7463f)
                    lineTo(42.2538f, 21.9769f)
                    curveTo(42.5366f, 22.267f, 42.7458f, 22.5867f, 42.8813f, 22.936f)
                    curveTo(43.0168f, 23.2854f, 43.0845f, 23.64f, 43.0845f, 24.0f)
                    curveTo(43.0845f, 24.3538f, 43.0183f, 24.7044f, 42.8859f, 25.0517f)
                    curveTo(42.7534f, 25.399f, 42.5427f, 25.7164f, 42.2538f, 26.0038f)
                    lineTo(26.0231f, 42.2537f)
                    curveTo(25.733f, 42.5247f, 25.4134f, 42.731f, 25.064f, 42.8724f)
                    curveTo(24.7147f, 43.0138f, 24.36f, 43.0845f, 24.0f, 43.0845f)
                    close()
                    moveTo(24.0098f, 26.1731f)
                    curveTo(24.3341f, 26.1731f, 24.6026f, 26.0644f, 24.8154f, 25.8469f)
                    curveTo(25.0282f, 25.6294f, 25.1346f, 25.36f, 25.1346f, 25.0385f)
                    verticalLineTo(16.0769f)
                    curveTo(25.1346f, 15.7555f, 25.025f, 15.486f, 24.8056f, 15.2685f)
                    curveTo(24.5863f, 15.0511f, 24.3145f, 14.9424f, 23.9902f, 14.9424f)
                    curveTo(23.666f, 14.9424f, 23.3975f, 15.0511f, 23.1847f, 15.2685f)
                    curveTo(22.9718f, 15.486f, 22.8654f, 15.7555f, 22.8654f, 16.0769f)
                    verticalLineTo(25.0385f)
                    curveTo(22.8654f, 25.36f, 22.9751f, 25.6294f, 23.1944f, 25.8469f)
                    curveTo(23.4138f, 26.0644f, 23.6856f, 26.1731f, 24.0098f, 26.1731f)
                    close()
                    moveTo(24.0f, 31.0384f)
                    curveTo(24.3423f, 31.0384f, 24.6362f, 30.9157f, 24.8817f, 30.6702f)
                    curveTo(25.1273f, 30.4247f, 25.25f, 30.1308f, 25.25f, 29.7885f)
                    curveTo(25.25f, 29.4461f, 25.1273f, 29.1523f, 24.8817f, 28.9067f)
                    curveTo(24.6362f, 28.6612f, 24.3423f, 28.5385f, 24.0f, 28.5385f)
                    curveTo(23.6577f, 28.5385f, 23.3638f, 28.6612f, 23.1183f, 28.9067f)
                    curveTo(22.8728f, 29.1523f, 22.75f, 29.4461f, 22.75f, 29.7885f)
                    curveTo(22.75f, 30.1308f, 22.8728f, 30.4247f, 23.1183f, 30.6702f)
                    curveTo(23.3638f, 30.9157f, 23.6577f, 31.0384f, 24.0f, 31.0384f)
                    close()
                }
            }
        }
            .build()
        return _alert!!
    }

private var _alert: ImageVector? = null
