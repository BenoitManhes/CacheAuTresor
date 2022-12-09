package com.benoitmanhes.designsystem.atoms.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun SpacerExtraSmall() {
    Spacer(modifier = Modifier.size(CTTheme.spacing.extraSmall))
}
