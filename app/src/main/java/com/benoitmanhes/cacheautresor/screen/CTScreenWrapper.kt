package com.benoitmanhes.cacheautresor.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogView
import com.benoitmanhes.cacheautresor.screen.loading.LoadingScreen
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetView
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarView
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CTScreenWrapper(
    bottomPadding: Int = 0,
    darkStatusBarIcons: Boolean = true,
    content: ComposableContent,
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController, darkStatusBarIcons) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = darkStatusBarIcons,
        )
        onDispose {}
    }

    content()
    SnackbarView(bottomPadding)
    LoadingScreen()
    AlertDialogView()
    ModalBottomSheetView()
}

@Composable
fun CTScreenWrapper(
    colorTheme: CTColorTheme,
    bottomPadding: Int = 0,
    darkStatusBarIcons: Boolean = true,
    content: ComposableContent,
) {
    CTTheme(colorTheme) {
        CTScreenWrapper(
            bottomPadding = bottomPadding,
            darkStatusBarIcons = darkStatusBarIcons,
            content = content,
        )
    }
}
