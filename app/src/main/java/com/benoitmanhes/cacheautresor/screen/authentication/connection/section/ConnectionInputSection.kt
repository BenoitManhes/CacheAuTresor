package com.benoitmanhes.cacheautresor.screen.authentication.connection.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.error.localizedDescription
import com.benoitmanhes.cacheautresor.screen.authentication.connection.ConnectionInputViewModel
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.molecule.divider.CTDividerText
import com.benoitmanhes.designsystem.molecule.textfield.CTDoubleTextField
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.molecule.textfield.InputType
import com.benoitmanhes.designsystem.molecule.textfield.TextFieldType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme

@Composable
internal fun LoginInputSection(
    onAccountTokenValid: (accountToken: String) -> Unit,
    showErrorSnackBar: (errorMsg: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ConnectionInputViewModel = hiltViewModel(),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    val uiState = viewModel.uiState
    val snackbarMessage = uiState.errorSnackbar?.localizedDescription()?.value()?.text
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            showErrorSnackBar(snackbarMessage)
            viewModel.consumeSnackbarError()
        }
    }

    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        AnimatedVisibility(
            visible = uiState.isLoginTextVisible,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
        ) {
            Column {
                CTDoubleTextField(
                    valueTop = uiState.valueLoginEmail,
                    valueBottom = uiState.valueLoginPwd,
                    labelTop = TextSpec.Resources(R.string.loginScreen_login_emailTextField_label),
                    labelBottom = TextSpec.Resources(R.string.loginScreen_login_passwordTextField_label),
                    errorText = uiState.errorLogin?.localizedDescription(),
                    isError = uiState.errorLogin != null,
                    onValueTopChanged = { viewModel.updateLoginEmail(it) },
                    onValueBottomChanged = { viewModel.updateLoginPassword(it) },
                    imeAction = ImeAction.Done,
                    inputTypeTop = InputType.Email,
                    inputTypeBottom = InputType.Password,
                    textFieldTypeBottom = TextFieldType.PASSWORD,
                )
                SpacerSmall()
            }
        }
        Crossfade(targetState = uiState.connectionInputState) { state ->
            if (state == ConnectionInputState.Login) {
                CTPrimaryButton(
                    text = TextSpec.Resources(R.string.loginScreen_loginButton_label),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.login()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    status = uiState.loginButtonStatus,
                )
            } else {
                CTPrimaryButton(
                    text = TextSpec.Resources(R.string.loginScreen_loginButton_label),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.updateConnexionState(ConnectionInputState.Login)
                    },
                    type = PrimaryButtonType.OUTLINED,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        SpacerLarge()
        CTDividerText(TextSpec.Resources(R.string.orSeparator_label))
        SpacerLarge()
        CTTheme(CTColorTheme.Cartography) {
            AnimatedVisibility(
                visible = uiState.isRegisterTextVisible,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
            ) {
                Column {
                    CTOutlinedTextField(
                        value = uiState.valueRegisterCode,
                        onValueChange = { viewModel.updateRegisterCode(it) },
                        modifier = Modifier.fillMaxWidth(),
                        labelText = TextSpec.Resources(R.string.loginScreen_register_codeText_label),
                        errorText = uiState.errorRegister?.localizedDescription(),
                        isError = uiState.errorRegister != null,
                    )
                    SpacerSmall()
                }
            }
            Crossfade(targetState = uiState.connectionInputState, label = "crossfade") { state ->
                if (state == ConnectionInputState.Register) {
                    CTPrimaryButton(
                        text = TextSpec.Resources(R.string.loginScreen_registerButton_label),
                        onClick = {
                            keyboardController?.hide()
                            viewModel.register(onAccountTokenValid)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        status = uiState.registerButtonStatus,
                    )
                } else {
                    CTPrimaryButton(
                        text = TextSpec.Resources(R.string.loginScreen_registerButton_label),
                        onClick = {
                            keyboardController?.hide()
                            viewModel.updateConnexionState(ConnectionInputState.Register)
                        },
                        type = PrimaryButtonType.OUTLINED,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}
