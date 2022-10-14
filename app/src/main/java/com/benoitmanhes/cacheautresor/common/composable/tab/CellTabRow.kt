package com.benoitmanhes.cacheautresor.common.composable.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun <T : TabItem> CellTabRow(
    itemSelected: T,
    items: List<T>,
    onItemClicked: (T) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
) {
    val indexSelected = items.indexOf(itemSelected).coerceAtLeast(0)

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CellIndicator(contentColor, Modifier.tabIndicatorOffset(tabPositions[indexSelected]))
    }

    TabRow(
        selectedTabIndex = indexSelected,
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(100)),
        contentColor = contentColor,
        indicator = indicator,
    ) {
        items.forEach { tabItem ->
            Tab(
                text = { stringResource(id = tabItem.resTitle) },
                selected = tabItem == itemSelected,
                onClick = { onItemClicked(tabItem) },
                selectedContentColor = backgroundColor,
                unselectedContentColor = contentColor,
            )
        }
    }
}

@Composable
private fun CellIndicator(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxSize()
            .background(color, RoundedCornerShape(100))
    )
}

sealed class CellTabItem : TabItem {
    class Map(override val resTitle: Int = R.string.bottomBar_explore) : CellTabItem()
    class List(override val resTitle: Int = R.string.bottomBar_news) : CellTabItem()
}

@Preview
@Composable
private fun PreviewCellTab() {
    AppTheme {
        val tabs = listOf(CellTabItem.Map(), CellTabItem.List())
        var currentItem by remember { mutableStateOf(tabs.first()) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CellTabRow(
                itemSelected = currentItem,
                items = tabs,
                onItemClicked = { currentItem = it },
            )
        }
    }
}