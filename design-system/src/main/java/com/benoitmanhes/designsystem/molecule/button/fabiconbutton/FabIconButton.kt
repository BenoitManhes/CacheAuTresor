package com.benoitmanhes.designsystem.molecule.button.fabiconbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.contentColorFor
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun FabIconButton(
    icon: IconSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: FabButtonType = FabButtonType.COLORED,
    color: Color = CTTheme.color.primary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    when (type) {
        FabButtonType.OUTLINED -> {
            Box(
                modifier = modifier
                    .size(Dimens.Size.fabIconButtonSize)
                    .clip(CTTheme.shape.small)
                    .border(width = CTTheme.stroke.medium, color = color, shape = CTTheme.shape.small)
                    .background(CTTheme.color.surface)
                    .clickable(
                        onClick = onClick,
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = rememberRipple()
                    ),
                contentAlignment = Alignment.Center,
            ) {
                CTIcon(
                    icon = icon,
                    size = Dimens.IconSize.Large,
                    color = color,
                )
            }
        }

        FabButtonType.COLORED -> {
            val contentColor = contentColorFor(backgroundColor = color)
            Box(
                modifier = modifier
                    .size(Dimens.Size.fabIconButtonSize)
                    .clip(CTTheme.shape.small)
                    .background(color)
                    .clickable(
                        onClick = onClick,
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = rememberRipple()
                    ),
                contentAlignment = Alignment.Center,
            ) {
                CTIcon(
                    icon = icon,
                    size = Dimens.IconSize.Large,
                    color = contentColor,
                )
            }
        }
    }
}
