package com.benoitmanhes.cacheautresor.navigation.root

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.navigation.root.holder.AuthenticatedState
import com.benoitmanhes.domain.synchronization.SynchronizeAllUsersUseCase
import com.benoitmanhes.domain.synchronization.SynchronizeCacheUseCase
import com.benoitmanhes.domain.synchronization.SynchronizeUserUseCase
import com.benoitmanhes.domain.usecase.authentication.IsAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppContentViewModel @Inject constructor(
    isAuthenticatedUseCase: IsAuthenticatedUseCase,
    private val synchronizeCacheUseCase: SynchronizeCacheUseCase,
    private val synchronizeUserUseCase: SynchronizeUserUseCase,
    private val synchronizeAllUsersUseCase: SynchronizeAllUsersUseCase,
) : ViewModel() {

    var authenticatedState: AuthenticatedState by mutableStateOf(AuthenticatedState.Unknown)
        private set

    init {
        viewModelScope.launch {
            isAuthenticatedUseCase().distinctUntilChanged().collect { isLoggedIn ->
                authenticatedState = if (isLoggedIn) {
                    AuthenticatedState.Authenticated
                } else {
                    AuthenticatedState.UnAuthenticated
                }

                if (isLoggedIn) {
                    synchronizeCache()
                    synchronizeUser()
                    synchronizeAllUsers()
                }
            }
        }
    }

    private fun synchronizeCache() {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeCacheUseCase()
        }
    }

    private fun synchronizeUser() {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeUserUseCase()
        }
    }

    private fun synchronizeAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeAllUsersUseCase()
        }
    }
}
