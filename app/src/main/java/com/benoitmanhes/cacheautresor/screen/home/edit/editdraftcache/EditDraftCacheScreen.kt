package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun EditDraftCacheRoute(
    navigateBack: () -> Unit,
) {
    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper {
            EditDraftCacheScreen()
        }
    }
}

@Composable
private fun EditDraftCacheScreen() {
}
