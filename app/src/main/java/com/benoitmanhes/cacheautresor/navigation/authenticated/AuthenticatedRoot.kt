package com.benoitmanhes.cacheautresor.navigation.authenticated

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.cacheautresor.navigation.animation.DestinationAnimation
import com.benoitmanhes.designsystem.theme.CTTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticatedRoot(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier
            .background(CTTheme.color.backgroundRoot)
            .navigationBarsPadding(),
        containerColor = CTTheme.color.backgroundRoot,
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeRootDestination.route,
            enterTransition = {
                DestinationAnimation.getEnterTransitionFromRoute(targetState.destination.route)(this)
            },
            exitTransition = {
                DestinationAnimation.getExitTransitionFromRoute(targetState.destination.route)(this)
            },
            popEnterTransition = {
                DestinationAnimation.getPopEnterTransitionFromRoute(initialState.destination.route)(this)
            },
            popExitTransition = {
                DestinationAnimation.getPopExitTransitionFromRoute(initialState.destination.route)(this)
            },
        ) {
            mainGraph()
        }
    }
}
