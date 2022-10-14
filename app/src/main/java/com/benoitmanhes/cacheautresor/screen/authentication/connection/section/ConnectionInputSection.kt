package com.benoitmanhes.cacheautresor.screen.authentication.connection.section

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.button.ButtonStyle
import com.benoitmanhes.cacheautresor.common.composable.button.StyleButton
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.common.composable.divider.TextDivider
import com.benoitmanhes.cacheautresor.common.composable.textfield.DoubleTextField
import com.benoitmanhes.cacheautresor.common.composable.textfield.OutlinedTextField
import com.benoitmanhes.cacheautresor.screen.authentication.connection.ConnectionInputViewModel
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
internal fun LoginInputSection(
    onAccountTokenValid: (accountToken: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ConnectionInputViewModel = hiltViewModel(),
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(viewModel.navigateToAccountCreation) {
        viewModel.navigateToAccountCreation?.let(onAccountTokenValid)
    }

    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        AnimatedVisibility(
            visible = viewModel.state.isLoginTextVisible,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
        ) {
            Column {
                DoubleTextField(
                    valueTop = viewModel.state.valueLoginEmail,
                    valueBottom = viewModel.state.valueLoginPwd,
                    focusRequester = focusRequester,
                    labelTopRes = R.string.loginScreen_login_emailTextField_label,
                    labelBottomRes = R.string.loginScreen_login_passwordTextField_label,
                    onTextTopChanged = { viewModel.updateLoginEmail(it) },
                    onTextBottomChanged = { viewModel.updateLoginPassword(it) },
                )
                Spacer(size = Dimens.Margin.medium)
            }
        }
        StyleButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.loginScreen_loginButton_label),
            buttonStyle = if (viewModel.state.connectionInputState == ConnectionInputState.Login) ButtonStyle.Filled else ButtonStyle.Outlined,
            isEnabled = { viewModel.state.isLoginEnable || it == ButtonStyle.Outlined },
            isLoading = viewModel.state.loadingLogin,
            onClick = { viewModel.clickLogin() },
        )
        Spacer(size = Dimens.Margin.large)
        TextDivider()
        Spacer(size = Dimens.Margin.large)
        AnimatedVisibility(
            visible = viewModel.state.isRegisterTextVisible,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
        ) {
            Column {
                OutlinedTextField(
                    focusRequester = focusRequester,
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.state.valueRegisterCode,
                    color = AppTheme.colors.secondary,
                    labelRes = R.string.loginScreen_register_codeText_label,
                    onTextChanged = { viewModel.updateRegisterCode(it) },
                )
                Spacer(size = Dimens.Margin.medium)
            }
        }
        StyleButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.loginScreen_registerButton_label),
            isEnabled = { viewModel.state.isRegisterEnable || it == ButtonStyle.Outlined },
            isLoading = viewModel.state.loadingRegister,
            buttonStyle = if (viewModel.state.connectionInputState == ConnectionInputState.Register) ButtonStyle.Filled else ButtonStyle.Outlined,
            color = AppTheme.colors.secondary,
            onClick = { viewModel.clickRegister() },
        )
    }
}

@Preview
@Composable
private fun PreviewLoginInputSection() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 64.dp,
                ),
            contentAlignment = Alignment.Center
        ) {
            LoginInputSection(onAccountTokenValid = {})
        }
    }
}
