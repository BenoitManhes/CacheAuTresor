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

internal val CTIconPack.Layer: ImageVector
    get() {
        if (_layer != null) {
            return _layer!!
        }
        _layer = Builder(
            name = "Layer",
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
                moveTo(24.0588f, 44.0f)
                curveTo(23.6667f, 44.0f, 23.2843f, 43.9314f, 22.9118f, 43.7941f)
                curveTo(22.5392f, 43.6569f, 22.1961f, 43.4706f, 21.8824f, 43.2353f)
                lineTo(4.7059f, 29.8824f)
                curveTo(4.2353f, 29.5294f, 4.0098f, 29.0686f, 4.0294f, 28.5f)
                curveTo(4.049f, 27.9314f, 4.2941f, 27.4706f, 4.7647f, 27.1176f)
                curveTo(5.0784f, 26.8824f, 5.4314f, 26.7647f, 5.8235f, 26.7647f)
                curveTo(6.2157f, 26.7647f, 6.5686f, 26.8824f, 6.8823f, 27.1176f)
                lineTo(24.0588f, 40.4706f)
                lineTo(41.2353f, 27.1176f)
                curveTo(41.549f, 26.8824f, 41.902f, 26.7647f, 42.2941f, 26.7647f)
                curveTo(42.6863f, 26.7647f, 43.0392f, 26.8824f, 43.3529f, 27.1176f)
                curveTo(43.8235f, 27.4706f, 44.0686f, 27.9314f, 44.0882f, 28.5f)
                curveTo(44.1078f, 29.0686f, 43.8824f, 29.5294f, 43.4118f, 29.8824f)
                lineTo(26.2353f, 43.2353f)
                curveTo(25.9216f, 43.4706f, 25.5784f, 43.6569f, 25.2059f, 43.7941f)
                curveTo(24.8333f, 43.9314f, 24.451f, 44.0f, 24.0588f, 44.0f)
                close()
                moveTo(24.0588f, 35.0588f)
                curveTo(23.6667f, 35.0588f, 23.2843f, 34.9902f, 22.9118f, 34.8529f)
                curveTo(22.5392f, 34.7157f, 22.1961f, 34.5294f, 21.8824f, 34.2941f)
                lineTo(4.6471f, 20.9412f)
                curveTo(4.4118f, 20.7451f, 4.2451f, 20.5294f, 4.1471f, 20.2941f)
                curveTo(4.049f, 20.0588f, 4.0f, 19.8039f, 4.0f, 19.5294f)
                curveTo(4.0f, 19.2549f, 4.049f, 19.0f, 4.1471f, 18.7647f)
                curveTo(4.2451f, 18.5294f, 4.4118f, 18.3137f, 4.6471f, 18.1176f)
                lineTo(21.8824f, 4.7647f)
                curveTo(22.1961f, 4.5294f, 22.5392f, 4.3431f, 22.9118f, 4.2059f)
                curveTo(23.2843f, 4.0686f, 23.6667f, 4.0f, 24.0588f, 4.0f)
                curveTo(24.451f, 4.0f, 24.8333f, 4.0686f, 25.2059f, 4.2059f)
                curveTo(25.5784f, 4.3431f, 25.9216f, 4.5294f, 26.2353f, 4.7647f)
                lineTo(43.4706f, 18.1176f)
                curveTo(43.7059f, 18.3137f, 43.8726f, 18.5294f, 43.9706f, 18.7647f)
                curveTo(44.0686f, 19.0f, 44.1177f, 19.2549f, 44.1177f, 19.5294f)
                curveTo(44.1177f, 19.8039f, 44.0686f, 20.0588f, 43.9706f, 20.2941f)
                curveTo(43.8726f, 20.5294f, 43.7059f, 20.7451f, 43.4706f, 20.9412f)
                lineTo(26.2353f, 34.2941f)
                curveTo(25.9216f, 34.5294f, 25.5784f, 34.7157f, 25.2059f, 34.8529f)
                curveTo(24.8333f, 34.9902f, 24.451f, 35.0588f, 24.0588f, 35.0588f)
                close()
                moveTo(24.0588f, 31.5294f)
                lineTo(39.4706f, 19.5294f)
                lineTo(24.0588f, 7.5294f)
                lineTo(8.6471f, 19.5294f)
                lineTo(24.0588f, 31.5294f)
                close()
            }
        }
            .build()
        return _layer!!
    }

private var _layer: ImageVector? = null
