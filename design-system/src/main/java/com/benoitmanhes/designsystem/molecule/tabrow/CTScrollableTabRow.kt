package com.benoitmanhes.designsystem.molecule.tabrow

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ComposableContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: ComposableContent,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        CTDivider(color = CTTheme.color.disable)

        PrimaryScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = CTTheme.spacing.large,
            divider = {},
            contentColor = CTTheme.color.primary,
            containerColor = Color.Transparent,
            indicator = @Composable { tabPositions ->
                Indicator(tabPositions = tabPositions, selectedTabIndex = selectedTabIndex)
            },
            modifier = modifier,
            tabs = tabs,
        )
    }
}

@Composable
private fun Indicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val width by animateDpAsState(targetValue = tabPositions[selectedTabIndex].contentWidth)
    TabRowDefaults.PrimaryIndicator(
        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
        width = width,
    )
}
