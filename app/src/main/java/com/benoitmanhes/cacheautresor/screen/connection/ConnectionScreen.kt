package com.benoitmanhes.cacheautresor.screen.connection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.connection.section.LoginInputSection
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun ConnectionScreen(
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
            Image(
                modifier = Modifier.size(Dimens.Size.loginImageSize),
                painter = painterResource(id = R.drawable.logo_monochrome),
                contentDescription = null,
                colorFilter = ColorFilter.tint(AppTheme.colors.onBackground),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            contentAlignment = Alignment.TopCenter
        ) {
            LoginInputSection(
                modifier = Modifier
                    .padding(horizontal = Dimens.Margin.huge),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    AppTheme {
        ConnectionScreen()
    }
}
