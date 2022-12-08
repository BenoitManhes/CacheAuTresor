package com.benoitmanhes.cacheautresor.screen.authentication.connection

import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.ConnectionInputState
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus

data class ConnectionInputUIState(
    val connectionInputState: ConnectionInputState = ConnectionInputState.Login,
    val valueLoginEmail: String? = null,
    val valueLoginPwd: String? = null,
    val valueRegisterCode: String? = null,
    val loadingLogin: Boolean = false,
    val loadingRegister: Boolean = false,
    val errorLogin: CTDomainError? = null,
    val errorRegister: CTDomainError? = null,
    val errorSnackbar: CTDomainError? = null,
) {
    val isLoginTextVisible: Boolean = connectionInputState == ConnectionInputState.Login
    val isRegisterTextVisible: Boolean = connectionInputState == ConnectionInputState.Register

    private val isLoginEnable: Boolean = !valueLoginEmail.isNullOrEmpty() && !valueLoginPwd.isNullOrEmpty()
    private val isRegisterEnable: Boolean = !valueRegisterCode.isNullOrEmpty()

    val loginButtonStatus: ButtonStatus = when {
        loadingLogin -> ButtonStatus.LOADING
        isLoginEnable -> ButtonStatus.ENABLE
        else -> ButtonStatus.DISABLE
    }
    val registerButtonStatus: ButtonStatus = when {
        loadingRegister -> ButtonStatus.LOADING
        isRegisterEnable -> ButtonStatus.ENABLE
        else -> ButtonStatus.DISABLE
    }
}
