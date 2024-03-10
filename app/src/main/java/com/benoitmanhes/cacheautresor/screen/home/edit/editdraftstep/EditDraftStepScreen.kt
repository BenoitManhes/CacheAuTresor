package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.SectionHeader
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBarAction
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun EditDraftStepRoute(
    navigateBack: () -> Unit,
    navigateToPickStepCoordinates: (String, String) -> Unit,
    navigateToEditInstructions: (String, String) -> Unit,
    navigateToPickClue: (String, String) -> Unit,
    navigateToPickValidationCode: (String, String) -> Unit,
    viewModel: EditDraftStepViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigate.collectAsState()

    LaunchedEffect(navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            is EditDraftStepNavigation.PickStepCoordinates -> {
                navigateToPickStepCoordinates(navValue.draftCacheId, navValue.draftStepId)
            }

            is EditDraftStepNavigation.EditInstructions -> {
                navigateToEditInstructions(navValue.draftCacheId, navValue.draftStepId)
            }

            is EditDraftStepNavigation.EditClue -> {
                navigateToPickClue(navValue.draftCacheId, navValue.draftStepId)
            }

            is EditDraftStepNavigation.EditValidationCode -> {
                navigateToPickValidationCode(navValue.draftCacheId, navValue.draftStepId)
            }

            is EditDraftStepNavigation.Back -> navigateBack()
        }
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper(darkStatusBarIcons = false) {
            EditDraftStepScreen(
                uiState = uiState,
                navigateBack = navigateBack,
            )
        }
    }
}

@Composable
private fun EditDraftStepScreen(
    uiState: EditDraftStepViewModelState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = uiState.topBarTitle,
                navAction = CTNavAction.Back(navigateBack),
                trailingAction = uiState.onClickDelete?.let(CTTopBarAction::Delete),
            )
        },
        backgroundColor = CTTheme.color.background,
        bottomBar = {
            uiState.bottomBar?.Content()
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            uiState.stepCoordinates?.let { mapRowPickerState ->
                sectionHeaderItem(TextSpec.Resources(R.string.stepEditor_coordinates_header))
                mapRowPickerState.lazyItem(this)
            }

            uiState.instructions?.sectionItems(this)

            uiState.clue?.let { textPicker ->
                sectionHeaderItem(TextSpec.Resources(R.string.stepEditor_clue_header))
                textPicker.lazyItem(this, "clue.text.picker")
            }

            uiState.validationCode?.let { textPicker ->
                sectionHeaderItem(TextSpec.Resources(R.string.stepEditor_validationCode_header))
                textPicker.lazyItem(this, "validation.code.text.picker")
            }
        }
    }
}

private fun LazyListScope.sectionHeaderItem(title: TextSpec) {
    SpacerLarge.item(this)
    SectionHeader.item(this, title = title)
}
