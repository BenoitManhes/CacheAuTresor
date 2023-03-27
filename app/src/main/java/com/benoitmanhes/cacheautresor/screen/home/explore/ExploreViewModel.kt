package com.benoitmanhes.cacheautresor.screen.home.explore

import android.location.Location
import android.location.LocationManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModel
import com.benoitmanhes.cacheautresor.utils.extension.toModel
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UICache
import com.benoitmanhes.domain.usecase.cache.GetAllUICachesUseCase
import com.benoitmanhes.domain.usecase.cache.UpdateCachesDistancesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    getAllUICacheUseCase: GetAllUICachesUseCase,
    private val updateCachesDistancesUseCase: UpdateCachesDistancesUseCase,
    locationManager: LocationManager,
) : LocationAccessViewModel(locationManager) {

    var uiState: ExploreUIState by mutableStateOf(ExploreUIState())
        private set

    init {
        viewModelScope.launch {
            getAllUICacheUseCase().collect { result ->
                when (result) {
                    is CTResult.Success -> {
                        val cachesWithDistance = uiState.currentPosition?.let { _currentPosition ->
                            updateCachesDistancesUseCase(
                                currentLocation = _currentPosition,
                                uiCaches = result.successData,
                            )
                        } ?: result.successData

                        uiState = uiState.copy(caches = cachesWithDistance)
                    }
                    is CTResult.Loading -> {}
                    is CTResult.Failure -> {}
                }
            }
        }
    }

    fun setMapPosition(position: Coordinates) {
        uiState = uiState.copy(mapPosition = position)
    }

    fun selectCache(uiCache: UICache) {
        uiState = uiState.copy(cacheSelected = uiCache)
    }

    fun unselectCache() {
        uiState = uiState.copy(cacheSelected = null)
    }

    override fun onAccessLocationRefused() {
        uiState = uiState.copy(isAccessPositionGranted = false)
    }

    override fun onLocationChanged(p0: Location) {
        uiState = uiState.copy(
            isAccessPositionGranted = true,
            currentPosition = p0.toModel(),
            caches = updateCachesDistancesUseCase(
                currentLocation = p0.toModel(),
                uiCaches = uiState.caches,
            )
        )
    }
}
