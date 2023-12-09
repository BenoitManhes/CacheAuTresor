package com.benoitmanhes.cacheautresor.screen.home.create

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.lock.LockScreen
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CreateScreen(
    innerPadding: PaddingValues,
) {
    LockScreen(
        message = TextSpec.Resources(R.string.create_lock_message),
        modifier = Modifier.padding(innerPadding),
    )
}
