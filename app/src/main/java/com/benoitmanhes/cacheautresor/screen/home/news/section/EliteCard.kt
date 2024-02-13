package com.benoitmanhes.cacheautresor.screen.home.news.section

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.CTIconSlot
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.atoms.text.CTTextMultiSize
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Chevron
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Etoile2
import com.benoitmanhes.designsystem.res.icons.iconpack.Etoile4
import com.benoitmanhes.designsystem.res.icons.iconpack.Logo
import com.benoitmanhes.designsystem.res.icons.iconpack.Rank
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.benoitmanhes.designsystem.utils.extensions.ctClickable
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun EliteCard(
    headerIcon: IconSpec,
    headerTitle: TextSpec,
    explorers: List<EliteCardState.Explorer>,
    onClickRank: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = CTTheme.shape.large,
        border = BorderStroke(CTTheme.stroke.medium, brush = CTTheme.gradient.surfacePrimary),
        color = CTTheme.color.surface,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CTTheme.gradient.surfacePrimary)
                    .padding(horizontal = CTTheme.spacing.medium)
                    .padding(top = CTTheme.spacing.medium, bottom = CTTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
            ) {
                CTIconSlot(
                    icon = headerIcon,
                    size = Dimens.IconSlotSize.Medium,
                    contentColor = CTTheme.color.textOnSurfacePrimary,
                )
                CTTextView(
                    text = headerTitle,
                    style = CTTheme.typography.body,
                    color = CTTheme.color.textOnSurfacePrimary,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CTTheme.spacing.large, vertical = CTTheme.spacing.small),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
            ) {
                EliteUserCell(
                    explorer = explorers.getOrNull(1),
                    medalIcon = CTTheme.icon.Etoile4.toIconSpec(),
                    medalIconSize = Dimens.IconSize.Medium,
                    modifier = Modifier.weight(100f)
                )
                EliteUserCell(
                    explorer = explorers.getOrNull(0),
                    medalIcon = CTTheme.icon.Logo.toIconSpec(),
                    medalIconSize = Dimens.IconSize.Large,
                    modifier = Modifier.weight(120f)
                )
                EliteUserCell(
                    explorer = explorers.getOrNull(2),
                    medalIcon = CTTheme.icon.Etoile2.toIconSpec(),
                    medalIconSize = Dimens.IconSize.Medium,
                    modifier = Modifier.weight(100f)
                )
            }

            CTDivider(
                modifier = Modifier.fillMaxWidth(),
                color = CTTheme.color.strokeDisable,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .ctClickable(onClickRank)
                    .padding(horizontal = CTTheme.spacing.medium, vertical = CTTheme.spacing.small),
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
                ) {
                    CTIcon(icon = CTTheme.icon.Rank.toIconSpec(), size = Dimens.IconSize.Medium)
                    CTTextView(
                        text = TextSpec.Resources(R.string.news_eliteCard_rankButton),
                        style = CTTheme.typography.body,
                        color = CTTheme.color.textOnSurface,
                    )
                    CTIcon(icon = CTTheme.icon.Chevron.toIconSpec(), size = Dimens.IconSize.Medium)
                }
            }
        }
    }
}

@Composable
private fun EliteUserCell(
    explorer: EliteCardState.Explorer?,
    medalIcon: IconSpec,
    medalIconSize: Dimens.IconSize,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
    ) {
        explorer?.let {
            CTIcon(
                icon = medalIcon,
                size = medalIconSize,
            )
            CTImage(
                image = explorer.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(CTTheme.shape.circle),
                contentScale = ContentScale.Crop,
            )
            PseudoAndPts(name = explorer.name, points = explorer.points)
        }
    }
}

@Composable
private fun PseudoAndPts(
    name: TextSpec,
    points: TextSpec,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CTResponsiveText(
            text = name,
            minFontSize = CTTheme.typography.caption.fontSize,
            style = CTTheme.typography.bodyBold,
        )
        CTTextMultiSize(
            firstText = points,
            secondText = TextSpec.Resources(R.string.common_pointsUnit),
            firstTextStyle = CTTheme.typography.bodySmall,
            color = CTTheme.color.textLight,
        )
    }
}

@Stable
data class EliteCardState(
    val explorers: List<Explorer>,
    val onClickRank: () -> Unit,
    val headerIcon: IconSpec,
    val headerTitle: TextSpec,
    val colorTheme: CTColorTheme,
) {
    data class Explorer(
        val image: ImageSpec,
        val name: TextSpec,
        val points: TextSpec,
    )

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CTTheme(colorTheme) {
            EliteCard(
                headerIcon = headerIcon,
                headerTitle = headerTitle,
                explorers = explorers,
                onClickRank = onClickRank,
                modifier = modifier,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewEliteCard() {
    CTTheme {
        EliteCardState(
            explorers = listOf(
                EliteCardState.Explorer(
                    image = ImageSpec.UrlImage(
                        "https://images.genius.com/f568ddc8c714d36d1424cb1604102a02.545x545x1.png"
                    ),
                    name = "Pseudo".textSpec(),
                    points = "125".textSpec(),
                ),
                EliteCardState.Explorer(
                    image = ImageSpec.UrlImage(
                        "https://images.genius.com/f568ddc8c714d36d1424cb1604102a02.545x545x1.png"
                    ),
                    name = "Pseudo".textSpec(),
                    points = "75".textSpec(),
                ),
                EliteCardState.Explorer(
                    image = ImageSpec.UrlImage(
                        "https://images.genius.com/f568ddc8c714d36d1424cb1604102a02.545x545x1.png"
                    ),
                    name = "Pseudo".textSpec(),
                    points = "8".textSpec(),
                ),
            ),
            onClickRank = {},
            headerIcon = CTTheme.icon.Crown.toIconSpec(),
            headerTitle = "Header".textSpec(),
            colorTheme = CTColorTheme.Cartography,
        ).Content(
            modifier = Modifier.padding(CTTheme.spacing.large),
        )
    }
}
