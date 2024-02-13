package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun EditDraftCacheRoute(
    navigateBack: () -> Unit,
    navigateToPickName: (String) -> Unit,
    navigateToPickType: (String) -> Unit,
    navigateToPickInitCoordinates: (String) -> Unit,
    viewModel: EditCacheViewModel = hiltViewModel(),
) {
    val uiState by viewModel.editCacheState.collectAsState()
    val navigation by viewModel.navigation.collectAsState()

    LaunchedEffect(navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            is EditCacheNavigation.PickName -> navigateToPickName(navValue.draftCacheId)
            is EditCacheNavigation.PickType -> navigateToPickType(navValue.draftCacheId)
            is EditCacheNavigation.PickInitCoordinates -> navigateToPickInitCoordinates(navValue.draftCacheId)
        }
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper(darkStatusBarIcons = false) {
            EditDraftCacheScreen(
                uiState = uiState,
                navigateBack = navigateBack,
            )
        }
    }
}

@Composable
private fun EditDraftCacheScreen(
    uiState: EditCacheViewModelState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = TextSpec.Resources(R.string.startCreationModal_title),
                navAction = CTNavAction.Back(navigateBack),
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
                uiState.initCoordinates.lazyItem(this, key = "init-coordinates")
            }
        }
    }
}

private fun LazyListScope.sectionHeaderItem(title: TextSpec) {
    SpacerLarge.item(this)
    SectionHeader.item(this, title = title)
}
