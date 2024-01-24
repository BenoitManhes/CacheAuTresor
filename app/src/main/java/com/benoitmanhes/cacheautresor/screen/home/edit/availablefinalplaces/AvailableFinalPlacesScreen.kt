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
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Polygon

@Composable
fun AvailableFinalPlacesRoute(
    navigateBack: () -> Unit,
    viewModel: AvailableFinalPlacesViewModel = hiltViewModel(),
) {

    val forbiddenPlaces by viewModel.forbiddenPlaces.collectAsState()

    CTTheme(CTColorTheme.Cartography) {
        CTScreenWrapper {
            AvailableFinalPlacesScreen(
                forbiddenPlaces = forbiddenPlaces,
                navigateBack = navigateBack,
                startCreation = {},
                requestLocationPermission = {},
            )
        }
    }
}

@Composable
private fun AvailableFinalPlacesScreen(
    forbiddenPlaces: List<Coordinates>,
    navigateBack: () -> Unit,
    startCreation: () -> Unit,
    requestLocationPermission: () -> Unit,
) {
    val mapViewState = rememberMapViewWithLifecycle()
    val zonesFolder = remember { FolderOverlay() }
    val borderZoneColor = CTTheme.color.criticalHard.toArgb()
    val fillZoneColor = CTTheme.color.criticalHardSurface.toArgb()

    LaunchedEffect(forbiddenPlaces) {
        mapViewState.overlays.removeAll(zonesFolder.items)
        zonesFolder.items.clear()

        val polygones = forbiddenPlaces.map { center ->
            val circlePoints = Polygon.pointsAsCircle(center.toGeoPoint(), 50.0)
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
                firstButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.availableFinalPlaces_firstButton_text),
                    onClick = startCreation,
                ),
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
