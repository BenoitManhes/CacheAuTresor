package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.navigation.unauthenticated.AccountCreationDestination
import com.benoitmanhes.domain.structure.BResult
import com.benoitmanhes.domain.usecase.register.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AccountCreationInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val accountUseCase: CreateAccountUseCase,
) : ViewModel() {

    var state: AccountCreationInputViewModelState by mutableStateOf(AccountCreationInputViewModelState())
        private set

    private val tokenAccount: String = savedStateHandle.get<String>(AccountCreationDestination.accountTokenArgument) ?: ""

    fun updateEmail(value: String) {
        state = state.copy(valueEmail = value)
    }

    fun updatePwd(value: String) {
        state = state.copy(valuePwd = value)
    }

    fun updateName(value: String) {
        state = state.copy(valueName = value)
    }

    fun validate() {
        viewModelScope.launch(Dispatchers.IO) {
            accountUseCase(
                tokenAccount = tokenAccount,
                email = state.valueEmail!!,
                password = state.valuePwd!!,
                explorerName = state.valueName!!,
            ).collect { result ->
                when (result) {
                    is BResult.Loading -> {
                        state = state.copy(loading = true)
                    }
                    is BResult.Failure -> {
                        state = state.copy(
                            loading = false,
                        )
                        Timber.e(result.error)
                    }
                    is BResult.Success -> {
                        state = state.copy(
                            loading = false,
                        )
                    }
                }
            }
        }
    }
}