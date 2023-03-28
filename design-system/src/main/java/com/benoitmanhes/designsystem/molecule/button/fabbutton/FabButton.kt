package com.benoitmanhes.designsystem.molecule.button.fabbutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Add
import com.benoitmanhes.designsystem.res.icons.iconpack.Favorite
import com.benoitmanhes.designsystem.res.icons.iconpack.FavoriteFilled
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FabButton(
    icon: IconSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: TextSpec? = null,
    type: FabButtonType = FabButtonType.COLORED,
    color: Color = CTTheme.color.primary,
) {
    val attribute = provideFabButtonAttribute(type = type, color = color)

    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = CTTheme.shape.medium,
        color = attribute.backgroundColor,
        contentColor = attribute.backgroundColor,
        elevation = CTTheme.elevation.none,
        border = attribute.border,
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = Dimens.Size.fabButtonSize, minHeight = Dimens.Size.fabButtonSize),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(CTTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CTIcon(
                    icon = icon,
                    size = Dimens.Size.largeIconSize,
                    color = attribute.contentColor,
                )
                text?.let {
                    SpacerSmall()
                    CTTextView(
                        text = text,
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
private fun provideFabButtonAttribute(type: FabButtonType, color: Color): FabButtonParameters = when (type) {
    FabButtonType.COLORED -> FabButtonParameters(
        contentColor = contentColorFor(color),
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

@Preview
@Composable
private fun PreviewFabButton() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium)
            ) {
                FabButton(
                    icon = IconSpec.VectorIcon(CTTheme.icon.Favorite, null),
                    type = FabButtonType.OUTLINED,
                    onClick = { },
                )
                FabButton(
                    icon = IconSpec.VectorIcon(CTTheme.icon.FavoriteFilled, null),
                    type = FabButtonType.COLORED,
                    onClick = { },
                )
                FabButton(
                    icon = IconSpec.VectorIcon(CTTheme.icon.Add, null),
                    type = FabButtonType.OUTLINED,
                    text = TextSpec.RawString("Ajouter"),
                    onClick = { },
                )
                FabButton(
                    icon = IconSpec.VectorIcon(CTTheme.icon.Add, null),
                    type = FabButtonType.COLORED,
                    text = TextSpec.RawString("Ajouter"),
                    onClick = { },
                )
            }
        }
    }
}
