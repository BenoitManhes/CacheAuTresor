package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.topbar.DefaultTopBar
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.colorScheme
import com.benoitmanhes.designsystem.theme.spacing
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCreationScreen(
    onNavigateBack: () -> Unit,
    showSnackbar: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = TextSpec.Resources(R.string.accountCreation_topBar_title),
                onNavBack = onNavigateBack,
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.immense,
                    vertical = MaterialTheme.spacing.large,
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Image(
                    modifier = Modifier.size(Dimens.Size.accountCreationImageSize),
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
