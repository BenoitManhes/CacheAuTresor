package com.benoitmanhes.designsystem.molecule.caches

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIconSlot
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.label.LabelIconMedium
import com.benoitmanhes.designsystem.molecule.label.LabelIconSmall
import com.benoitmanhes.designsystem.molecule.sticker.CTSticker
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CacheCard(
    icon: IconSpec,
    creatorText: TextSpec,
    titleText: TextSpec,
    difficultyText: TextSpec,
    groundText: TextSpec,
    sizeText: TextSpec,
    distanceText: TextSpec?,
    stickerText: TextSpec?,
    modifier: Modifier = Modifier,
    color: Color = CTTheme.color.primary,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.Size.cacheCardHeight),
        shape = CTTheme.shape.medium,
        border = BorderStroke(CTTheme.stroke.strong, color),
        color = CTTheme.color.surface,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(CTTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        ) {
            CTIconSlot(
                icon = icon,
                size = Dimens.IconSlotSize.Huge,
                backgroundColor = color,
                contentColor = CTTheme.color.textOnSurfacePrimary,
            )
            MainInfoLayout(
                color = color,
                creatorText = creatorText,
                titleText = titleText,
                difficultyText = difficultyText,
                groundText = groundText,
                sizeText = sizeText,
            )
            distanceText?.let {
                LabelIconMedium(
                    icon = CTTheme.icon.Location,
                    text = distanceText,
                    color = color,
                )
            }
            stickerText?.let {
                CTSticker(label = stickerText)
            }
        }
    }
}

@Composable
private fun RowScope.MainInfoLayout(
    color: Color,
    creatorText: TextSpec,
    titleText: TextSpec,
    difficultyText: TextSpec,
    groundText: TextSpec,
    sizeText: TextSpec,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
    ) {
        CTTextView(
            text = creatorText,
            style = CTTheme.typography.caption,
            maxLine = 1,
        )
        CTResponsiveText(
            text = titleText,
            style = CTTheme.typography.header1,
            minFontSize = Dimens.Font.cacheCardTitleMinFontSize,
            maxLines = 1,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large)
        ) {
            LabelIconSmall(
                icon = CTTheme.icon.Difficulty,
                text = difficultyText,
                color = color,
            )
            LabelIconSmall(
                icon = CTTheme.icon.Mountain,
                text = groundText,
                color = color,
            )
            LabelIconSmall(
                icon = CTTheme.icon.Box,
                text = sizeText,
                color = color,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCacheCard() {
    CTTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(CTTheme.spacing.large),
            contentAlignment = Alignment.Center,
        ) {
            CacheCard(
                icon = CTTheme.icon.Logo,
                color = CTTheme.color.primary,
                creatorText = TextSpec.RawString("Creator"),
                titleText = TextSpec.RawString("Title: Cache title"),
                difficultyText = TextSpec.RawString("difficulty"),
                groundText = TextSpec.RawString("ground"),
                sizeText = TextSpec.RawString("size"),
                distanceText = TextSpec.RawString("800m"),
                stickerText = null,
            )
        }
    }
}
