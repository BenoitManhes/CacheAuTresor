package com.benoitmanhes.cacheautresor.screen.authentication.connection

import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.ConnectionInputState

data class ConnectionInputViewModelState(
    val connectionInputState: ConnectionInputState = ConnectionInputState.Login,
    val valueLoginEmail: String? = null,
    val valueLoginPwd: String? = null,
    val valueRegisterCode: String? = null,
    val loadingLogin: Boolean = false,
    val loadingRegister: Boolean = false,
) {
    val isLoginTextVisible: Boolean = connectionInputState == ConnectionInputState.Login
    val isRegisterTextVisible: Boolean = connectionInputState == ConnectionInputState.Register
    val isLoginEnable: Boolean = !valueLoginEmail.isNullOrEmpty() && !valueLoginPwd.isNullOrEmpty()
    val isRegisterEnable: Boolean = !valueRegisterCode.isNullOrEmpty()
}
