package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheCharacteristicsSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheDescriptionSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheGeoSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheTypeSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CartographerSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.jaugesSection
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsViewModelState
import com.benoitmanhes.designsystem.atoms.dividerItem
import com.benoitmanhes.designsystem.molecule.card.CTInfoCard
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed

@Composable
fun CacheDetailRecapScreen(
    uiState: CacheDetailsViewModelState.Data,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(vertical = CTTheme.spacing.large),
    ) {
        jaugesSection(this, uiState)
        divider()

        uiState.infoCardState?.let { cardState ->
            CTInfoCard.item(scope = this, state = cardState)
            divider()
        }

        CacheTypeSection.item(scope = this, state = uiState.typeSectionState)
        divider()

        CartographerSection.item(this, state = uiState.cartographerSectionState)
        divider()

        CacheGeoSection.item(this, distanceText = uiState.distanceText, coordinates = uiState.cacheCoordinates)
        divider()

        CacheDescriptionSection.item(scope = this, description = uiState.description)

        uiState.characteristics.takeIf { it.isNotEmpty() }?.let {
            divider()
            CacheCharacteristicsSection.item(scope = this, characteristics = uiState.characteristics)
        }
    }
}

private fun LazyListScope.divider() {
    dividerItem(
        modifier = Modifier.composed {
            padding(horizontal = CTTheme.spacing.large, vertical = CTTheme.spacing.large)
        },
        color = CTTheme.composed { color.disable },
    )
}
