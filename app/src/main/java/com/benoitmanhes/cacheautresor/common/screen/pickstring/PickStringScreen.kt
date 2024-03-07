package com.benoitmanhes.cacheautresor.common.screen.pickstring

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun PickStringScreen(
    topBarTitle: TextSpec,
    description: TextSpec,
    uiState: PickStringUiState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = topBarTitle,
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
                text = description,
                style = CTTheme.typography.body,
                color = CTTheme.color.textOnBackground,
            )
            SpacerLarge()
            CTOutlinedTextField(
                value = uiState.textValue,
                onValueChange = uiState.onTextChanged,
            )
        }
    }
}
