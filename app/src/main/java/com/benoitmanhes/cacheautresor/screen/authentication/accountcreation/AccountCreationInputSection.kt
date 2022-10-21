package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.button.LoadingFilledButton
import com.benoitmanhes.cacheautresor.common.composable.textfield.DoubleTextField
import com.benoitmanhes.cacheautresor.common.composable.textfield.OutlinedTextField
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun AccountCreationInputSection(
    modifier: Modifier = Modifier,
    viewModel: AccountCreationInputViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = viewModel.state.valueName,
            labelRes = R.string.accountCreation_name_label,
            hasNext = true,
            onTextChanged = { viewModel.updateName(it) }
        )
        Spacer(modifier = Modifier.weight(0.2f))
        DoubleTextField(
            valueTop = viewModel.state.valueEmail,
            valueBottom = viewModel.state.valuePwd,
            labelTopRes = R.string.accountCreation_email_label,
            labelBottomRes = R.string.accountCreation_password_label,
            onTextTopChanged = { viewModel.updateEmail(it) },
            onTextBottomChanged = { viewModel.updatePwd(it) },
        )
        Spacer(modifier = Modifier.weight(1f))
        LoadingFilledButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.common_validate),
            enabled = viewModel.state.isValidateButtonEnabled,
            isLoading = viewModel.state.loading,
        )
    }
}

@Preview
@Composable
private fun PreviewAccountCreationInputSection() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            AccountCreationInputSection()
        }
    }
}