package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.button.FilledButton
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.common.composable.textfield.DoubleTextField
import com.benoitmanhes.cacheautresor.common.composable.textfield.OutlinedTextField
import com.benoitmanhes.cacheautresor.screen.authentication.connection.ConnectionInputViewModel
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun AccountCreationInputSection(
    modifier: Modifier = Modifier,
    viewModel: ConnectionInputViewModel = hiltViewModel(),
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            focusRequester = focusRequester,
            labelRes = R.string.accountCreation_name_label,
            errorRes = R.string.accountCreation_name_error,
        )
        Spacer(size = Dimens.Margin.large)
//        DoubleTextField(
//            valueTop =,
//            valueBottom =,
//            focusRequester = focusRequester,
//        )
//        Spacer(size = Dimens.Margin.large)
//        FilledButton(text = )
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