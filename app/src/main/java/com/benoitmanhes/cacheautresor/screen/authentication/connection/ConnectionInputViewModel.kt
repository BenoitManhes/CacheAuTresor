package com.benoitmanhes.cacheautresor.screen.authentication.connection

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.ConnectionInputState
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.domain.usecase.authentication.CheckAuthCodeUseCase
import com.benoitmanhes.domain.usecase.authentication.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectionInputViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase,
) : ViewModel() {

    var state: ConnectionInputViewModelState by mutableStateOf(
        value = ConnectionInputViewModelState()
    )
        private set

    private var loginJob: Job? = null
    private var registerJob: Job? = null

    fun clickLogin() {
        when (state.connectionInputState) {
            ConnectionInputState.Login -> login()
            ConnectionInputState.Register -> updateConnexionState(ConnectionInputState.Login)
        }
    }

    fun clickRegister(onAccountTokenValid: (accountToken: String) -> Unit) {
        when (state.connectionInputState) {
            ConnectionInputState.Login -> updateConnexionState(ConnectionInputState.Register)
            ConnectionInputState.Register -> register(onAccountTokenValid)
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

    fun updateRegisterCode(value: String) {
        state = state.copy(
            valueRegisterCode = value,
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
                                valueRegisterCode = null,
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

    private fun register(onAccountTokenValid: (accountToken: String) -> Unit) {
        registerJob?.cancel()
        registerJob = viewModelScope.launch {
            checkAuthCodeUseCase.invoke(state.valueRegisterCode!!).collect { registerResult ->
                when (registerResult) {
                    is BResult.Loading -> {
                        state = state.copy(loadingRegister = true)
                    }
                    is BResult.Success -> {
                        state = state.copy(
                            loadingRegister = false,
                            valueLoginPwd = null,
                        )
                        onAccountTokenValid.invoke(registerResult.successData)
                    }
                    is BResult.Failure -> {
                        state = state.copy(
                            loadingRegister = false,
                        )
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