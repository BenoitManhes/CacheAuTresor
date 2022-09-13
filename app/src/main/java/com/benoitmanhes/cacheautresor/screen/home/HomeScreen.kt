package com.benoitmanhes.cacheautresor.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.common.composable.button.FilledButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = { HomeBottomBar() },
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            FilledButton(text = "Se déconnecter", onClick = { viewModel.logout() })
        }
    }
}

@Composable
private fun HomeBottomBar() {
    BottomAppBar() {
        
    }
}