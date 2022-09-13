package com.benoitmanhes.cacheautresor.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.screen.main.holder.AuthenticatedState
import com.benoitmanhes.domain.usecase.user.IsAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppContentViewModel @Inject constructor(
    isAuthenticatedUseCase: IsAuthenticatedUseCase
) : ViewModel() {

    var authenticatedState: AuthenticatedState by mutableStateOf(AuthenticatedState.Unknown)
        private set

    init {
        viewModelScope.launch {
            isAuthenticatedUseCase().collect { isLoggedIn ->
                authenticatedState = if (isLoggedIn) {
                    AuthenticatedState.Authenticated
                } else {
                    AuthenticatedState.UnAuthenticated
                }
            }
        }
    }
}
