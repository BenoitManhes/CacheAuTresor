package com.benoitmanhes.cacheautresor.screen.lock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec

@Composable
fun LockScreen(
    message: TextSpec,
    image: ImageSpec,
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
            CTImage(
                image = image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
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
