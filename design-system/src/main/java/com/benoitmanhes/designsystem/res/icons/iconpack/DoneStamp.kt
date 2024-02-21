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

internal val CTIconPack.DoneStamp: ImageVector
    get() {
        if (_doneStamd != null) {
            return _doneStamd!!
        }
        _doneStamd = Builder(
            name = "DoneStamd",
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
                moveTo(21.8666f, 26.0333f)
                lineTo(18.4666f, 22.55f)
                curveTo(18.1444f, 22.2056f, 17.7472f, 22.0333f, 17.275f, 22.0333f)
                curveTo(16.8027f, 22.0333f, 16.3888f, 22.2f, 16.0333f, 22.5333f)
                curveTo(15.6888f, 22.8778f, 15.5166f, 23.2889f, 15.5166f, 23.7667f)
                curveTo(15.5166f, 24.2444f, 15.6888f, 24.6555f, 16.0333f, 25.0f)
                lineTo(20.7f, 29.6333f)
                curveTo(21.0333f, 29.9667f, 21.4222f, 30.1333f, 21.8666f, 30.1333f)
                curveTo(22.3111f, 30.1333f, 22.7f, 29.9667f, 23.0333f, 29.6333f)
                lineTo(31.9666f, 20.7333f)
                curveTo(32.2999f, 20.4f, 32.4638f, 20.0f, 32.4583f, 19.5333f)
                curveTo(32.4527f, 19.0667f, 32.2777f, 18.6667f, 31.9333f, 18.3333f)
                curveTo(31.6f, 18.0222f, 31.2028f, 17.8694f, 30.7417f, 17.875f)
                curveTo(30.2805f, 17.8806f, 29.8944f, 18.0389f, 29.5833f, 18.35f)
                lineTo(21.8666f, 26.0333f)
                close()
                moveTo(16.5f, 43.7f)
                lineTo(13.4666f, 38.5334f)
                lineTo(7.4999f, 37.3f)
                curveTo(7.0889f, 37.2222f, 6.75f, 37.0028f, 6.4833f, 36.6417f)
                curveTo(6.2166f, 36.2806f, 6.1166f, 35.8945f, 6.1833f, 35.4834f)
                lineTo(6.8333f, 29.6f)
                lineTo(2.9499f, 25.1f)
                curveTo(2.6611f, 24.8f, 2.5166f, 24.4333f, 2.5166f, 24.0f)
                curveTo(2.5166f, 23.5667f, 2.6611f, 23.2f, 2.9499f, 22.9f)
                lineTo(6.8333f, 18.4333f)
                lineTo(6.1833f, 12.55f)
                curveTo(6.1166f, 12.1389f, 6.2166f, 11.7528f, 6.4833f, 11.3916f)
                curveTo(6.75f, 11.0305f, 7.0889f, 10.8111f, 7.4999f, 10.7333f)
                lineTo(13.4666f, 9.5f)
                lineTo(16.5f, 4.3f)
                curveTo(16.7222f, 3.9333f, 17.0333f, 3.6806f, 17.4333f, 3.5417f)
                curveTo(17.8333f, 3.4028f, 18.2333f, 3.4222f, 18.6333f, 3.6f)
                lineTo(24.0f, 6.0333f)
                lineTo(29.3666f, 3.6f)
                curveTo(29.7666f, 3.4222f, 30.1666f, 3.3972f, 30.5666f, 3.525f)
                curveTo(30.9666f, 3.6528f, 31.2777f, 3.9f, 31.5f, 4.2667f)
                lineTo(34.5666f, 9.5f)
                lineTo(40.5f, 10.7333f)
                curveTo(40.9111f, 10.8111f, 41.2499f, 11.0305f, 41.5166f, 11.3916f)
                curveTo(41.7833f, 11.7528f, 41.8833f, 12.1389f, 41.8166f, 12.55f)
                lineTo(41.1666f, 18.4333f)
                lineTo(45.05f, 22.9f)
                curveTo(45.3389f, 23.2f, 45.4833f, 23.5667f, 45.4833f, 24.0f)
                curveTo(45.4833f, 24.4333f, 45.3389f, 24.8f, 45.05f, 25.1f)
                lineTo(41.1666f, 29.6f)
                lineTo(41.8166f, 35.4834f)
                curveTo(41.8833f, 35.8945f, 41.7833f, 36.2806f, 41.5166f, 36.6417f)
                curveTo(41.2499f, 37.0028f, 40.9111f, 37.2222f, 40.5f, 37.3f)
                lineTo(34.5666f, 38.5334f)
                lineTo(31.5f, 43.7334f)
                curveTo(31.2777f, 44.1f, 30.9666f, 44.3472f, 30.5666f, 44.475f)
                curveTo(30.1666f, 44.6028f, 29.7666f, 44.5778f, 29.3666f, 44.4f)
                lineTo(24.0f, 41.9667f)
                lineTo(18.6333f, 44.4f)
                curveTo(18.2333f, 44.5778f, 17.8333f, 44.5972f, 17.4333f, 44.4584f)
                curveTo(17.0333f, 44.3195f, 16.7222f, 44.0667f, 16.5f, 43.7f)
                close()
                moveTo(18.7333f, 40.6667f)
                lineTo(24.0f, 38.4334f)
                lineTo(29.4f, 40.6667f)
                lineTo(32.5667f, 35.7334f)
                lineTo(38.3f, 34.3f)
                lineTo(37.7333f, 28.4667f)
                lineTo(41.6667f, 24.0f)
                lineTo(37.7333f, 19.4333f)
                lineTo(38.3f, 13.6f)
                lineTo(32.5667f, 12.2666f)
                lineTo(29.3f, 7.3333f)
                lineTo(24.0f, 9.5667f)
                lineTo(18.6f, 7.3333f)
                lineTo(15.4333f, 12.2666f)
                lineTo(9.7f, 13.6f)
                lineTo(10.2666f, 19.4333f)
                lineTo(6.3333f, 24.0f)
                lineTo(10.2666f, 28.4667f)
                lineTo(9.7f, 34.4f)
                lineTo(15.4333f, 35.7334f)
                lineTo(18.7333f, 40.6667f)
                close()
            }
        }
            .build()
        return _doneStamd!!
    }

private var _doneStamd: ImageVector? = null
