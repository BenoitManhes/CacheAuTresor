package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.error.localizedDescription
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.textfield.CTDoubleTextField
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.molecule.textfield.InputType
import com.benoitmanhes.designsystem.molecule.textfield.TextFieldType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccountCreationInputSection(
    modifier: Modifier = Modifier,
    viewModel: AccountCreationInputViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
) {
    val snackbarMessage = viewModel.uiState.errorSnackbar?.localizedDescription()?.value()?.text
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            showSnackbar(snackbarMessage)
            viewModel.consumeSnackbarError()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        CTOutlinedTextField(
            value = viewModel.uiState.valueName,
            onValueChange = { viewModel.updateName(it) },
            labelText = TextSpec.Resources(R.string.accountCreation_name_label),
            errorText = viewModel.uiState.errorUserName?.localizedDescription(),
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.weight(0.2f))
        CTDoubleTextField(
            valueTop = viewModel.uiState.valueEmail,
            valueBottom = viewModel.uiState.valuePwd,
            onValueTopChanged = { viewModel.updateEmail(it) },
            onValueBottomChanged = { viewModel.updatePwd(it) },
            labelTop = TextSpec.Resources(R.string.accountCreation_email_label),
            labelBottom = TextSpec.Resources(R.string.accountCreation_password_label),
            errorText = viewModel.uiState.errorCredential?.localizedDescription(),
            inputTypeTop = InputType.Email,
            inputTypeBottom = InputType.Password,
            textFieldTypeBottom = TextFieldType.PASSWORD,
        )
        Spacer(modifier = Modifier.weight(1f))
        CTPrimaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = TextSpec.Resources(R.string.common_validate),
            status = viewModel.uiState.validateButtonStatus,
            onClick = { viewModel.validate() },
        )
    }
}

@Preview
@Composable
private fun PreviewAccountCreationInputSection() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            AccountCreationInputSection {}
        }
    }
}
