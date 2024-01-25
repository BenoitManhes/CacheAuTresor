package com.benoitmanhes.cacheautresor.navigation.authenticated

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.benoitmanhes.designsystem.theme.CTTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticatedRoot(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = CTTheme.color.backgroundRoot,
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeDestination.route,
        ) {
            mainGraph()
        }
    }
}
