package com.benoitmanhes.designsystem.molecule.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun CTSelectionCard(
    icon: IconSpec,
    title: TextSpec,
    description: TextSpec?,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconColor = remember(isSelected) {
        CTTheme.composed { if (isSelected) color.textOnSurfacePrimary else color.textOnSurface }
    }
    val iconSurfaceColor = remember(isSelected) {
        CTTheme.composed { if (isSelected) color.surfacePrimary else color.surface }
    }
    val contentSurfaceColor = remember(isSelected) {
        CTTheme.composed { if (isSelected) color.surfacePrimarySoft else color.surface }
    }

    CTHorizontalCard(
        onClick = onClick,
        modifier = modifier,
        leadingContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .background(iconSurfaceColor()),
                contentAlignment = Alignment.Center,
            ) {
                CTIcon(
                    icon = icon,
                    size = Dimens.IconSize.XLarge,
                    color = iconColor(),
                    modifier = Modifier.padding(CTTheme.spacing.small),
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(contentSurfaceColor()),
                contentAlignment = Alignment.CenterStart,
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(CTTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
                ) {
                    CTTextView(
                        text = title,
                        style = CTTheme.typography.bodyBold,
                        color = CTTheme.color.textOnSurface,
                    )
                    CTTextView(
                        text = description,
                        style = CTTheme.typography.bodySmall,
                        color = CTTheme.color.textOnSurface,
                    )
                }
            }
        },
    )
}

@Stable
data class CTSelectionCardState(
    val icon: IconSpec,
    val title: TextSpec,
    val description: TextSpec?,
    val isSelected: Boolean,
    val colorTheme: CTColorTheme,
    val onClick: () -> Unit,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CTTheme(colorTheme) {
            CTSelectionCard(
                icon = icon,
                title = title,
                description = description,
                isSelected = isSelected,
                onClick = onClick,
                modifier = modifier
            )
        }
    }

    fun lazyItem(
        scope: LazyListScope,
        key: Any = title.hashCode(),
    ) {
        scope.item(
            key = key,
            contentType = contentType,
            content = { Content() },
        )
    }

    companion object {
        private const val contentType: String = "CTSelectionCard"
    }
}

@Composable
@Preview
private fun PreviewCTSelectionCard() {
    CTTheme(CTColorTheme.Classical) {
        var isSelected by remember { mutableStateOf(false) }

        CTSelectionCard(
            icon = CTTheme.icon.Parchment.toIconSpec(),
            title = TextSpec.loreumIpsum(4),
            description = TextSpec.loreumIpsum(12),
            isSelected = isSelected,
            onClick = { isSelected = !isSelected },
        )
    }
}
