package com.benoitmanhes.cacheautresor.screen.connection.section

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.domain.usecase.user.LoginUseCase
import com.benoitmanhes.domain.structure.BResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectionInputViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    var state: ConnectionInputViewModelState by mutableStateOf(
        value = ConnectionInputViewModelState()
    )
        private set

    private var loginJob: Job? = null

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
        loginJob?.cancel()
        if (state.valueLoginEmail?.isEmailValid() == true && state.valueLoginPwd?.isPasswordValid() == true) {
            loginJob = viewModelScope.launch {
                loginUseCase.invoke(
                    identifier = state.valueLoginEmail!!,
                    password = state.valueLoginPwd!!,
                ).collect { loginResult ->
                    when (loginResult) {
                        is BResult.Loading -> {
                            state = state.copy(loadingLogin = true)
                        }
                        is BResult.Success -> {
                            state = state.copy(
                                loadingLogin = false,
                                valueLoginPwd = null,
                                valueRegisterPwd = null,
                            )
                        }
                        is BResult.Failure -> {
                            state = state.copy(loadingLogin = false)
                        }
                    }
                }
            }
        }
    }

    private fun updateConnexionState(value: ConnectionInputState) {
        if (value == ConnectionInputState.Register) {
            loginJob?.cancel()
        }
        state = state.copy(
            connectionInputState = value,
            loadingLogin = state.loadingLogin && value == ConnectionInputState.Login,
            loadingRegister = state.loadingRegister && value == ConnectionInputState.Register,
        )
    }

    private fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun String.isPasswordValid(): Boolean = this.isNotBlank()
}