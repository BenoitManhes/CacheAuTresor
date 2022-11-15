package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import com.benoitmanhes.core.error.CTDomainError

data class AccountCreationInputViewModelState(
    val valueEmail: String? = null,
    val valuePwd: String? = null,
    val valueName: String? = null,
    val loading: Boolean = false,
    val errorUserName: CTDomainError? = null,
    val errorCredential: CTDomainError? = null,
    val errorSnackbar: CTDomainError? = null,
) {
    val isValidateButtonEnabled: Boolean = !valueEmail.isNullOrEmpty() && !valueName.isNullOrEmpty() && !valuePwd.isNullOrEmpty()
}
