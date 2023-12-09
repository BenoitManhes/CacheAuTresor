package com.benoitmanhes.cacheautresor.navigation.authenticated

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.benoitmanhes.designsystem.theme.CTTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthenticatedRoot(
    navController: NavHostController = rememberAnimatedNavController(),
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = CTTheme.color.rootBackground,
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = HomeDestination.route,
        ) {
            mainGraph()
        }
    }
}
