package com.benoitmanhes.cacheautresor.screen.home.edit.pickinitcoordinates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.common.extensions.onTapListener
import com.benoitmanhes.cacheautresor.common.extensions.refresh
import com.benoitmanhes.cacheautresor.common.extensions.setUpDefaultParameters
import com.benoitmanhes.cacheautresor.common.extensions.setUpMyLocation
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.maps.CTMapView
import com.benoitmanhes.cacheautresor.common.maps.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.utils.DomainConstants
import kotlinx.coroutines.launch
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Polygon

@Composable
fun PickInitCoordinatesRoute(
    navigateBack: () -> Unit,
    viewModel: PickInitCoordinatesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigateBack.collectAsState()

    LaunchedEffect(navigation) {
        navigation ?: return@LaunchedEffect
        navigateBack()
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper {
            PickInitCoordinatesScreen(
                uiState = uiState,
                navigateBack = navigateBack,
                onClickEdit = viewModel::editCoordinatesManually,
                onLongClickMap = viewModel::updateCoordinates,
            )
        }
    }
}

@Composable
private fun PickInitCoordinatesScreen(
    uiState: PickInitCoordinatesViewModelState,
    navigateBack: () -> Unit,
    onClickEdit: () -> Unit,
    onLongClickMap: (Coordinates?) -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val zonesFolder = remember { FolderOverlay() }
    val markerFolder = remember { FolderOverlay() }
    val borderZoneColor = CTTheme.color.critical.toArgb()
    val fillZoneColor = CTTheme.color.surfaceCriticalSoft.toArgb()
    val scope = rememberCoroutineScope()

    LaunchedEffect(mapViewState) {
        mapViewState.setUpDefaultParameters()
        mapViewState.setUpMyLocation()
        mapViewState.onTapListener(
            onLongPress = {
                onLongClickMap(it)
                true
            },
        )
    }

    LaunchedEffect(uiState.forbiddenPlaces) {
        mapViewState.overlays.removeAll(zonesFolder.items)
        zonesFolder.items.clear()

        val polygones = uiState.forbiddenPlaces.map { center ->
            val circlePoints = Polygon.pointsAsCircle(
                center.toGeoPoint(),
                DomainConstants.Cache.initialCoordinatesDistanceMin.meters
            )
            Polygon().apply {
                points = circlePoints
                fillPaint.color = fillZoneColor
                outlinePaint.color = borderZoneColor
                outlinePaint.strokeWidth = 4f
            }
        }
        zonesFolder.items.addAll(polygones)
        mapViewState.overlays.addAll(zonesFolder.items)
        mapViewState.refresh()
    }

    LaunchedEffect(uiState.uiMarker) {
        mapViewState.overlays.removeAll(markerFolder.items)
        markerFolder.items.clear()
        uiState.uiMarker?.let { uiMarker ->
            markerFolder.add(uiMarker.getOSMMarker(mapViewState))
            mapViewState.overlays.addAll(markerFolder.items)
            scope.launch {
                mapViewState.controller.animateTo(uiMarker.coordinates.toGeoPoint())
            }
        }
        mapViewState.refresh()
    }

    Scaffold(
        topBar = {
            CTTopBar(
                navAction = CTNavAction.Close(navigateBack),
            )
        },
        bottomBar = {
            uiState.bottomActionBar.Content()
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
        ) {
            CTMapView(
                modifier = Modifier.fillMaxSize(),
                mapViewState = mapViewState,
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .wrapContentHeight()
                    .padding(CTTheme.spacing.large),
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
            ) {
                uiState.currentCoordinates.Content(
                    modifier = Modifier
                        .weight(1f)
                        .height(Dimens.Size.fabIconButtonSize),
                )
                FabIconButton(
                    icon = CTTheme.icon.Edit,
                    color = CTTheme.color.textOnSurface,
                    type = FabButtonType.OUTLINED,
                    onClick = onClickEdit,
                )
            }
        }
    }
}
