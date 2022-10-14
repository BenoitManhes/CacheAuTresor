package com.benoitmanhes.cacheautresor.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.cacheautresor.common.composable.bottombar.AppBottomBar
import com.benoitmanhes.cacheautresor.navigation.home.HomeDestination
import com.benoitmanhes.cacheautresor.navigation.home.HomeNavigation
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun HomeRoot() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val showFab = remember(currentRoute?.route) { currentRoute?.route == HomeRoot.fabDestination.route }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            HomeBottomBar(navController = navController, currentRoute = currentRoute)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (showFab) {
                FloatingActionButton(
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = Dimens.Elevation.small),
                    onClick = { },
                    backgroundColor = AppTheme.colors.primary,
                ) {
                    Icon(
                        imageVector = HomeRoot.fabDestination.iconSelected,
                        contentDescription = null,
                        tint = AppTheme.colors.onPrimary,
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        backgroundColor = AppTheme.colors.background,
    ) { innerPadding ->
        HomeNavigation(
            navController = navController,
            scaffoldPadding = innerPadding,
        )
    }
}

@Composable
private fun HomeBottomBar(
    navController: NavController,
    currentRoute: NavDestination?,
) {
    BottomAppBar(
        backgroundColor = AppTheme.colors.surface,
        cutoutShape = CircleShape,
        contentPadding = PaddingValues(0.dp),
    ) {
        AppBottomBar(
            bottomBarItems = HomeRoot.bottomBarDestinations,
            backgroundColor = AppTheme.colors.surface,
            selectedContentColor = AppTheme.colors.primary,
            unselectedContentColor = AppTheme.colors.placeholder,
            itemIsSelected = { it.route == currentRoute?.route },
            onItemSelected = {
                navController.navigate(it.route)
            }
        )
    }
}

private object HomeRoot {
    val bottomBarDestinations: List<HomeDestination> = listOf(
        HomeDestination.News,
        HomeDestination.Explore,
        HomeDestination.Create,
        HomeDestination.Instruments,
        HomeDestination.Profile,
    )

    val fabDestination: HomeDestination = HomeDestination.Create
}
