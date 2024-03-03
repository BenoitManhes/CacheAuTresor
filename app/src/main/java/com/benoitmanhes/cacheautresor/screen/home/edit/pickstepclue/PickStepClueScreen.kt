package com.benoitmanhes.cacheautresor.screen.home.edit.pickstepclue

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun PickStepClueRoute(
    navigateBack: () -> Unit,
    viewModel: PickStepClueViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigateBack.collectAsState()

    LaunchedEffect(navigation) {
        navigation ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper(darkStatusBarIcons = false) {
            PickStepClueScreen(
                uiState = uiState,
                navigateBack = navigateBack,
            )
        }
    }
}

@Composable
private fun PickStepClueScreen(
    uiState: PickStepClueViewModelState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = uiState.tobBarTitle,
                navAction = CTNavAction.Back(navigateBack),
            )
        },
        backgroundColor = CTTheme.color.background,
        bottomBar = {
            uiState.bottomActionBar.Content(modifier = Modifier.imePadding())
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = CTTheme.spacing.large),
        ) {
            CTTextView(
                text = TextSpec.Resources(R.string.pickStepClue_message),
                style = CTTheme.typography.body,
                color = CTTheme.color.textOnBackground,
                modifier = Modifier
                    .padding(horizontal = CTTheme.spacing.large)
            )
            SpacerLarge()
            uiState.switchRowState.Content()
            AnimatedVisibility(visible = uiState.switchRowState.checked) {
                CTOutlinedTextField(
                    value = uiState.clueValue,
                    onValueChange = uiState.onTextChanged,
                    modifier = Modifier
                        .padding(horizontal = CTTheme.spacing.large),
                )
            }
        }
    }
}
