package com.benoitmanhes.cacheautresor.screen.connection.section

data class ConnectionInputViewModelState(
    val connectionInputState: ConnectionInputState = ConnectionInputState.Login,
    val valueLoginEmail: String? = null,
    val valueLoginPwd: String? = null,
    val valueRegisterEmail: String? = null,
    val valueRegisterPwd: String? = null,
    val loadingLogin: Boolean = false,
    val loadingRegister: Boolean = false,
) {
    val isLoginTextVisible: Boolean = connectionInputState == ConnectionInputState.Login
    val isRegisterTextVisible: Boolean = connectionInputState == ConnectionInputState.Register
    val isLoginEnable: Boolean = !valueLoginEmail.isNullOrEmpty() && !valueLoginPwd.isNullOrEmpty()
    val isRegisterEnable: Boolean = !valueRegisterEmail.isNullOrEmpty() && !valueRegisterPwd.isNullOrEmpty()
}
