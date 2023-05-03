package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBarLegacy
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CacheDetailsRoute() {
    CacheDetailsScreen()
}

@Composable
private fun CacheDetailsScreen() {
    Scaffold(
        topBar = CTTopBarLegacy(title = )
    ) {
        
    }
}

@Preview
@Composable
private fun PreviewCacheDetailsScreen() {
    CTTheme {
        CacheDetailsScreen()
    }
}
