package com.benoitmanhes.cacheautresor.screen.home

import androidx.lifecycle.ViewModel
import com.benoitmanhes.domain.usecase.user.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    fun logout() {
        logoutUseCase()
    }
}