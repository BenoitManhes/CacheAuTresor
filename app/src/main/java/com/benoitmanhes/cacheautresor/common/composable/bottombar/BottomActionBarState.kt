package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState

@Stable
data class BottomActionBarState(
    val title: TextSpec? = null,
    val message: TextSpec? = null,
    val primaryButton: PrimaryButtonState? = null,
    val secondaryButton: PrimaryButtonState? = null,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        BottomActionBar(
            title = title,
            message = message,
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
            modifier = modifier,
        )
    }
}
