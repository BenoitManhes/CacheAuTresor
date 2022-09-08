package com.benoitmanhes.cacheautresor.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.screen.main.holder.AuthenticatedState
import kotlinx.coroutines.launch

class AppContentViewModel : ViewModel() {

    var authenticatedState: AuthenticatedState by mutableStateOf(AuthenticatedState.Unknown)
        private set

    init {
        viewModelScope.launch {
            authenticatedState = AuthenticatedState.UnAuthenticated
        }
    }
}