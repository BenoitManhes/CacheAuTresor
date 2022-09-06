package com.benoitmanhes.cacheautresor.screen.login.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.button.ButtonStyle
import com.benoitmanhes.cacheautresor.common.composable.button.StyleButton
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.common.composable.divider.TextDivider
import com.benoitmanhes.cacheautresor.common.composable.textfield.DoubleTextField
import com.benoitmanhes.cacheautresor.screen.login.LoginState
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
internal fun LoginInputSection(
    loginState: LoginState,
    valueLoginMail: String?,
    valueLoginPwd: String?,
    valueRegisterMail: String?,
    valueRegisterPwd: String?,
    onLoginMailChange: (String) -> Unit,
    onLoginPwdChange: (String) -> Unit,
    onRegisterMailChange: (String) -> Unit,
    onRegisterPwdChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit = { },
    onClickRegister: () -> Unit = { },
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        AnimatedVisibility(
            visible = loginState == LoginState.Login,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
        ) {
            Column {
                DoubleTextField(
                    valueTop = valueLoginMail,
                    valueBottom = valueLoginPwd,
                    focusRequester = focusRequester,
                    labelTopRes = R.string.loginScreen_login_emailTextField_label,
                    labelBottomRes = R.string.loginScreen_login_passwordTextField_label,
                    onTextTopChanged = onLoginMailChange,
                    onTextBottomChanged = onLoginPwdChange,
                )
                Spacer(size = Dimens.Margin.medium)
            }
        }
        StyleButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.loginScreen_loginButton_label),
            buttonStyle = if (loginState == LoginState.Login) ButtonStyle.Filled else ButtonStyle.Outlined,
            isEnabled = { !valueLoginMail.isNullOrEmpty() && !valueLoginPwd.isNullOrEmpty() || it == ButtonStyle.Outlined },
            onClick = onClickLogin,
        )
        Spacer(size = Dimens.Margin.large)
        TextDivider()
        Spacer(size = Dimens.Margin.large)
        AnimatedVisibility(
            visible = loginState == LoginState.Register,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
        ) {
            Column {
                DoubleTextField(
                    valueTop = valueRegisterMail,
                    valueBottom = valueRegisterPwd,
                    focusRequester = focusRequester,
                    labelTopRes = R.string.logenScreen_register_emailTextField_label,
                    labelBottomRes = R.string.logenScreen_register_passwordTextField_label,
                    onTextTopChanged = onRegisterMailChange,
                    onTextBottomChanged = onRegisterPwdChange,
                )
                Spacer(size = Dimens.Margin.medium)
            }
        }
        StyleButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.loginScreen_registerButton_label),
            isEnabled = { !valueRegisterMail.isNullOrEmpty() && !valueRegisterPwd.isNullOrEmpty() || it == ButtonStyle.Outlined },
            buttonStyle = if (loginState == LoginState.Register) ButtonStyle.Filled else ButtonStyle.Outlined,
            onClick = onClickRegister,
        )
    }
}

@Preview
@Composable
private fun PreviewLoginInputSection() {
    AppTheme {
        val focusRequester = remember { FocusRequester() }
        var textLoginMail by remember { mutableStateOf("") }
        var textLoginPwd by remember { mutableStateOf("") }
        var textRegisterMail by remember { mutableStateOf("") }
        var textRegisterPwd by remember { mutableStateOf("") }
        var loginState by remember { mutableStateOf(LoginState.Login) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 64.dp,
                ),
            contentAlignment = Alignment.Center
        ) {
            LoginInputSection(
                loginState = loginState,
                valueLoginMail = textLoginMail,
                valueLoginPwd = textLoginPwd,
                valueRegisterMail = textRegisterMail,
                valueRegisterPwd = textRegisterPwd,
                onLoginMailChange = { textLoginMail = it },
                onLoginPwdChange = { textLoginPwd = it },
                onRegisterMailChange = { textRegisterMail = it },
                onRegisterPwdChange = { textRegisterPwd = it },
                onClickLogin = {
                    loginState = LoginState.Login
                },
                onClickRegister = {
                    loginState = LoginState.Register
                },
                focusRequester = focusRequester,
            )
        }
    }
}