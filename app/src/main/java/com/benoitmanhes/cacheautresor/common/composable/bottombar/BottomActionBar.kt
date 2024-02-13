package com.benoitmanhes.cacheautresor.common.composable.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun BottomActionBar(
    modifier: Modifier = Modifier,
    message: TextSpec? = null,
    title: TextSpec? = null,
    primaryButton: PrimaryButtonState? = null,
    secondaryButton: PrimaryButtonState? = null,
) {
    Surface(
        modifier = modifier
            .wrapContentSize(),
        color = CTTheme.color.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
        ) {
            CTDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CTTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
            ) {
                // Text column
                if (title != null || message != null) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
                    ) {
                        CTTextView(
                            text = title,
                            style = CTTheme.typography.bodyBold,
                            color = CTTheme.color.textOnSurface,
                        )
                        CTTextView(
                            text = message,
                            style = CTTheme.typography.bodySmall,
                            color = CTTheme.color.textOnSurface,
                        )
                    }
                }

                // Buttons row
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
                ) {
                    secondaryButton
                        ?.copy(type = PrimaryButtonType.OUTLINED)
                        ?.Content(
                            modifier = Modifier.weight(1f),
                        )

                    primaryButton
                        ?.copy(type = PrimaryButtonType.COLORED)
                        ?.Content(
                            modifier = Modifier
                                .weight(1f),
                        )
                }
            }
        }
    }
}
