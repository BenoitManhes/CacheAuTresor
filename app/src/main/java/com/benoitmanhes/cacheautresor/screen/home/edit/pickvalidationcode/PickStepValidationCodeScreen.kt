package com.benoitmanhes.cacheautresor.screen.home.edit.pickvalidationcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun PickStepValidationCodeRoute(
    navigateBack: () -> Unit,
    viewModel: PickStepValidationCodeViewModel = hiltViewModel(),
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
            PickStepValidationCodeScreen(
                uiState = uiState,
                navigateBack = navigateBack,
            )
        }
    }
}

@Composable
private fun PickStepValidationCodeScreen(
    uiState: PickStepValidationCodeViewModelState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = uiState.topBarTitle,
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
                .padding(CTTheme.spacing.large),
        ) {
            CTTextView(
                text = TextSpec.Resources(R.string.pickValidationCode_message),
                style = CTTheme.typography.body,
                color = CTTheme.color.textOnBackground,
            )
            SpacerLarge()
            CTOutlinedTextField(
                value = uiState.validationCodeValue,
                onValueChange = uiState.onTextChanged,
            )
        }
    }
}
