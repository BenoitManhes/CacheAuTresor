package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.navigation.unauthenticated.AccountCreationDestination
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.usecase.register.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AccountCreationInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val accountUseCase: CreateAccountUseCase,
) : ViewModel() {

    var uiState: AccountCreationInputViewModelState by mutableStateOf(AccountCreationInputViewModelState())
        private set

    private val tokenAccount: String = savedStateHandle.get<String>(AccountCreationDestination.accountTokenArgument) ?: ""

    fun updateEmail(value: String) {
        uiState = uiState.copy(
            valueEmail = value,
            errorCredential = null,
        )
    }

    fun updatePwd(value: String) {
        uiState = uiState.copy(
            valuePwd = value,
            errorCredential = null,
        )
    }

    fun updateName(value: String) {
        uiState = uiState.copy(
            valueName = value,
            errorUserName = null,
        )
    }

    fun validate() {
        viewModelScope.launch(Dispatchers.IO) {
            accountUseCase(
                tokenAccount = tokenAccount,
                email = uiState.valueEmail!!,
                password = uiState.valuePwd!!,
                explorerName = uiState.valueName!!,
            ).collect { result ->
                when (result) {
                    is CTResult.Loading -> {
                        uiState = uiState.copy(loading = true)
                    }
                    is CTResult.Failure -> {
                        Timber.e(result.error)
                        uiState = when (result.error?.code) {
                            CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE ->
                                uiState.copy(
                                    loading = false,
                                    errorUserName = result.error,
                                )
                            CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM ->
                                uiState.copy(
                                    loading = false,
                                    errorCredential = result.error,
                                )
                            else -> uiState.copy(
                                loading = false,
                                errorSnackbar = result.error,
                            )
                        }
                    }
                    is CTResult.Success -> {
                        uiState = uiState.copy(
                            loading = false,
                        )
                    }
                }
            }
        }
    }

    fun consumeSnackbarError() {
        uiState = uiState.copy(errorSnackbar = null)
    }
}
