package com.benoitmanhes.cacheautresor.screen.authentication.connection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.CompassSection
import com.benoitmanhes.cacheautresor.screen.authentication.connection.section.LoginInputSection
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun ConnectionScreen(
    navigateToAccountCreation: (accountToken: String) -> Unit,
    showErrorSnackBar: (errorMsg: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(CTTheme.color.background)
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
                modifier = Modifier.size(LoginImageSize),
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
                    .padding(horizontal = CTTheme.spacing.immense),
            )
        }
    }
}

private val LoginImageSize: Dp = 128.dp

@Preview
@Composable
private fun PreviewLoginScreen() {
    CTTheme {
        ConnectionScreen({}, {})
    }
}
