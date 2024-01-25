package com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.usecase.cache.GetForbiddenFinalPlacesUseCase
import com.benoitmanhes.domain.usecase.draftcache.CreateDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableFinalPlacesViewModel @Inject constructor(
    private val createDraftCacheUseCase: CreateDraftCacheUseCase,
    private val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
    getForbiddenFinalPlacesUseCase: GetForbiddenFinalPlacesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AvailableFinalPlacesViewModelState(
            forbiddenPlaces = emptyList(),
            bottomBarButton = PrimaryButtonState(
                text = TextSpec.Resources(R.string.availableFinalPlaces_firstButton_text),
                onClick = ::createDraftCache,
            )
        )
    )
    val uiState: StateFlow<AvailableFinalPlacesViewModelState> get() = _uiState.asStateFlow()

    private val _navigation = MutableStateFlow<AvailableFinalPlacesNavigation?>(null)
    val navigation: StateFlow<AvailableFinalPlacesNavigation?> get() = _navigation.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getForbiddenFinalPlacesUseCase()
            when (result) {
                is CTSuspendResult.Success -> _uiState.value = _uiState.value.copy(forbiddenPlaces = result.successData)
                is CTSuspendResult.Failure -> snackbarManager.showError(result.error)
            }
        }
    }

    fun consumeNavigation() {
        _navigation.value = null
    }

    private fun createDraftCache() {
        viewModelScope.launch {
            loadingManager.showLoading()
            val result = createDraftCacheUseCase()
            loadingManager.hideLoading()
            when (result) {
                is CTSuspendResult.Success -> {
                    _navigation.value = AvailableFinalPlacesNavigation.DraftCacheDetail(result.successData.draftCacheId)
                }

                is CTSuspendResult.Failure -> {
                    snackbarManager.showError(result.error)
                }
            }
        }
    }
}

sealed interface AvailableFinalPlacesNavigation {
    @JvmInline
    value class DraftCacheDetail(val draftCacheId: String) : AvailableFinalPlacesNavigation
}
