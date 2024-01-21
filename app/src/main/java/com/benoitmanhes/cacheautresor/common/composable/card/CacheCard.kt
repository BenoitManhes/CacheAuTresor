package com.benoitmanhes.cacheautresor.common.composable.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.spacer.SpacerMicro
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.atoms.text.CTTextMultiSize
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.card.CTHorizontalCard
import com.benoitmanhes.designsystem.molecule.label.LabelIconSmall
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Alert
import com.benoitmanhes.designsystem.res.icons.iconpack.Box
import com.benoitmanhes.designsystem.res.icons.iconpack.Difficulty
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.res.icons.iconpack.Mystery
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun CacheCard(
    icon: IconSpec,
    typeText: TextSpec,
    cacheIdText: TextSpec?,
    titleText: TextSpec,
    difficultyText: TextSpec,
    groundText: TextSpec,
    sizeText: TextSpec,
    modifier: Modifier = Modifier,
    titleColor: Color = CTTheme.color.onSurface,
    highlightColor: Color = CTTheme.color.primary,
    borderColor: Color = CTTheme.color.border,
    trailingContent: CacheCardTrailing?,
    onClick: () -> Unit,
) {
    CTHorizontalCard(
        modifier = modifier,
        borderColor = borderColor,
        onClick = onClick,
        leadingContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .background(CTTheme.color.primary),
                contentAlignment = Alignment.Center,
            ) {
                CTIcon(
                    icon = icon,
                    size = Dimens.IconSize.XLarge,
                    color = CTTheme.color.onPrimary,
                    modifier = Modifier.padding(CTTheme.spacing.small),
                )
            }
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CTTheme.spacing.small)
                .padding(end = CTTheme.spacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        ) {
            Column(
                modifier.weight(1f),
            ) {
                Subtitles(
                    subtitleHighlight = typeText,
                    highlightColor = highlightColor,
                    subtitleOptional = cacheIdText,
                )
                SpacerMicro()

                CTTextView(
                    text = titleText,
                    color = titleColor,
                    style = CTTheme.typography.body,
                )
                SpacerSmall()

                Row(
                    horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large)
                ) {
                    LabelIconSmall(
                        icon = IconSpec.VectorIcon(CTTheme.icon.Difficulty, null),
                        text = difficultyText,
                        color = highlightColor,
                    )
                    LabelIconSmall(
                        icon = IconSpec.VectorIcon(CTTheme.icon.Mountain, null),
                        text = groundText,
                        color = highlightColor,
                    )
                    LabelIconSmall(
                        icon = IconSpec.VectorIcon(CTTheme.icon.Box, null),
                        text = sizeText,
                        color = highlightColor,
                    )
                }
            }
            trailingContent?.Content()
        }
    }
}

@Composable
private fun Subtitles(
    subtitleHighlight: TextSpec,
    subtitleOptional: TextSpec?,
    highlightColor: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.micro),
    ) {
        CTTextView(
            text = subtitleHighlight,
            style = CTTheme.typography.captionBold,
            color = highlightColor,
        )
        subtitleOptional?.let {
            CTTextView(
                text = "•".textSpec(),
                style = CTTheme.typography.captionBold,
                color = CTTheme.color.onSurface,
            )
            CTTextView(
                text = subtitleOptional,
                style = CTTheme.typography.caption,
                color = CTTheme.color.onSurface,
            )
        }
    }
}

@Immutable
data class CacheCardState(
    val itemId: String,
    val cacheColorTheme: CTColorTheme,
    val name: TextSpec,
    val icon: IconSpec,
    val typeText: TextSpec,
    val cacheIdText: TextSpec?,
    val difficultyText: TextSpec,
    val groundText: TextSpec,
    val sizeText: TextSpec,
    val trailingContent: CacheCardTrailing?,
    val onClick: () -> Unit,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CTTheme(cacheColorTheme) {
            val isError = trailingContent is CacheCardTrailing.Warning
            CacheCard(
                icon = icon,
                typeText = typeText,
                cacheIdText = cacheIdText,
                titleText = name,
                difficultyText = difficultyText,
                groundText = groundText,
                sizeText = sizeText,
                trailingContent = trailingContent,
                onClick = onClick,
                highlightColor = if (isError) CTTheme.color.critical else CTTheme.color.primary,
                titleColor = if (isError) CTTheme.color.critical else CTTheme.color.onSurface,
                borderColor = if (isError) CTTheme.color.critical else CTTheme.color.border,
                modifier = modifier,
            )
        }
    }

    fun lazyItem(
        scope: LazyListScope,
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = itemId,
            contentType = contentType,
        ) {
            Content(modifier)
        }
    }

    companion object {
        private const val contentType: String = "CacheCard"
    }
}

@Immutable
sealed interface CacheCardTrailing {
    @Composable
    fun Content()

    @JvmInline
    value class Progress(val progress: Float) : CacheCardTrailing {
        @Composable
        override fun Content() {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = CTTheme.color.primary,
                    trackColor = CTTheme.color.disable,
                    strokeCap = StrokeCap.Round,
                    strokeWidth = CTTheme.stroke.circularProgress,
                    modifier = Modifier
                        .size(AppDimens.MyCaches.progressSize)
                        .rotate(180f),
                )
                CTTextMultiSize(
                    firstText = (progress * 100).toInt().toString().textSpec(),
                    secondText = "%".textSpec(),
                    firstTextStyle = CTTheme.typography.captionBold,
                    secondTextStyle = CTTheme.typography.captionBold.copy(
                        fontSize = CTTheme.typography.captionBold.fontSize / 2,
                    ),
                    color = CTTheme.color.primary,
                )
            }
        }
    }

    @JvmInline
    value class Point(val text: TextSpec) : CacheCardTrailing {
        @Composable
        override fun Content() {
            CTTextMultiSize(
                firstText = text,
                secondText = TextSpec.Resources(R.string.common_pointsUnit),
                firstTextStyle = CTTheme.typography.bodyBold,
                secondTextStyle = CTTheme.typography.captionBold,
                color = CTTheme.color.onSurface,
            )
        }
    }

    data object Warning : CacheCardTrailing {
        @Composable
        override fun Content() {
            CTIcon(
                icon = CTTheme.icon.Alert.toIconSpec(),
                size = Dimens.IconSize.Medium,
                color = CTTheme.color.critical,
            )
        }
    }
}

@Preview
@Composable
fun CacheCardPreview() {
    val defaulCardState = CacheCardState(
        itemId = "id",
        cacheColorTheme = CTColorTheme.Mystery,
        name = "Tuto 01: Passerelle vers l’inconnu".textSpec(),
        icon = CTTheme.icon.Mystery.toIconSpec(),
        typeText = TextSpec.Resources(R.string.cache_type_mystery),
        cacheIdText = null,
        difficultyText = "3".textSpec(),
        groundText = "2.5".textSpec(),
        sizeText = "moyenne".textSpec(),
        trailingContent = null,
        onClick = {},
    )

    CTTheme(CTColorTheme.Cartography) {
        Column(
            modifier = Modifier.padding(CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        ) {
            defaulCardState.copy(
                cacheColorTheme = CTColorTheme.Cartography,
                trailingContent = CacheCardTrailing.Progress(0.01f),
                groundText = "-".textSpec(),
                sizeText = "-".textSpec(),
                icon = Icons.Rounded.QuestionMark.toIconSpec(),
            ).Content()

            defaulCardState.copy(
                cacheColorTheme = CTColorTheme.Cartography,
                trailingContent = CacheCardTrailing.Progress(0.67f),
                typeText = "Piste".textSpec(),
                icon = CTTheme.icon.Piste.toIconSpec(),
            ).Content()

            defaulCardState.copy(
                cacheColorTheme = CTColorTheme.Mystery,
                trailingContent = CacheCardTrailing.Warning,
                typeText = "Mystery".textSpec(),
                icon = CTTheme.icon.Mystery.toIconSpec(),
            ).Content()

            defaulCardState.copy(
                trailingContent = CacheCardTrailing.Point("127".textSpec()),
                typeText = "Classical".textSpec(),
                cacheColorTheme = CTColorTheme.Classical,
                icon = CTTheme.icon.Parchment.toIconSpec(),
                cacheIdText = "CL-29TC3D".textSpec()
            ).Content()
        }
    }
}
