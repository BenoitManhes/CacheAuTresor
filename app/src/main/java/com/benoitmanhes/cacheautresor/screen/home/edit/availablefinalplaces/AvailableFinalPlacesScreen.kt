package com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBar
import com.benoitmanhes.cacheautresor.common.extensions.refresh
import com.benoitmanhes.cacheautresor.common.extensions.setUpDefaultParameters
import com.benoitmanhes.cacheautresor.common.extensions.setUpMyLocation
import com.benoitmanhes.cacheautresor.common.extensions.toGeoPoint
import com.benoitmanhes.cacheautresor.common.maps.CTMapView
import com.benoitmanhes.cacheautresor.common.maps.rememberMapViewWithLifecycle
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Polygon

@Composable
fun AvailableFinalPlacesRoute(
    navigateBack: () -> Unit,
    navigateDraftCacheDetail: (String) -> Unit,
    viewModel: AvailableFinalPlacesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navigation by viewModel.navigation.collectAsState()

    LaunchedEffect(navigation) {
        val navValue = navigation ?: return@LaunchedEffect
        when (navValue) {
            is AvailableFinalPlacesNavigation.DraftCacheDetail -> navigateDraftCacheDetail(navValue.draftCacheId)
        }
        viewModel.consumeNavigation()
    }

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper {
            AvailableFinalPlacesScreen(
                uiState = uiState,
                navigateBack = navigateBack,
            )
        }
    }
}

@Composable
private fun AvailableFinalPlacesScreen(
    uiState: AvailableFinalPlacesViewModelState,
    navigateBack: () -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val zonesFolder = remember { FolderOverlay() }
    val borderZoneColor = CTTheme.color.critical.toArgb()
    val fillZoneColor = CTTheme.color.surfaceCriticalSoft.toArgb()

    LaunchedEffect(uiState.forbiddenPlaces) {
        mapViewState.overlays.removeAll(zonesFolder.items)
        zonesFolder.items.clear()

        val polygones = uiState.forbiddenPlaces.map { (center, distance) ->
            val circlePoints = Polygon.pointsAsCircle(center.toGeoPoint(), distance.meters)
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

    LaunchedEffect(mapViewState) {
        mapViewState.setUpDefaultParameters()
        mapViewState.setUpMyLocation()
    }

    Scaffold(
        topBar = {
            CTTopBar(
                navAction = CTNavAction.Close(navigateBack),
            )
        },
        bottomBar = {
            BottomActionBar(
                title = TextSpec.Resources(R.string.availableFinalPlaces_bottomBar_title),
                message = TextSpec.Resources(R.string.availableFinalPlaces_bottomBar_message),
                primaryButton = uiState.bottomBarButton,
            )
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
        }
    }
}
