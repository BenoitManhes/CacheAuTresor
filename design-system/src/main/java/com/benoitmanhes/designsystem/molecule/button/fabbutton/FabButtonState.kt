package com.benoitmanhes.designsystem.molecule.button.fabbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTTheme

data class FabButtonState(
    val icon: IconSpec,
    val onClick: () -> Unit,
    val text: TextSpec? = null,
    val type: FabButtonType = FabButtonType.COLORED,
) {
    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        color: Color = CTTheme.color.primary,
        contentColor: Color = CTTheme.color.onPrimary,
    ) {
        CTFabButton(
            state = this,
            modifier = modifier,
            color = color,
            contentColor = contentColor,
        )
    }
}
