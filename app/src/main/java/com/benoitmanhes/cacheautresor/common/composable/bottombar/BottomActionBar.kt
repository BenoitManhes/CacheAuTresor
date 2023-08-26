package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun BottomActionBar(
    state: BottomActionBarState,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .wrapContentSize(),
        color = CTTheme.color.surface,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CTDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CTTheme.spacing.large),
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
            ) {

                CTPrimaryButton(
                    modifier = Modifier.weight(1f),
                    state = state.firstButtonState.copy(type = PrimaryButtonType.COLORED)
                )

                state.secondButtonState?.let { buttonState ->
                    CTPrimaryButton(
                        modifier = Modifier.weight(1f),
                        state = buttonState.copy(type = PrimaryButtonType.OUTLINED)
                    )
                }
            }
        }
    }
}
