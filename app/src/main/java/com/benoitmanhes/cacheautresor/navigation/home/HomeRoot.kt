package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.designsystem.molecule.bottomnavbar.BottomBarFloatingButton
import com.benoitmanhes.designsystem.molecule.bottomnavbar.CTBottomNavBar
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val showFab = remember(currentRoute?.route) { currentRoute?.route == HomeRoot.fabDestination.route }
    val showBottomBar = remember(currentRoute?.route) {
        currentRoute?.route in HomeRoot.bottomBarDestinations.map { it.route }
    }

    val navigateEvent by viewModel.navigate.collectAsState()

    LaunchedEffect(navigateEvent) {
        navigateEvent ?: return@LaunchedEffect
        if (navigateEvent == true) {
            navController.navigate(EditCacheDestination.AvailableFinalPlaces.route)
        }
        viewModel.consumeNavigation()
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    animationSpec = tween(),
                    initialOffsetY = { it },
                ),
                exit = slideOutVertically(
                    animationSpec = tween(),
                    targetOffsetY = { it },
                )
            ) {
                CTBottomNavBar(
                    bottomBarItems = HomeRoot.bottomBarDestinations,
                    itemIsSelected = { currentRoute?.route == it.route },
                    onItemSelected = { navController.navigate(it.route) },
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (showFab) {
                CTTheme(CTColorTheme.Cartography) {
                    BottomBarFloatingButton(
                        icon = HomeRoot.fabDestination.selectedIcon(),
                        onClick = viewModel::showCacheCreationModal,
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        backgroundColor = CTTheme.color.background,
    ) { innerPadding ->
        HomeNavigation(
            navController = navController,
            scaffoldPadding = innerPadding,
        )
    }
}

private object HomeRoot {
    val bottomBarDestinations: List<HomeDestination> = listOf(
        HomeDestination.News,
        HomeDestination.Explore,
        HomeDestination.Create,
        HomeDestination.Encyclopedia,
        HomeDestination.Profile,
    )

    val fabDestination: HomeDestination = HomeDestination.Create
}
