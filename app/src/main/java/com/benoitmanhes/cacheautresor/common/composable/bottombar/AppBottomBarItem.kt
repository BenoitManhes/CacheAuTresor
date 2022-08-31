package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconCompass
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExplore
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExploreFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconFavorite
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconFavoriteFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconHome
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconHomeFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfile
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfileFilled

sealed class AppBottomBarItem : BottomBarItem {

    object Home : AppBottomBarItem() {
        override val iconSelected: ImageVector = AppIconPack.IconHomeFilled
        override val iconUnselected: ImageVector = AppIconPack.IconHome
        @StringRes override val labelRes: Int = R.string.bottomBar_home
    }

    object Favourites : AppBottomBarItem() {
        override val iconSelected: ImageVector = AppIconPack.IconFavoriteFilled
        override val iconUnselected: ImageVector = AppIconPack.IconFavorite
        @StringRes override val labelRes: Int = R.string.bottomBar_favourites
    }

    object Explore : AppBottomBarItem() {
        override val iconSelected: ImageVector = AppIconPack.IconExploreFilled
        override val iconUnselected: ImageVector = AppIconPack.IconExplore
        @StringRes override val labelRes: Int = R.string.bottomBar_explore
    }

    object Instruments : AppBottomBarItem() {
        override val iconSelected: ImageVector = AppIconPack.IconCompass
        override val iconUnselected: ImageVector = AppIconPack.IconCompass
        @StringRes override val labelRes: Int = R.string.bottomBar_instruments
    }

    object Profile : AppBottomBarItem() {
        override val iconSelected: ImageVector = AppIconPack.IconProfileFilled
        override val iconUnselected: ImageVector = AppIconPack.IconProfile
        @StringRes override val labelRes: Int = R.string.bottomBar_profile
    }
}
