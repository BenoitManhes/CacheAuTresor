package com.benoitmanhes.cacheautresor.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.molecule.loading.CTLoadingView

@Composable
fun LoadingScreen(
    showLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(visible = showLoading) {
            CTLoadingView()
        }
    }
}
