package com.benoitmanhes.cacheautresor.screen.authentication.connection

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.ConnectionInputState
import com.benoitmanhes.domain.structure.BError
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

    var uiState: ConnectionInputUIState by mutableStateOf(
        value = ConnectionInputUIState()
    )
        private set

    private var loginJob: Job? = null
    private var registerJob: Job? = null

    fun clickLogin() {
        when (uiState.connectionInputState) {
            ConnectionInputState.Login -> login()
            ConnectionInputState.Register -> updateConnexionState(ConnectionInputState.Login)
        }
    }

    fun clickRegister(onAccountTokenValid: (accountToken: String) -> Unit) {
        when (uiState.connectionInputState) {
            ConnectionInputState.Login -> updateConnexionState(ConnectionInputState.Register)
            ConnectionInputState.Register -> register(onAccountTokenValid)
        }
    }

    fun updateLoginEmail(value: String) {
        uiState = uiState.copy(
            valueLoginEmail = value,
        )
    }

    fun updateLoginPassword(value: String) {
        uiState = uiState.copy(
            valueLoginPwd = value,
        )
    }

    fun updateRegisterCode(value: String) {
        uiState = uiState.copy(
            valueRegisterCode = value,
            errorRegister = null,
        )
    }

    private fun login() {
        loginJob?.cancel()
        if (uiState.valueLoginEmail?.isEmailValid() == true && uiState.valueLoginPwd?.isPasswordValid() == true) {
            loginJob = viewModelScope.launch {
                loginUseCase.invoke(
                    identifier = uiState.valueLoginEmail!!,
                    password = uiState.valueLoginPwd!!,
                ).collect { loginResult ->
                    when (loginResult) {
                        is BResult.Loading -> {
                            uiState = uiState.copy(loadingLogin = true)
                        }
                        is BResult.Success -> {
                            uiState = uiState.copy(
                                loadingLogin = false,
                                valueLoginPwd = null,
                                valueRegisterCode = null,
                            )
                        }
                        is BResult.Failure -> {
                            uiState = uiState.copy(loadingLogin = false)
                        }
                    }
                }
            }
        }
    }

    private fun register(onAccountTokenValid: (accountToken: String) -> Unit) {
        registerJob?.cancel()
        registerJob = viewModelScope.launch {
            checkAuthCodeUseCase(code = uiState.valueRegisterCode ?: "").collect { result ->
                when (result) {
                    is BResult.Loading -> {
                        uiState = uiState.copy(loadingRegister = true)
                    }
                    is BResult.Failure -> {
                        uiState = uiState.copy(
                            loadingRegister = false,
                            errorRegister = result.error as? BError.AuthenticationCodeInvalidError,
                        )
                    }
                    is BResult.Success -> {
                        uiState = uiState.copy(
                            loadingRegister = false,
                            valueRegisterCode = null,
                        )
                        onAccountTokenValid(result.successData)
                    }
                }
            }
        }
    }

    private fun updateConnexionState(value: ConnectionInputState) {
        when (value) {
            ConnectionInputState.Register -> loginJob?.cancel()
            ConnectionInputState.Login -> registerJob?.cancel()
        }
        uiState = uiState.copy(
            connectionInputState = value,
            loadingLogin = uiState.loadingLogin && value == ConnectionInputState.Login,
            loadingRegister = uiState.loadingRegister && value == ConnectionInputState.Register,
        )
    }

    private fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun String.isPasswordValid(): Boolean = this.isNotBlank()
}