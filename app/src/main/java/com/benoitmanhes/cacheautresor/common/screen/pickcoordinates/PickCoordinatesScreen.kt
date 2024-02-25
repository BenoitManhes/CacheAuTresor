package com.benoitmanhes.cacheautresor.common.screen.pickcoordinates

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.benoitmanhes.cacheautresor.common.extensions.onTapListener
import com.benoitmanhes.cacheautresor.common.extensions.refresh
import com.benoitmanhes.cacheautresor.common.extensions.setUpDefaultParameters
import com.benoitmanhes.cacheautresor.common.extensions.setUpMyLocation
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.maps.CTMapView
import com.benoitmanhes.cacheautresor.common.maps.rememberMapViewWithLifecycle
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates
import kotlinx.coroutines.launch
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Polygon

@Composable
internal fun PickCoordinatesScreen(
    uiState: PickCoordinatesUiState,
    navigateBack: () -> Unit,
    onClickEdit: () -> Unit,
    onLongClickMap: (Coordinates?) -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val zonesFolder = remember { FolderOverlay() }
    val currentMarkerFolder = remember { FolderOverlay() }
    val otherMarkerFolder = remember { FolderOverlay() }
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

        val polygones = uiState.forbiddenPlaces.map { (center, radius) ->
            val circlePoints = Polygon.pointsAsCircle(
                center.toGeoPoint(),
                radius.meters,
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

    LaunchedEffect(uiState.otherMarkers) {
        mapViewState.overlays.removeAll(otherMarkerFolder.items)
        otherMarkerFolder.items.clear()
        uiState.otherMarkers.forEach { uiMarker ->
            otherMarkerFolder.add(uiMarker.getOSMMarker(mapViewState))
        }
        mapViewState.overlays.addAll(otherMarkerFolder.items)
        mapViewState.refresh()
    }

    LaunchedEffect(uiState.uiMarker) {
        mapViewState.overlays.removeAll(currentMarkerFolder.items)
        currentMarkerFolder.items.clear()
        uiState.uiMarker?.let { uiMarker ->
            currentMarkerFolder.add(uiMarker.getOSMMarker(mapViewState))
            mapViewState.overlays.addAll(currentMarkerFolder.items)
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
