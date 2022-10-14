package com.benoitmanhes.cacheautresor.navigation.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomBarItem
import com.benoitmanhes.cacheautresor.navigation.AppDestination
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconCompass
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExplore
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExploreFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfile
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfileFilled

sealed class HomeDestination(
    override val route: String,
    @StringRes override val labelRes: Int,
    override val iconSelected: ImageVector,
    override val iconUnselected: ImageVector,
) : BottomBarItem, AppDestination {
    object News : HomeDestination(
        route = "news",
        labelRes = R.string.bottomBar_news,
        iconSelected = Icons.Rounded.Newspaper,
        iconUnselected = Icons.Rounded.Newspaper,
    )

    object Explore : HomeDestination(
        route = "explore",
        labelRes = R.string.bottomBar_explore,
        iconSelected = AppIconPack.IconExploreFilled,
        iconUnselected = AppIconPack.IconExplore,
    )

    object Instruments : HomeDestination(
        route = "instruments",
        labelRes = R.string.bottomBar_instruments,
        iconSelected = AppIconPack.IconCompass,
        iconUnselected = AppIconPack.IconCompass,
    )

    object Profile : HomeDestination(
        route = "profile",
        labelRes = R.string.bottomBar_profile,
        iconSelected = AppIconPack.IconProfileFilled,
        iconUnselected = AppIconPack.IconProfile,
    )

    object Create : HomeDestination(
        route = "create",
        labelRes = R.string.bottomBar_create,
        iconSelected = Icons.Rounded.Add,
        iconUnselected = Icons.Rounded.Add,
    )
}
