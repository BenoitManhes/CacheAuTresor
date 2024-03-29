package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.SectionHeader
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable.SectionHeader2
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.dividerItem
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.spacer.spacerMediumItem
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBarAction
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed

@Composable
fun EditDraftCacheRoute(
    navigateBack: () -> Unit,
    navigateToPickName: (String) -> Unit,
    navigateToPickType: (String) -> Unit,
    navigateToPickInitCoordinates: (String) -> Unit,
    navigateToEditDraftStep: (String, String) -> Unit,
    navigateToPickDifficulty: (String) -> Unit,
    navigateToPickGround: (String) -> Unit,
    navigateToPickSize: (String) -> Unit,
    navigateToPickDescription: (String) -> Unit,
    navigateToPickUnlockInstructions: (String) -> Unit,
    navigateToPickUnlockCode: (String) -> Unit,
    navigateToCreationSuccess: (String) -> Unit,
    viewModel: EditCacheViewModel = hiltViewModel(),
) {
    val uiState by viewModel.editCacheState.collectAsStateWithLifecycle()
    val navigation by viewModel.navigation.collectAsStateWithLifecycle()

    LaunchedEffect(navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            EditCacheNavigation.Back -> navigateBack()
            is EditCacheNavigation.PickName -> navigateToPickName(navValue.draftCacheId)
            is EditCacheNavigation.PickType -> navigateToPickType(navValue.draftCacheId)
            is EditCacheNavigation.PickInitCoordinates -> navigateToPickInitCoordinates(navValue.draftCacheId)
            is EditCacheNavigation.EditDraftStep -> navigateToEditDraftStep(navValue.draftCacheId, navValue.draftStepId)
            is EditCacheNavigation.PickDifficulty -> navigateToPickDifficulty(navValue.draftCacheId)
            is EditCacheNavigation.PickGround -> navigateToPickGround(navValue.draftCacheId)
            is EditCacheNavigation.PickSize -> navigateToPickSize(navValue.draftCacheId)
            is EditCacheNavigation.PickDescription -> navigateToPickDescription(navValue.draftCacheId)
            is EditCacheNavigation.PickUnlockInstructions -> navigateToPickUnlockInstructions(navValue.draftCacheId)
            is EditCacheNavigation.PickUnlockCode -> navigateToPickUnlockCode(navValue.draftCacheId)
            is EditCacheNavigation.CreationSuccess -> navigateToCreationSuccess(navValue.cacheId)
        }
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper(darkStatusBarIcons = false) {
            EditDraftCacheScreen(
                uiState = uiState,
                navigateBack = navigateBack,
                delete = viewModel::showDeleteModal,
            )
        }
    }
}

@Composable
private fun EditDraftCacheScreen(
    uiState: EditCacheViewModelState,
    navigateBack: () -> Unit,
    delete: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = TextSpec.Resources(R.string.startCreationModal_title),
                navAction = CTNavAction.Back(navigateBack),
                trailingAction = CTTopBarAction.Delete(delete),
            )
        },
        backgroundColor = CTTheme.color.background,
        bottomBar = {
            uiState.bottomActionBar?.Content()
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            if (uiState.showGeneralSection) {
                SectionHeader2.lazyItem(
                    scope = this,
                    text = TextSpec.Resources(R.string.cacheEditor_generalSection_header),
                    icon = CTTheme.composed { icon.Globe },
                )
            }
            uiState.cacheName?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_cacheName_header))
                uiState.cacheName.lazyItem(this, key = "cache-name")
            }

            uiState.cacheType?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_type_header))
                uiState.cacheType.lazyItem(this, key = "cache-type")
            }

            uiState.initCoordinates?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_initialCoordinates_header))
                uiState.initCoordinates.lazyItem(this)
            }

            if (uiState.showStepsSection) {
                sectionDividerItem()
                SectionHeader2.lazyItem(
                    scope = this,
                    text = TextSpec.Resources(R.string.cacheEditor_stepSection_header),
                    icon = CTTheme.composed { icon.Book },
                )
            }
            uiState.stepSection?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_steps_header))
                uiState.stepSection.lazyItem(this)
            }

            if (uiState.showCharacteristicsSection) {
                sectionDividerItem()
                SectionHeader2.lazyItem(
                    scope = this,
                    text = TextSpec.Resources(R.string.cacheEditor_characteristicsSection_header),
                    icon = CTTheme.composed { icon.Mountain },
                )
            }
            uiState.propertiesSection?.let {
                uiState.propertiesSection.lazyItem(this)
            }

            uiState.descriptionSection?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_description_header))
                uiState.descriptionSection.lazyItem(this, key = "cache-description")
            }

            if (uiState.showLockSection) {
                sectionDividerItem()
                SectionHeader2.lazyItem(
                    scope = this,
                    text = TextSpec.Resources(R.string.cacheEditor_LockSection_header),
                    icon = CTTheme.composed { icon.Key },
                )
            }

            uiState.unlockInstructions?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_unlockingInstructions_header))
                uiState.unlockInstructions.lazyItem(this, key = "cache-unlock-instructions")
            }

            uiState.unlockCode?.let {
                sectionHeaderItem(TextSpec.Resources(R.string.cacheEditor_unlockingCode_header))
                uiState.unlockCode.lazyItem(this, key = "cache-unlock-code")
            }

            SpacerLarge.item(this)
        }
    }
}

private fun LazyListScope.sectionDividerItem() {
    spacerMediumItem()
    dividerItem()
}

private fun LazyListScope.sectionHeaderItem(title: TextSpec) {
    SpacerLarge.item(this)
    SectionHeader.item(this, title = title)
}
