package com.benoitmanhes.cacheautresor.screen.home.edit.creationsuccess

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.domain.usecase.cache.GetCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreationSuccessViewModel @Inject constructor(
    getCacheUseCase: GetCacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(EditCacheDestination.CreationSuccess.arg).orEmpty()

    val cacheName: StateFlow<TextSpec?> = getCacheUseCase(cacheId).map { result ->
        result.data?.title?.let(TextSpec::RawString)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(AppConstants.ViewModel.defaultStopTimeOut),
        initialValue = null,
    )
}
