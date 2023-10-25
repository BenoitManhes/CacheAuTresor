package com.benoitmanhes.cacheautresor.navigation.authenticated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.domain.synchronization.SynchronizeCacheUseCase
import com.benoitmanhes.domain.synchronization.SynchronizeUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticatedRootViewModel @Inject constructor(
    private val synchronizeCacheUseCase: SynchronizeCacheUseCase,
    private val synchronizeUserUseCase: SynchronizeUserUseCase,
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeCacheUseCase()
        }
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeUserUseCase()
        }
    }
}
