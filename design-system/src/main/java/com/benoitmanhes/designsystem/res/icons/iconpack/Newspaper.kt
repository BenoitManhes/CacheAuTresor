package com.benoitmanhes.designsystem.res.icons.iconpack

import com.benoitmanhes.designsystem.res.icons.CTIconPack

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val CTIconPack.Newspaper: ImageVector
    get() {
        if (_newspaper != null) {
            return _newspaper!!
        }
        _newspaper = materialIcon(name = "Rounded.Newspaper") {
            materialPath {
                moveTo(21.15f, 3.85f)
                lineToRelative(-0.82f, 0.82f)
                lineToRelative(-0.95f, -0.96f)
                curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.42f, 0.0f)
                lineTo(17.0f, 4.67f)
                lineToRelative(-0.96f, -0.96f)
                curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.42f, 0.0f)
                lineToRelative(-0.95f, 0.96f)
                lineToRelative(-0.96f, -0.96f)
                curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f)
                lineToRelative(-0.96f, 0.96f)
                lineTo(9.38f, 3.71f)
                curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.42f, 0.0f)
                lineTo(7.0f, 4.67f)
                lineTo(6.04f, 3.71f)
                curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.42f, 0.0f)
                lineTo(3.67f, 4.67f)
                lineTo(2.85f, 3.85f)
                curveTo(2.54f, 3.54f, 2.0f, 3.76f, 2.0f, 4.21f)
                verticalLineTo(19.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                lineToRelative(16.0f, 0.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(4.21f)
                curveTo(22.0f, 3.76f, 21.46f, 3.54f, 21.15f, 3.85f)
                close()
                moveTo(11.0f, 19.0f)
                horizontalLineTo(4.0f)
                verticalLineToRelative(-6.0f)
                horizontalLineToRelative(7.0f)
                verticalLineTo(19.0f)
                close()
                moveTo(20.0f, 19.0f)
                horizontalLineToRelative(-7.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(7.0f)
                verticalLineTo(19.0f)
                close()
                moveTo(20.0f, 15.0f)
                horizontalLineToRelative(-7.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(7.0f)
                verticalLineTo(15.0f)
                close()
                moveTo(20.0f, 11.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(8.0f)
                horizontalLineToRelative(16.0f)
                verticalLineTo(11.0f)
                close()
            }
        }
        return _newspaper!!
    }

private var _newspaper: ImageVector? = null
