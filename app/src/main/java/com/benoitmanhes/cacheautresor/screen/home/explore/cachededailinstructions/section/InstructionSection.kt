package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun InstructionSection() {
    val context = LocalContext.current
    Section(title = TextSpec.Resources(R.string.cacheDetail_instructionsSection_title)) {
    }
}