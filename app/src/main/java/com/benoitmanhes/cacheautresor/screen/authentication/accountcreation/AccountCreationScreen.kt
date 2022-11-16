package com.benoitmanhes.cacheautresor.screen.authentication.accountcreation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCreationScreen(
    onNavigateBack: () -> Unit,
    showSnackbar: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(onNavigateBack)
        },
        containerColor = AppTheme.colors.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(
                    horizontal = Dimens.Margin.huge,
                    vertical = Dimens.Margin.large,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(onNavigateBack: () -> Unit) {
    SmallTopAppBar(
        title = {
            TextView(
                textRes = R.string.accountCreation_topBar_title,
                style = AppTheme.typography.header1,
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
    )
}

@Preview
@Composable
private fun PreviewAccountCreationScreen() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            AccountCreationScreen({}) {}
        }
    }
}
