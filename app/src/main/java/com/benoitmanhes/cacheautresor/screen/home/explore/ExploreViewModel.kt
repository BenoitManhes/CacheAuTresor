package com.benoitmanhes.cacheautresor.screen.home.explore

import android.location.Location
import android.location.LocationManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModel
import com.benoitmanhes.cacheautresor.utils.extension.toModel
import com.benoitmanhes.domain.model.Coordinates
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    locationManager: LocationManager,
) : LocationAccessViewModel(locationManager) {

    var uiState: ExploreUIState by mutableStateOf(ExploreUIState())
        private set

    fun setMapPosition(position: Coordinates) {
        uiState = uiState.copy(mapPosition = position)
    }

    override fun onAccessLocationRefused() {
        uiState = uiState.copy(isAccessPositionGranted = false)
    }

    override fun onLocationChanged(p0: Location) {
        uiState = uiState.copy(
            isAccessPositionGranted = true,
            currentPosition = p0.toModel(),
        )
    }
}