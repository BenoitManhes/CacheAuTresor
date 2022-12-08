package com.benoitmanhes.designsystem.res.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.designsystem.res.icons.iconpack.EyeClose
import com.benoitmanhes.designsystem.res.icons.iconpack.EyeOpen

internal object CTIconPack

@Suppress("ObjectPropertyName")
private var _CTIcons: List<ImageVector>? = null

internal val CTIconPack.CTIcons: List<ImageVector>
    get() {
        if (_CTIcons != null) {
            return _CTIcons!!
        }
        _CTIcons = listOf(
            EyeClose,
            EyeOpen,
        )
        return _CTIcons!!
    }