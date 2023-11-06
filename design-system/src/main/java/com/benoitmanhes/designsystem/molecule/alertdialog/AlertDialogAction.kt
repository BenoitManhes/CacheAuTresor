package com.benoitmanhes.designsystem.molecule.alertdialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.molecule.button.secondaryButton.CTSecondaryButton
import com.benoitmanhes.designsystem.molecule.button.secondaryButton.SecondaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec

data class AlertDialogAction(
    val text: TextSpec,
    val type: Type = Type.Neutral,
    val onClick: () -> Unit,
) {
    enum class Type {
        Cancel, Neutral, Confirm, Dangerous;

        val color: Color
            @Composable
            get() = when (this) {
                Cancel,
                Neutral,
                -> CTTheme.color.onSurface

                Confirm -> CTTheme.color.primary

                Dangerous -> CTTheme.color.error
            }
    }

    @Composable
    fun Content() {
        CTSecondaryButton(
            text = text,
            onClick = onClick,
            type = when (type) {
                Type.Dangerous -> SecondaryButtonType.Text
                else -> SecondaryButtonType.Text
            },
            color = { type.color },
        )
    }
}
