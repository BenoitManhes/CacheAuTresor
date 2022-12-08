package com.benoitmanhes.designsystem.atoms.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.theme.spacing

@Composable
fun SpacerTiny() {
    Spacer(modifier = Modifier.size(MaterialTheme.spacing.extraSmall))
}