package com.benoitmanhes.designsystem.molecule.button.fabbutton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.StableMarker
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider

@StableMarker
data class FabButtonState(
    val icon: ComposeProvider<IconSpec>,
    val onClick: () -> Unit,
    val text: TextSpec? = null,
    val type: FabButtonType = FabButtonType.COLORED,
) {
    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        color: Color = CTTheme.color.surfacePrimary,
        contentColor: Color = CTTheme.color.textOnSurfacePrimary,
    ) {
        CTFabButton(
            state = this,
            modifier = modifier,
            color = color,
            contentColor = contentColor,
        )
    }
}
