package com.benoitmanhes.cacheautresor.screen.home.encyclopedia

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.screen.emptyscreen.CTEmptyScreen
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.utils.ImageSpec

@Composable
fun EncyclopediaScreen(
    innerPadding: PaddingValues,
) {
    CTEmptyScreen(
        illustration = ImageSpec.ResImage(R.drawable.illustr_encyclopedia),
        message = TextSpec.Resources(R.string.encyclopedia_lock_message),
        modifier = Modifier.padding(innerPadding),
    )
}
