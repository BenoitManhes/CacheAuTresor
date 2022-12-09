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

val CTIconPack.EyeOpen: ImageVector
    get() {
        if (_iconSmallEye != null) {
            return _iconSmallEye!!
        }
        _iconSmallEye = Builder(
            name = "EyeOpen", defaultWidth = 16.0.dp,
            defaultHeight =
            16.0.dp,
            viewportWidth = 16.0f, viewportHeight = 16.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 0.25f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(8.0f, 10.5f)
                curveTo(8.7889f, 10.5f, 9.4583f, 10.225f, 10.0083f, 9.675f)
                curveTo(10.5583f, 9.125f, 10.8333f, 8.4556f, 10.8333f, 7.6667f)
                curveTo(10.8333f, 6.8778f, 10.5583f, 6.2083f, 10.0083f, 5.6583f)
                curveTo(9.4583f, 5.1083f, 8.7889f, 4.8333f, 8.0f, 4.8333f)
                curveTo(7.2111f, 4.8333f, 6.5417f, 5.1083f, 5.9917f, 5.6583f)
                curveTo(5.4417f, 6.2083f, 5.1667f, 6.8778f, 5.1667f, 7.6667f)
                curveTo(5.1667f, 8.4556f, 5.4417f, 9.125f, 5.9917f, 9.675f)
                curveTo(6.5417f, 10.225f, 7.2111f, 10.5f, 8.0f, 10.5f)
                close()
                moveTo(8.0f, 9.5333f)
                curveTo(7.4778f, 9.5333f, 7.0361f, 9.3528f, 6.675f, 8.9917f)
                curveTo(6.3139f, 8.6306f, 6.1333f, 8.1889f, 6.1333f, 7.6667f)
                curveTo(6.1333f, 7.1444f, 6.3139f, 6.7028f, 6.675f, 6.3417f)
                curveTo(7.0361f, 5.9805f, 7.4778f, 5.8f, 8.0f, 5.8f)
                curveTo(8.5222f, 5.8f, 8.9639f, 5.9805f, 9.325f, 6.3417f)
                curveTo(9.6861f, 6.7028f, 9.8667f, 7.1444f, 9.8667f, 7.6667f)
                curveTo(9.8667f, 8.1889f, 9.6861f, 8.6306f, 9.325f, 8.9917f)
                curveTo(8.9639f, 9.3528f, 8.5222f, 9.5333f, 8.0f, 9.5333f)
                close()
                moveTo(8.0f, 12.6667f)
                curveTo(6.4667f, 12.6667f, 5.0694f, 12.25f, 3.8083f, 11.4167f)
                curveTo(2.5472f, 10.5833f, 1.5722f, 9.4833f, 0.8833f, 8.1167f)
                curveTo(0.85f, 8.0611f, 0.825f, 7.9944f, 0.8083f, 7.9167f)
                curveTo(0.7917f, 7.8389f, 0.7833f, 7.7555f, 0.7833f, 7.6667f)
                curveTo(0.7833f, 7.5778f, 0.7917f, 7.4944f, 0.8083f, 7.4167f)
                curveTo(0.825f, 7.3389f, 0.85f, 7.2722f, 0.8833f, 7.2167f)
                curveTo(1.5722f, 5.85f, 2.5472f, 4.75f, 3.8083f, 3.9167f)
                curveTo(5.0694f, 3.0833f, 6.4667f, 2.6667f, 8.0f, 2.6667f)
                curveTo(9.5333f, 2.6667f, 10.9306f, 3.0833f, 12.1917f, 3.9167f)
                curveTo(13.4528f, 4.75f, 14.4278f, 5.85f, 15.1167f, 7.2167f)
                curveTo(15.15f, 7.2722f, 15.175f, 7.3389f, 15.1917f, 7.4167f)
                curveTo(15.2083f, 7.4944f, 15.2167f, 7.5778f, 15.2167f, 7.6667f)
                curveTo(15.2167f, 7.7555f, 15.2083f, 7.8389f, 15.1917f, 7.9167f)
                curveTo(15.175f, 7.9944f, 15.15f, 8.0611f, 15.1167f, 8.1167f)
                curveTo(14.4278f, 9.4833f, 13.4528f, 10.5833f, 12.1917f, 11.4167f)
                curveTo(10.9306f, 12.25f, 9.5333f, 12.6667f, 8.0f, 12.6667f)
                close()
                moveTo(8.0f, 11.6667f)
                curveTo(9.3444f, 11.6667f, 10.5806f, 11.3028f, 11.7083f, 10.575f)
                curveTo(12.8361f, 9.8472f, 13.6945f, 8.8778f, 14.2833f, 7.6667f)
                curveTo(13.6945f, 6.4556f, 12.8361f, 5.4861f, 11.7083f, 4.7583f)
                curveTo(10.5806f, 4.0306f, 9.3444f, 3.6667f, 8.0f, 3.6667f)
                curveTo(6.6556f, 3.6667f, 5.4194f, 4.0306f, 4.2917f, 4.7583f)
                curveTo(3.1639f, 5.4861f, 2.3f, 6.4556f, 1.7f, 7.6667f)
                curveTo(2.3f, 8.8778f, 3.1639f, 9.8472f, 4.2917f, 10.575f)
                curveTo(5.4194f, 11.3028f, 6.6556f, 11.6667f, 8.0f, 11.6667f)
                close()
            }
        }
            .build()
        return _iconSmallEye!!
    }

private var _iconSmallEye: ImageVector? = null
