package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountCreationInputViewModel @Inject constructor() : ViewModel() {

    var state: AccountCreationInputViewModelState by mutableStateOf(AccountCreationInputViewModelState())
        private set

    fun updateEmail(value: String) {
        state = state.copy(valueEmail = value)
    }

    fun updatePwd(value: String) {
        state = state.copy(valuePwd = value)
    }

    fun updateName(value: String) {
        state = state.copy(valueName = value)
    }
}