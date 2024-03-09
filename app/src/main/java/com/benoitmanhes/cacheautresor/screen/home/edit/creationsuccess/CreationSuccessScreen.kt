package com.benoitmanhes.cacheautresor.screen.home.edit.creationsuccess

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.CTScreenWrapper
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.spacer.SpacerWeight
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec

@Composable
fun CreationSuccessRoute(
    close: () -> Unit,
    viewModel: CreationSuccessViewModel = hiltViewModel(),
) {
    val cacheName by viewModel.cacheName.collectAsState()

    CTScreenWrapper(colorTheme = CTColorTheme.Cartography) {
        CreationSuccessScreen(
            cacheName = cacheName,
            close = close,
        )
    }
}

@Composable
private fun CreationSuccessScreen(
    cacheName: TextSpec?,
    close: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CTTheme.color.background)
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(CTTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
    ) {
        SpacerWeight()

        CTImage(
            image = ImageSpec.ResImage(R.drawable.illustr_cache_created),
            modifier = Modifier
                .fillMaxWidth()
                .padding(CTTheme.spacing.large)
                .aspectRatio(1f),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = CTTheme.spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large)
        ) {
            CTTextView(
                text = TextSpec.Resources(R.string.creationSuccess_message),
                style = CTTheme.typography.header1,
                color = CTTheme.color.textDefault,
                textAlign = TextAlign.Center,
            )
            CTTextView(
                text = cacheName,
                style = CTTheme.typography.header2,
                color = CTTheme.color.textPrimary,
                textAlign = TextAlign.Center,
            )
        }

        SpacerWeight()

        CTPrimaryButton(
            text = TextSpec.Resources(R.string.creationSuccess_button),
            onClick = close,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
