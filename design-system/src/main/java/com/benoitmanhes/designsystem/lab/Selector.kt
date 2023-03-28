package com.benoitmanhes.designsystem.lab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun Selector(
    items: List<SelectorItem>,
    selectedItem: SelectorItem,
    onSelectedItem: (SelectorItem) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CTTheme.color.primary,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    state: SelectorState = rememberSelectorState(
        items = items,
        selectedItem = selectedItem,
        selectedColor = backgroundColor,
        unselectedColor = contentColor,
    ),
) {
    require(items.size >= 2) { "This composable requires at least 2 options" }

    LaunchedEffect(key1 = items, key2 = selectedItem) {
        state.selectedItem(this, items.indexOf(selectedItem))
    }

    Layout(
        modifier = modifier
            .height(Dimens.Size.selectorHeight)
            .clip(CTTheme.shape.circle)
            .background(backgroundColor)
            .padding(CTTheme.spacing.micro),
        content = {
            val itemColor = state.itemsContentColor

            items.mapIndexed { index, item ->
                SelectorOption(
                    contentColor = itemColor[index],
                    item = item,
                    onSelectedItem = onSelectedItem,
                    modifier = Modifier.layoutId(ItemId)
                )
            }
            Box(
                modifier = Modifier
                    .layoutId(SelectorId)
                    .clip(CTTheme.shape.circle)
                    .background(contentColor)
            )
        },
    ) { mesurables, constraints ->

        val itemWidth = constraints.maxWidth / items.size
        val itemConstraints = Constraints.fixedWidth(width = itemWidth)

        val itemPlaceables = mesurables
            .filter { it.layoutId == ItemId }
            .map { measurable ->
                measurable.measure(itemConstraints)
            }

        val maxHeight = itemPlaceables.maxOf { it.height }

        val selectorConstraints = Constraints.fixed(width = itemWidth, height = maxHeight)
        val selectorPlaceable = mesurables
            .first { it.layoutId == SelectorId }
            .measure(selectorConstraints)

        layout(constraints.maxWidth, maxHeight) {
            selectorPlaceable.placeRelative(x = (state.selectedIndex * itemWidth).toInt(), y = 0)

            itemPlaceables.forEachIndexed { index, placeable ->
                placeable.placeRelative(x = index * itemWidth, y = 0)
            }
        }
    }
}

@Composable
private fun SelectorOption(
    contentColor: Color,
    item: SelectorItem,
    onSelectedItem: (SelectorItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .widthIn(min = Dimens.Size.selectorMinWidth)
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onSelectedItem(item) },
        contentAlignment = Alignment.Center,
    ) {
        CTTextView(
            text = item.text,
            style = CTTheme.typography.bodyBold,
            color = contentColor,
            modifier = Modifier.padding(horizontal = CTTheme.spacing.extraSmall, vertical = CTTheme.spacing.small),
            maxLine = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private const val ItemId: String = "ItemId"
private const val SelectorId: String = "SelectorId"

@Preview
@Composable
private fun PreviewSelectorLayout() {
    CTTheme {
        val selectorItems = listOf(
            SelectorItem(TextSpec.RawString("Lorem")),
            SelectorItem(TextSpec.RawString("Ipsum")),
            SelectorItem(TextSpec.RawString("Dolor")),
        )
        var selectedItem by remember { mutableStateOf(selectorItems.first()) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Selector(
                items = selectorItems,
                selectedItem = selectedItem,
                onSelectedItem = { selectedItem = it },
                modifier = Modifier.width(300.dp),
            )
        }
    }
}
