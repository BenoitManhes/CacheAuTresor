package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

data class AccountCreationInputViewModelState(
    val valueEmail: String? = null,
    val valuePwd: String? = null,
    val valueName: String? = null,
    val loading: Boolean = false,
) {
    val isValidateButtonEnabled: Boolean = !valueEmail.isNullOrEmpty() && !valueName.isNullOrEmpty() && !valuePwd.isNullOrEmpty()
}
