package com.benoitmanhes.designsystem.molecule.sticker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.CTVerticalDivider
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTStickerIcon(
    icon: IconSpec,
    text: TextSpec,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clip(CTTheme.shape.small)
            .background(CTTheme.color.surface)
            .border(CTTheme.stroke.medium, CTTheme.color.strokeBorder, CTTheme.shape.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(CTTheme.color.surfacePrimary)
                .padding(
                    vertical = CTTheme.spacing.extraSmall,
                    horizontal = CTTheme.spacing.small,
                ),
        ) {
            CTIcon(
                icon = icon,
                size = Dimens.IconSize.Medium,
                color = CTTheme.color.textOnSurfacePrimary,
            )
        }

        CTVerticalDivider(
            modifier = modifier.fillMaxHeight(),
            color = CTTheme.color.strokeBorder,
            stroke = CTTheme.stroke.medium,
        )
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    vertical = CTTheme.spacing.extraSmall,
                    horizontal = CTTheme.spacing.small,
                ),
            contentAlignment = Alignment.CenterStart,
        ) {
            CTTextView(
                text = text,
                color = CTTheme.color.textOnSurface,
                style = CTTheme.typography.body,
            )
        }
    }
}

@Stable
data class CTStickerIconState(
    val icon: ComposeProvider<IconSpec>,
    val text: TextSpec,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CTStickerIcon(icon = icon(), text = text, modifier = modifier)
    }
}

@Composable
@Preview
private fun PreviewCTStickerIcon() {
    CTTheme(CTColorTheme.Mystery) {
        CTStickerIcon(icon = CTTheme.icon.Mystery, text = TextSpec.RawString("Mystery"))
    }
}
