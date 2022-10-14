package com.benoitmanhes.cacheautresor.screen.authentication

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.cacheautresor.navigation.unauthenticated.AuthNavigation
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnAuthenticatedRoot(
    navController: NavHostController = rememberNavController(),
) {

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = AppTheme.colors.background,
    ) {
        AuthNavigation(navController = navController)
    }
}