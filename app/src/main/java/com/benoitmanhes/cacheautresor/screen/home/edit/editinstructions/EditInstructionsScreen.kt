package com.benoitmanhes.cacheautresor.screen.home.edit.editinstructions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.selector.CTTabSelector
import com.benoitmanhes.designsystem.molecule.selector.SelectorItem
import com.benoitmanhes.designsystem.molecule.selector.TabSelectorState
import com.benoitmanhes.designsystem.molecule.textfield.ZoneTextField
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import kotlinx.coroutines.launch

@Composable
fun EditInstructionsRoute(
    navigateBack: () -> Unit,
    viewModel: EditInstructionsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigate by viewModel.navigateBack.collectAsState()

    LaunchedEffect(navigate) {
        navigate ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper(darkStatusBarIcons = false) {
            EditInstructionsScreen(
                uiState = uiState,
                navigateBack = navigateBack,
                onInstructionsTextChange = viewModel::updateInstructions,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun EditInstructionsScreen(
    uiState: EditInstructionsViewModelState,
    onInstructionsTextChange: (String) -> Unit,
    navigateBack: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        pageCount = { 2 },
        initialPage = 0,
    )
    val tabSelectorState = remember(pagerState.currentPage) {
        TabSelectorState(
            items = instructionsTabItems,
            selectedItem = instructionsTabItems[pagerState.currentPage],
            onSelectedItem = { item ->
                scope.launch {
                    pagerState.scrollToPage(instructionsTabItems.indexOf(item))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = TextSpec.Resources(R.string.editInstructions_topBar_title),
                navAction = CTNavAction.Back(navigateBack),
            )
        },
        backgroundColor = CTTheme.color.background,
        bottomBar = {
            uiState.bottomBar.Content(modifier = Modifier.imePadding())
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
        ) {
            Header(uiState.headerText, tabSelectorState)
            Divider()
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                when (page) {
                    // Edition
                    0 -> {
                        ZoneTextField(
                            value = uiState.rawText,
                            onValueUpdated = { onInstructionsTextChange(it.orEmpty()) },
                            modifier = Modifier
                                .fillMaxSize()
                                .heightIn(min = AppDimens.EditCache.editInstructionsZoneTextfieldMinHeight)
                                .padding(CTTheme.spacing.large),
                            placeholder = TextSpec.Resources(R.string.editInstructions_textfield_placeholder),
                        )
                    }

                    // Preview
                    1 -> {
                        CTMarkdownText(
                            markdown = uiState.formattedText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(CTTheme.spacing.large),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(
    headerText: TextSpec?,
    tabSelectorState: TabSelectorState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CTTheme.spacing.large),
    ) {
        CTTextView(
            text = headerText,
            style = CTTheme.typography.bodyBold,
        )
        SpacerSmall()

        CTMarkdownText(
            markdown = TextSpec.Resources(
                R.string.editInstructions_markdownMessage,
                TextSpec.Resources(R.string.url_markdownGuide),
            ),
            style = CTTheme.typography.bodySmall,
        )
        SpacerLarge()

        CTTabSelector(tabSelectorState = tabSelectorState)
    }
}

private val instructionsTabItems: List<SelectorItem> = listOf(
    SelectorItem(text = TextSpec.Resources(R.string.editInstructions_tabSelector_edit)),
    SelectorItem(text = TextSpec.Resources(R.string.editInstructions_tabSelector_preview)),
)
