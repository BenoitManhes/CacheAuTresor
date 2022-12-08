package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus

data class AccountCreationInputViewModelState(
    val valueEmail: String? = null,
    val valuePwd: String? = null,
    val valueName: String? = null,
    val errorUserName: CTDomainError? = null,
    val errorCredential: CTDomainError? = null,
    val errorSnackbar: CTDomainError? = null,
    private val loading: Boolean = false,
) {
    private val isValidateButtonEnabled: Boolean = !valueEmail.isNullOrEmpty() && !valueName.isNullOrEmpty() && !valuePwd.isNullOrEmpty()
    val validateButtonStatus: ButtonStatus = when {
        loading -> ButtonStatus.LOADING
        isValidateButtonEnabled -> ButtonStatus.ENABLE
        else -> ButtonStatus.DISABLE
    }
}
