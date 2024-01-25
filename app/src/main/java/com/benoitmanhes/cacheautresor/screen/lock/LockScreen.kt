package com.benoitmanhes.cacheautresor.screen.lock

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun LockScreen(
    message: TextSpec,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = modifier.padding(CTTheme.spacing.huge),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.immense),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_monochrome),
                contentDescription = null,
                modifier = Modifier
                    .size(AppDimens.LockScreen.imageSize),
                colorFilter = ColorFilter.tint(CTTheme.color.disable),
            )
            CTTextView(
                text = message,
                style = CTTheme.typography.body,
                color = CTTheme.color.textLight,
                textAlign = TextAlign.Center,
            )
        }
    }
}
