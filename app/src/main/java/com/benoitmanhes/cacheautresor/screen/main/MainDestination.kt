package com.benoitmanhes.cacheautresor.screen.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomBarItem
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconCompass
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExplore
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconExploreFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconHome
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconHomeFilled
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfile
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconProfileFilled

sealed class MainDestination(
    val route: String,
    @StringRes override val labelRes: Int,
    override val iconSelected: ImageVector,
    override val iconUnselected: ImageVector,
) : BottomBarItem {
    object News : MainDestination(
        route = "news",
        labelRes = R.string.bottomBar_news,
        iconSelected = Icons.Rounded.Newspaper,
        iconUnselected = Icons.Rounded.Newspaper,
    )

    object Explore : MainDestination(
        route = "explore",
        labelRes = R.string.bottomBar_explore,
        iconSelected = AppIconPack.IconExploreFilled,
        iconUnselected = AppIconPack.IconExplore,
    )

    object Instruments : MainDestination(
        route = "instruments",
        labelRes = R.string.bottomBar_instruments,
        iconSelected = AppIconPack.IconCompass,
        iconUnselected = AppIconPack.IconCompass,
    )

    object Profile : MainDestination(
        route = "profile",
        labelRes = R.string.bottomBar_profile,
        iconSelected = AppIconPack.IconProfileFilled,
        iconUnselected = AppIconPack.IconProfile,
    )

    object Create : MainDestination(
        route = "create",
        labelRes = R.string.bottomBar_create,
        iconSelected = Icons.Rounded.Add,
        iconUnselected = Icons.Rounded.Add,
    )
}
