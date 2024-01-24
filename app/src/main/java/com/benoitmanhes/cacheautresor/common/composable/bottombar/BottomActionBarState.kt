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
    val firstButton: PrimaryButtonState? = null,
    val secondButton: PrimaryButtonState? = null,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        BottomActionBar(
            title = title,
            message = message,
            firstButton = firstButton,
            secondButton = secondButton,
            modifier = modifier

        )
    }
}
