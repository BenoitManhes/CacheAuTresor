package com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.usecase.cache.GetForbiddenFinalPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableFinalPlacesViewModel @Inject constructor(
    getForbiddenFinalPlacesUseCase: GetForbiddenFinalPlacesUseCase,
    snackbarManager: SnackbarManager,
) : ViewModel() {

    private val _forbiddenPlaces = MutableStateFlow<List<Coordinates>>(emptyList())
    val forbiddenPlaces: StateFlow<List<Coordinates>> get() = _forbiddenPlaces.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getForbiddenFinalPlacesUseCase()
            when (result) {
                is CTSuspendResult.Success -> _forbiddenPlaces.value = result.successData
                is CTSuspendResult.Failure -> snackbarManager.showError(result.error)
            }
        }
    }
}