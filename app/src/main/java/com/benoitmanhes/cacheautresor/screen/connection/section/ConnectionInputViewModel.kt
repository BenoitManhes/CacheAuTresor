package com.benoitmanhes.cacheautresor.screen.connection.section

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ConnectionInputViewModel : ViewModel() {

    var state: ConnectionInputViewModelState by mutableStateOf(
        value = ConnectionInputViewModelState()
    )
        private set

    fun clickLogin() {
        when (state.connectionInputState) {
            ConnectionInputState.Login -> {}
            ConnectionInputState.Register -> updateConnexionState(ConnectionInputState.Login)
        }
    }

    fun clickRegister() {
        when (state.connectionInputState) {
            ConnectionInputState.Login -> updateConnexionState(ConnectionInputState.Register)
            ConnectionInputState.Register -> {}
        }
    }

    fun updateLoginEmail(value: String) {
        state = state.copy(
            valueLoginEmail = value,
        )
    }

    fun updateLoginPassword(value: String) {
        state = state.copy(
            valueLoginPwd = value,
        )
    }

    fun updateRegisterEmail(value: String) {
        state = state.copy(
            valueRegisterEmail = value,
        )
    }

    fun updateRegisterPassword(value: String) {
        state = state.copy(
            valueRegisterPwd = value,
        )
    }

    private fun updateConnexionState(value: ConnectionInputState) {
        state = state.copy(
            connectionInputState = value,
        )
    }
}