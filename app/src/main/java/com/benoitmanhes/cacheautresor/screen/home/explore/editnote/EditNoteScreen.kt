package com.benoitmanhes.cacheautresor.screen.home.explore.editnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.designsystem.molecule.textfield.ZoneTextField
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBarAction
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditNoteRoute(
    onNavigateBack: () -> Unit,
    viewModel: EditNoteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigation.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            EditNoteNavigation.Back -> {
                keyboardController?.hide()
                onNavigateBack()
            }
        }
        viewModel.consumeNavigation()
    }

    CTScreenWrapper {
        EditNoteScreen(
            uiState = uiState,
            keyboardController = keyboardController,
            onNavigateBack = onNavigateBack,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditNoteScreen(
    uiState: EditNoteViewModelState,
    keyboardController: SoftwareKeyboardController?,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
            CTTopBar(
                title = TextSpec.Resources(R.string.editNote_topBar_title),
                navAction = CTNavAction.Close(onNavigateBack),
                trailingAction = CTTopBarAction.Save {
                    keyboardController?.hide()
                    uiState.onSaved()
                }
            )
        },
        backgroundColor = CTTheme.color.background,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(innerPadding)
                .padding(CTTheme.spacing.large)
        ) {
            ZoneTextField(
                value = uiState.note,
                onValueUpdated = uiState.onTextChanged,
            )
        }
    }
}
