package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTonalElevationEnabled
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.extensions.onTapListener
import com.benoitmanhes.cacheautresor.common.extensions.refresh
import com.benoitmanhes.cacheautresor.common.extensions.setUpDefaultParameters
import com.benoitmanhes.cacheautresor.common.extensions.setUpMyLocation
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.maps.CTMapView
import com.benoitmanhes.cacheautresor.common.maps.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessView
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.CacheDetailInstructionsScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.CacheDetailRecapScreen
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeader
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMedium
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.fabbutton.CTFabButton
import com.benoitmanhes.designsystem.molecule.loading.CTLoadingView
import com.benoitmanhes.designsystem.molecule.tabrow.CTScrollableTabRow
import com.benoitmanhes.designsystem.molecule.tabrow.CTTab
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.AnimatedNullableVisibility
import com.benoitmanhes.domain.uimodel.UIStep
import kotlinx.coroutines.launch
import org.osmdroid.views.overlay.FolderOverlay

@Composable
fun CacheDetailsRoute(
    onNavigateBack: () -> Unit,
    navigateToEditNote: (cacheId: String) -> Unit,
    viewModel: CacheDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigation by viewModel.navigation.collectAsState()

    LaunchedEffect(key1 = navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            is CacheDetailNavigation.EditNote -> navigateToEditNote(navValue.cacheId)
        }
        viewModel.consumeNavigation()
    }

    CTTheme(colorTheme = (uiState as? CacheDetailsViewModelState.Data)?.cacheColorTheme ?: CTColorTheme.Default) {
        LocationAccessView(viewModel = viewModel)
        CTScreenWrapper {
            CacheDetailsScreen(
                onNavigateBack = onNavigateBack,
                uiState = uiState,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CacheDetailsScreen(
    onNavigateBack: () -> Unit,
    uiState: CacheDetailsViewModelState,
) {
    val coroutineScope = rememberCoroutineScope()
    val mapViewState = rememberMapViewWithLifecycle()
    val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)
    val markerFolder = remember { FolderOverlay() }

    val data = uiState as? CacheDetailsViewModelState.Data

    // Map set-up
    LaunchedEffect(mapViewState) {
        mapViewState.apply {
            setUpDefaultParameters()
            setUpMyLocation()
            onTapListener(
                onSingleTap = {
                    coroutineScope.launch {
                        bottomSheetState.partialExpand()
                    }
                    true
                }
            )
        }
    }

    LaunchedEffect(data?.uiMarkers) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()
        data?.uiMarkers?.forEach { uiMarker ->
            markerFolder.add(uiMarker.getOSMMarker(mapViewState))
        }
        data?.uiMarkers?.firstOrNull()?.let { uiMarker ->
            mapViewState.controller.setCenter(uiMarker.coordinates.toGeoPoint())
        }

        mapViewState.overlays.addAll(markerFolder.items)
        mapViewState.refresh()
    }

    CTMapView(
        modifier = Modifier
            .fillMaxWidth(),
        mapViewState = mapViewState,
    )

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column {
            CTTopBar(
                navAction = CTNavAction.Back(onNavigateBack),
            )
            SpacerMedium()

            BottomSheetScaffold(
                scaffoldState = bottomSheetScaffoldState,
                sheetPeekHeight = AppDimens.CacheDetail.bottomSheetHeaderHeight,
                sheetContent = {
                    Column(
                        modifier = Modifier.background(CTTheme.color.background),
                    ) {
                        // Header
                        data?.headerState?.let {
                            CacheDetailHeader(uiState.headerState) {
                                coroutineScope.launch {
                                    bottomSheetState.expand()
                                }
                            }
                        }

                        // Body
                        when (uiState) {
                            is CacheDetailsViewModelState.Data -> {
                                DataContent(
                                    uiState = uiState,
                                    modifier = Modifier.weight(1f),
                                )
                            }

                            CacheDetailsViewModelState.Initialize -> {
                                InitContent()
                            }

                            is CacheDetailsViewModelState.Empty -> {
                                EmptyContent(uiState = uiState)
                            }
                        }

                        data?.bottomBarState?.Content()
                    }
                },
                sheetDragHandle = null,
                content = {},
            )
        }

        AnimatedNullableVisibility(
            value = data?.fabButtonState,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(CTTheme.spacing.extraLarge),
            visible = { bottomSheetState.targetValue != SheetValue.PartiallyExpanded },
            enter = fadeIn(),
            exit = fadeOut(),
        ) { buttonState ->
            CTFabButton(
                state = buttonState,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun DataContent(
    uiState: CacheDetailsViewModelState.Data,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = { uiState.pageCount },
        initialPage = uiState.initialPageIndex,
    )

    Column(
        modifier
            .fillMaxWidth()
            .animateContentSize(),
    ) {
        if (uiState.stepInstructions.isNotEmpty()) {
            CompositionLocalProvider(
                LocalTonalElevationEnabled provides false,
            ) {
                CTScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                ) {
                    CTTab(
                        text = TextSpec.Resources(R.string.cacheDetail_tabSelector_recap),
                        selected = pagerState.currentPage == 0,
                        onClick = {
                            coroutineScope.launch { pagerState.scrollToPage(0) }
                        }
                    )

                    uiState.stepInstructions.forEachIndexed { index, instructionSectionState ->
                        val icon = when (instructionSectionState.status) {
                            UIStep.Status.Done -> CTTheme.icon.Done
                            else -> null
                        }
                        CTTab(
                            text = instructionSectionState.title,
                            selected = pagerState.currentPage == index + 1,
                            enabled = instructionSectionState.enabled,
                            trailingIcon = icon,
                            onClick = {
                                coroutineScope.launch { pagerState.scrollToPage(index + 1) }
                            }
                        )
                    }
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            beyondBoundsPageCount = 1,
        ) { page ->
            when (page) {
                0 -> {
                    CacheDetailRecapScreen(
                        uiState = uiState,
                    )
                }

                else -> {
                    uiState.stepInstructions.getOrNull(page - 1)?.let { instuctions ->
                        CacheDetailInstructionsScreen(
                            instructionsSectionState = instuctions,
                            noteSectionState = uiState.noteSectionState,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InitContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CTLoadingView()
    }
}

@Composable
private fun EmptyContent(uiState: CacheDetailsViewModelState.Empty) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(CTTheme.spacing.large),
        contentAlignment = Alignment.Center,
    ) {
        CTTextView(
            text = uiState.message,
            style = CTTheme.typography.body,
            color = CTTheme.color.textLight,
        )
    }
}
