package com.benoitmanhes.cacheautresor.screen.loading

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    loadingManager: LoadingManager,
) : ViewModel() {

    val isLoading: StateFlow<Boolean> = loadingManager.isLoading
}
