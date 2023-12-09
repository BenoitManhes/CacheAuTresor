package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.molecule.topbar.CTTopBar
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun AccountCreationScreen(
    onNavigateBack: () -> Unit,
    showSnackbar: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        topBar = {
            CTTopBar(
                title = TextSpec.Resources(R.string.accountCreation_topBar_title),
                navAction = CTNavAction.Back(onNavigateBack),
                modifier = Modifier.statusBarsPadding(),
            )
        },
        backgroundColor = CTTheme.color.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(CTTheme.color.background)
                .padding(
                    horizontal = CTTheme.spacing.immense,
                    vertical = CTTheme.spacing.large,
                ).imePadding(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Image(
                    modifier = Modifier.size(MainImageSize),
                    painter = painterResource(id = R.drawable.explorer),
                    contentDescription = null,
                )
            }
            AccountCreationInputSection(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f),
                showSnackbar = showSnackbar,
            )
        }
    }
}

private val MainImageSize: Dp = 180.dp

@Preview
@Composable
private fun PreviewAccountCreationScreen() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            AccountCreationScreen({}) {}
        }
    }
}
