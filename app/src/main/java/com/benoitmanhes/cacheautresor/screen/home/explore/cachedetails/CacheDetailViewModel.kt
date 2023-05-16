package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.usecase.cache.GetSelectedUICacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CacheDetailViewModel @Inject constructor(
    getSelectedUICacheUseCase: GetSelectedUICacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(ExploreDestination.CacheDetails.cacheDetailsArgument).orEmpty()

    val uiState: StateFlow<CacheDetailsUIState> = getSelectedUICacheUseCase(cacheId)
        .map { result -> result.mapToUIState() }
        .stateIn(
            viewModelScope,
            AppConstants.Flow.DefaultSharingStarted,
            CacheDetailsUIState.Initialize,
        )

    private fun CTResult<UICacheDetails>.mapToUIState(): CacheDetailsUIState = when (this) {
        is CTResult.Success -> CacheDetailsUIState.Data(
            uiCacheDetails = successData,
            distance = null,
        )

        is CTResult.Loading -> CacheDetailsUIState.Initialize
        is CTResult.Failure -> CacheDetailsUIState.Error(error)
    }
}
