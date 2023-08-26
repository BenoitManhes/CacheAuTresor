package com.benoitmanhes.cacheautresor.screen.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.designsystem.molecule.loading.CTLoadingView
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun LoadingScreen(
    loadingViewModel: LoadingViewModel = hiltViewModel(),
) {
    val showLoading by loadingViewModel.isLoading.collectAsStateWithLifecycle()

    AnimatedVisibility(
        visible = showLoading,
        enter = fadeIn(tween(AppConstants.Loading.animationVisibilityDuration.inWholeMilliseconds.toInt())),
        exit = fadeOut(tween(AppConstants.Loading.animationVisibilityDuration.inWholeMilliseconds.toInt())),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CTTheme.color.backgroundMask),
            contentAlignment = Alignment.Center,
        ) {
            CTLoadingView()
        }
    }
}
