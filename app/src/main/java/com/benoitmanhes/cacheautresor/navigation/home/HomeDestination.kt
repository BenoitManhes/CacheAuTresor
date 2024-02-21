package com.benoitmanhes.cacheautresor.navigation.home

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.designsystem.molecule.bottomnavbar.BottomNavBarItemEntry
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed

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
        selectedIcon = CTTheme.composed { icon.Newspaper },
        unselectedIcon = CTTheme.composed { icon.Newspaper },
        colorTheme = CTColorTheme.Default,
    )

    object Explore : HomeDestination(
        route = "explore",
        labelText = TextSpec.Resources(R.string.bottomBar_explore),
        selectedIcon = CTTheme.composed { icon.ExploreFilled },
        unselectedIcon = CTTheme.composed { icon.Explore },
        colorTheme = CTColorTheme.Default,
    )

    object Encyclopedia : HomeDestination(
        route = "encyclopedia",
        labelText = TextSpec.Resources(R.string.bottomBar_encyclopedia),
        selectedIcon = CTTheme.composed { icon.BookFilled },
        unselectedIcon = CTTheme.composed { icon.Book },
        colorTheme = CTColorTheme.Default,
    )

    object Profile : HomeDestination(
        route = "profile",
        labelText = TextSpec.Resources(R.string.bottomBar_profile),
        selectedIcon = CTTheme.composed { icon.ProfileFilled },
        unselectedIcon = CTTheme.composed { icon.Profile },
        colorTheme = CTColorTheme.Default,
    )

    object Create : HomeDestination(
        route = "create",
        labelText = TextSpec.Resources(R.string.bottomBar_create),
        selectedIcon = CTTheme.composed { icon.CreateFilled },
        unselectedIcon = CTTheme.composed { icon.Create },
        colorTheme = CTColorTheme.Cartography,
    )
}
