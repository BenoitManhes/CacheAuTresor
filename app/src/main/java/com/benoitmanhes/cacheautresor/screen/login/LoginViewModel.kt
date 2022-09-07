package com.benoitmanhes.cacheautresor.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.ViewModel
import com.benoitmanhes.cacheautresor.common.composable.textfield.DoubleTextField

class LoginViewModel : ViewModel() {

    var state: LoginViewModelState by mutableStateOf(
        value = LoginViewModelState()
    )
        private set
}