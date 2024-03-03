package com.benoitmanhes.cacheautresor.common.screen.pickjauge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.benoitmanhes.designsystem.atoms.dividerItem
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.molecule.jauge.CTJauge
import com.benoitmanhes.designsystem.molecule.topbar.CTFilledTopBar
import com.benoitmanhes.designsystem.molecule.topbar.CTNavAction
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun PickJaugeScreen(
    uiState: PickJaugeUiState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CTFilledTopBar(
                title = uiState.topBarTitle,
                navAction = CTNavAction.Back(navigateBack),
            )
        },
        backgroundColor = CTTheme.color.background,
        bottomBar = {
            uiState.bottomActionBar.Content(modifier = Modifier.imePadding())
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(vertical = CTTheme.spacing.large),
        ) {
            item(key = "jauge", contentType = "jauge") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CTTheme.spacing.large),
                    contentAlignment = Alignment.Center,
                ) {
                    CTJauge(
                        state = uiState.jaugeState,
                        jaugeSize = Dimens.JaugeSize.Large,
                    )
                }
            }

            dividerItem()
            SpacerLarge.item(this)

            uiState.jaugeRow.map { row ->
                row.lazyItem(
                    this,
                    modifier = Modifier.composed {
                        padding(horizontal = CTTheme.spacing.large)
                    },
                )
            }
        }
    }
}
