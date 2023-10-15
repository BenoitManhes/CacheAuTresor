package com.benoitmanhes.cacheautresor.screen

import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.screen.loading.LoadingScreen
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetView
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarView
import com.benoitmanhes.designsystem.utils.ComposableContent

@Composable
fun CTScreenWrapper(
    bottomPadding: Int = 0,
    content: ComposableContent,
) {
    content()
    SnackbarView(bottomPadding)
    LoadingScreen()
    ModalBottomSheetView()
}
