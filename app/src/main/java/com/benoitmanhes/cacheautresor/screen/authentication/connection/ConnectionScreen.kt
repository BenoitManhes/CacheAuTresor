package com.benoitmanhes.cacheautresor.screen.authentication.connection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.CompassSection
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.LoginInputSection
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConnectionScreen(
    navigateToAccountCreation: (accountToken: String) -> Unit,
    showErrorSnackBar: (errorMsg: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            contentAlignment = Alignment.Center,
        ) {
            CompassSection(
                modifier = Modifier.size(Dimens.Size.loginImageSize),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            contentAlignment = Alignment.TopCenter
        ) {
            LoginInputSection(
                onAccountTokenValid = navigateToAccountCreation,
                showErrorSnackBar = showErrorSnackBar,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.immense),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    CTTheme {
        ConnectionScreen({}, {})
    }
}
