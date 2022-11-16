package com.benoitmanhes.cacheautresor.ui.res.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconCompass
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExplore
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExploreFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconFavorite
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconFavoriteFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconHome
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconHomeFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconLogo
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfile
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfileFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconSmallEye
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconSmallEyeClose
import kotlin.collections.List as ____KtList

public object AppIconPack

@Suppress("ObjectPropertyName")
private var _AppIcons: ____KtList<ImageVector>? = null

public val AppIconPack.AppIcons: ____KtList<ImageVector>
    get() {
        if (_AppIcons != null) {
            return _AppIcons!!
        }
        _AppIcons = listOf(
            IconCompass, IconExplore, IconLogo, IconSmallEye, IconProfile,
            IconProfileFilled, IconFavorite, IconHomeFilled, IconSmallEyeClose, IconExploreFilled,
            IconHome, IconFavoriteFilled
        )
        return _AppIcons!!
    }
