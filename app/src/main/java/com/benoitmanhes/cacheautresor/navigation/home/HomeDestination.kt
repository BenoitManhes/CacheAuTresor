package com.benoitmanhes.cacheautresor.navigation.home

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.designsystem.molecule.bottomnavbar.BottomNavBarItemEntry
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.Add
import com.benoitmanhes.designsystem.res.icons.iconpack.Compass
import com.benoitmanhes.designsystem.res.icons.iconpack.Explore
import com.benoitmanhes.designsystem.res.icons.iconpack.ExploreFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Newspaper
import com.benoitmanhes.designsystem.res.icons.iconpack.Profile
import com.benoitmanhes.designsystem.res.icons.iconpack.ProfileFilled
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

sealed class HomeDestination(
    override val route: String,
    override val selectedIcon: IconSpec,
    override val unselectedIcon: IconSpec,
    override val labelText: TextSpec,
) : BottomNavBarItemEntry, CTDestination {

    object News : HomeDestination(
        route = "news",
        labelText = TextSpec.Resources(R.string.bottomBar_news),
        selectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Newspaper, contentDescription = null),
        unselectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Newspaper, contentDescription = null),
    )

    object Explore : HomeDestination(
        route = "explore",
        labelText = TextSpec.Resources(R.string.bottomBar_explore),
        selectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.ExploreFilled, contentDescription = null),
        unselectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Explore, contentDescription = null),
    )

    object Instruments : HomeDestination(
        route = "instruments",
        labelText = TextSpec.Resources(R.string.bottomBar_instruments),
        selectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Compass, contentDescription = null),
        unselectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Compass, contentDescription = null),
    )

    object Profile : HomeDestination(
        route = "profile",
        labelText = TextSpec.Resources(R.string.bottomBar_profile),
        selectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.ProfileFilled, contentDescription = null),
        unselectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Profile, contentDescription = null),
    )

    object Create : HomeDestination(
        route = "create",
        labelText = TextSpec.Resources(R.string.bottomBar_create),
        selectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Add, contentDescription = null),
        unselectedIcon = IconSpec.VectorIcon(imageVector = CTIconPack.Add, contentDescription = null),
    )
}
