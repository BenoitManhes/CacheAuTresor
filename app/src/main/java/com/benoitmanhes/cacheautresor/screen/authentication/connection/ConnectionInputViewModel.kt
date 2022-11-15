package com.benoitmanhes.cacheautresor.screen.authentication.connection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.ConnectionInputState
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.usecase.authentication.CheckAuthCodeUseCase
import com.benoitmanhes.domain.usecase.authentication.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
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
            errorLogin = null,
        )
    }

    fun updateLoginPassword(value: String) {
        uiState = uiState.copy(
            valueLoginPwd = value,
            errorLogin = null,
        )
    }

    fun updateRegisterCode(value: String) {
        uiState = uiState.copy(
            valueRegisterCode = value,
            errorRegister = null,
        )
    }

    fun consumeSnackbarError() {
        uiState = uiState.copy(
            errorSnackbar = null,
        )
    }

    private fun login() {
        loginJob?.cancel()
        if (uiState.valueLoginEmail?.isEmailValid() == true && uiState.valueLoginPwd?.isPasswordValid() == true) {
            loginJob = viewModelScope.launch(Dispatchers.IO) {
                loginUseCase.invoke(
                    identifier = uiState.valueLoginEmail!!,
                    password = uiState.valueLoginPwd!!,
                ).collect { loginResult ->
                    when (loginResult) {
                        is CTResult.Loading -> {
                            uiState = uiState.copy(loadingLogin = true)
                        }
                        is CTResult.Success -> {
                            uiState = uiState.copy(
                                loadingLogin = false,
                                valueLoginPwd = null,
                                valueRegisterCode = null,
                            )
                        }
                        is CTResult.Failure -> {
                            Timber.e(loginResult.error)
                            uiState = when (loginResult.error?.code) {
                                CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM,
                                CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST,
                                CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL,
                                -> uiState.copy(
                                    errorLogin = loginResult.error,
                                    loadingLogin = false,
                                )

                                else -> uiState.copy(
                                    errorSnackbar = loginResult.error,
                                    loadingLogin = false,
                                )
                            }
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
                    is CTResult.Loading -> {
                        uiState = uiState.copy(loadingRegister = true)
                    }
                    is CTResult.Failure -> {
                        Timber.e(result.error)
                        uiState = when (result.error?.code) {
                            CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN -> uiState.copy(
                                errorRegister = result.error,
                                loadingRegister = false,
                            )

                            else -> uiState.copy(
                                errorSnackbar = result.error,
                                loadingRegister = false,
                            )
                        }
                    }
                    is CTResult.Success -> {
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

    private fun String.isEmailValid(): Boolean = this.isNotBlank()
    private fun String.isPasswordValid(): Boolean = this.isNotBlank()
}