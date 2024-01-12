package com.benoitmanhes.cacheautresor.navigation.home

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.designsystem.molecule.bottomnavbar.BottomNavBarItemEntry
import com.benoitmanhes.designsystem.res.icons.iconpack.Explore
import com.benoitmanhes.designsystem.res.icons.iconpack.ExploreFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Newspaper
import com.benoitmanhes.designsystem.res.icons.iconpack.Profile
import com.benoitmanhes.designsystem.res.icons.iconpack.ProfileFilled
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.res.icons.iconpack.Book
import com.benoitmanhes.designsystem.res.icons.iconpack.BookFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Create
import com.benoitmanhes.designsystem.res.icons.iconpack.CreateFilled
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

sealed class HomeDestination(
    override val route: String,
    override val selectedIcon: ComposeProvider<IconSpec>,
    override val unselectedIcon: ComposeProvider<IconSpec>,
    override val labelText: TextSpec,
    override val colorTheme: CTColorTheme,
) : BottomNavBarItemEntry, CTDestination {

    object News : HomeDestination(
        route = "news",
        labelText = TextSpec.Resources(R.string.bottomBar_news),
        selectedIcon = CTTheme.composed { icon.Newspaper.toIconSpec() },
        unselectedIcon = CTTheme.composed { icon.Newspaper.toIconSpec() },
        colorTheme = CTColorTheme.Default,
    )

    object Explore : HomeDestination(
        route = "explore",
        labelText = TextSpec.Resources(R.string.bottomBar_explore),
        selectedIcon = CTTheme.composed { icon.ExploreFilled.toIconSpec() },
        unselectedIcon = CTTheme.composed { icon.Explore.toIconSpec() },
        colorTheme = CTColorTheme.Default,
    )

    object Encyclopedia : HomeDestination(
        route = "encyclopedia",
        labelText = TextSpec.Resources(R.string.bottomBar_encyclopedia),
        selectedIcon = CTTheme.composed { icon.BookFilled.toIconSpec() },
        unselectedIcon = CTTheme.composed { icon.Book.toIconSpec() },
        colorTheme = CTColorTheme.Default,
    )

    object Profile : HomeDestination(
        route = "profile",
        labelText = TextSpec.Resources(R.string.bottomBar_profile),
        selectedIcon = CTTheme.composed { icon.ProfileFilled.toIconSpec() },
        unselectedIcon = CTTheme.composed { icon.Profile.toIconSpec() },
        colorTheme = CTColorTheme.Default,
    )

    object Create : HomeDestination(
        route = "create",
        labelText = TextSpec.Resources(R.string.bottomBar_create),
        selectedIcon = CTTheme.composed { icon.CreateFilled.toIconSpec() },
        unselectedIcon = CTTheme.composed { icon.Create.toIconSpec() },
        colorTheme = CTColorTheme.Cartography,
    )
}
