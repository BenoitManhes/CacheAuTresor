package com.benoitmanhes.designsystem.molecule.button.fabbutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CTFabButton(
    state: FabButtonState,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.surfacePrimary,
    contentColor: Color = CTTheme.color.textOnSurfacePrimary,
) {
    val attribute = provideFabButtonAttribute(type = state.type, color = color, contentColor = contentColor)
    val size = remember { Dimens.FloatingButtonSize.Large }

    Surface(
        onClick = state.onClick,
        modifier = modifier,
        shape = CTTheme.shape.medium,
        color = attribute.backgroundColor,
        contentColor = attribute.backgroundColor,
        elevation = CTTheme.elevation.medium,
        border = attribute.border,
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = size.button,
                    minHeight = size.button,
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(CTTheme.spacing.large),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CTIcon(
                    icon = state.icon(),
                    size = size.icon,
                    color = attribute.contentColor,
                )
                state.text?.let {
                    SpacerSmall()
                    CTTextView(
                        text = state.text,
                        style = CTTheme.typography.bodyBold,
                        color = attribute.contentColor,
                    )
                    SpacerSmall()
                }
            }
        }
    }
}

@Composable
private fun provideFabButtonAttribute(type: FabButtonType, color: Color, contentColor: Color): FabButtonParameters = when (type) {
    FabButtonType.COLORED -> FabButtonParameters(
        contentColor = contentColor,
        backgroundColor = color,
        border = null,
    )

    FabButtonType.OUTLINED -> FabButtonParameters(
        contentColor = color,
        backgroundColor = contentColorFor(color),
        border = BorderStroke(
            width = CTTheme.stroke.thin,
            color = color,
        ),
    )
}
