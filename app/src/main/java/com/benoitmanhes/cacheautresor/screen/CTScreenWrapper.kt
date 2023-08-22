package com.benoitmanhes.cacheautresor.screen

import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.screen.loading.LoadingScreen
import com.benoitmanhes.designsystem.utils.ComposableContent

@Composable
fun CTScreenWrapper(
    content: ComposableContent,
) {
    content()
    LoadingScreen()
}
