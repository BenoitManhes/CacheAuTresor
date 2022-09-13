package com.benoitmanhes.cacheautresor.screen.connection.section

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.domain.usecase.user.AuthenticationUseCase
import com.benoitmanhes.domain.utils.BResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectionInputViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {

    var state: ConnectionInputViewModelState by mutableStateOf(
        value = ConnectionInputViewModelState()
    )
        private set

    fun clickLogin() {
        when (state.connectionInputState) {
            ConnectionInputState.Login -> login()
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

    private fun login() {
        viewModelScope.launch {
            authenticationUseCase.invoke(
                identifier = state.valueLoginEmail,
                password = state.valueLoginPwd,
            ).collect { loginResult ->
                when (loginResult) {
                    is BResult.Loading -> {}
                    is BResult.Success -> {}
                    is BResult.Failure -> {}
                }
            }
        }
    }

    private fun updateConnexionState(value: ConnectionInputState) {
        state = state.copy(
            connectionInputState = value,
        )
    }
}