package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.atoms.text.CTTextMultiSize
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec

@Composable
fun RankRow(
    rank: TextSpec,
    explorerImage: ImageSpec,
    explorerName: TextSpec,
    points: TextSpec,
    backGroundGradient: Brush?,
    bold: Boolean,
) {
    val contentTextStyle = if (bold) CTTheme.typography.bodyBold else CTTheme.typography.body
    Box(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .background(CTTheme.color.surface)
            .clip(CTTheme.shape.small)
    ) {
        backGroundGradient?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backGroundGradient, alpha = 0.3f),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = CTTheme.spacing.extraSmall,
                    horizontal = CTTheme.spacing.small,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        ) {
            CTResponsiveText(
                text = rank,
                minFontSize = CTTheme.typography.caption.fontSize,
                modifier = Modifier.width(AppDimens.Rank.rankTextWidth),
                style = contentTextStyle,
                color = CTTheme.color.textOnSurface,
                textAlign = TextAlign.Center,
            )
            CTImage(
                image = explorerImage,
                modifier = Modifier
                    .size(AppDimens.Rank.explorerImageSize)
                    .clip(CTTheme.shape.circle),
                contentScale = ContentScale.Crop,
            )
            CTTextView(
                text = explorerName,
                style = contentTextStyle,
                modifier = Modifier.weight(1f),
                color = CTTheme.color.textOnSurface,
            )
            CTTextMultiSize(
                firstText = points,
                firstTextStyle = contentTextStyle,
                secondText = TextSpec.Resources(R.string.common_pointsUnit),
                color = CTTheme.color.textOnSurface,
            )
        }
    }
}

@Stable
data class RankRowState(
    val rank: TextSpec,
    val explorerImage: ImageSpec,
    val explorerName: TextSpec,
    val points: TextSpec,
    val colorTheme: CTColorTheme,
    val highlight: Boolean,
) {
    private val contentType: String = "RankRow"

    @Composable
    fun Content() {
        CTTheme(colorTheme) {
            RankRow(
                rank = rank,
                explorerImage = explorerImage,
                explorerName = explorerName,
                points = points,
                bold = highlight,
                backGroundGradient = CTTheme.gradient.surfacePrimary.takeIf { highlight },
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun item(
        scope: LazyListScope,
        key: Any,
    ) {
        if (highlight) {
            scope.stickyHeader(key = key, contentType = contentType) {
                Content()
            }
        } else {
            scope.item(key = key, contentType = contentType) {
                Content()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRankRow() {
    CTTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        ) {
            RankRow(
                rank = "28".textSpec(),
                explorerImage = ImageSpec.ResImage(R.drawable.explorer),
                explorerName = "Pseudo".textSpec(),
                points = "125".textSpec(),
                backGroundGradient = null,
                bold = false,
            )
            RankRow(
                rank = "28".textSpec(),
                explorerImage = ImageSpec.ResImage(R.drawable.explorer),
                explorerName = "Pseudo".textSpec(),
                points = "125".textSpec(),
                bold = true,
                backGroundGradient = CTTheme.gradient.surfacePrimary,
            )
        }
    }
}
