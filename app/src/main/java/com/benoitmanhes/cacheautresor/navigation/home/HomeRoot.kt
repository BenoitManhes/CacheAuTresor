package com.benoitmanhes.cacheautresor.navigation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.benoitmanhes.designsystem.molecule.bottomnavbar.BottomBarFloatingButton
import com.benoitmanhes.designsystem.molecule.bottomnavbar.CTBottomNavBar
import com.benoitmanhes.designsystem.theme.CTTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeRoot(
    showErrorSnackBar: (errorMsg: String) -> Unit,
) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val showFab = remember(currentRoute?.route) { currentRoute?.route == HomeRoot.fabDestination.route }
    val showBottomBar = remember(currentRoute?.route) {
        currentRoute?.route in HomeRoot.bottomBarDestinations.map { it.route }
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
                BottomBarFloatingButton(
                    icon = HomeRoot.fabDestination.selectedIcon,
                    onClick = {},
                )
            }
        },
        isFloatingActionButtonDocked = true,
        backgroundColor = CTTheme.color.background,
    ) { innerPadding ->
        HomeNavigation(
            navController = navController,
            scaffoldPadding = innerPadding,
            showSnackbar = showErrorSnackBar,
        )
    }
    Icons.Rounded.Add
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
