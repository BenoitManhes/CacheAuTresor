package com.benoitmanhes.cacheautresor.screen.home.create.mycaches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.spacer.SpacerExtraLarge
import com.benoitmanhes.designsystem.atoms.spacer.SpacerHuge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec

@Composable
fun MyCachesScreen(
    uiState: MyCachesViewModelState,
) {
    when (uiState) {
        is MyCachesViewModelState.Init -> {} /* nothing */
        is MyCachesViewModelState.Empty -> {
            EmptyScreen(uiState)
        }

        is MyCachesViewModelState.Data -> {
            DataScreen(uiState)
        }
    }

}

@Composable
private fun DataScreen(uiState: MyCachesViewModelState.Data) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(CTTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
    ) {
        item(key = "myCachesHeader") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
            ) {
                CTTextView(
                    text = uiState.headerText,
                    style = CTTheme.typography.body,
                    color = CTTheme.color.onPrimary,
                )

                CTImage(
                    image = ImageSpec.ResImage(R.drawable.hand_drawn_arrow),
                    modifier = Modifier
                        .size(AppDimens.MyCaches.handDrawArrowSize),
                    colorFilter = ColorFilter.tint(CTTheme.color.onPrimary),
                )
            }
        }

        uiState.cacheCards.map { it.lazyItem(this) }

        SpacerHuge.item(this)
    }
}

@Composable
private fun EmptyScreen(uiState: MyCachesViewModelState.Empty) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = CTTheme.spacing.immense),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CTImage(
            image = ImageSpec.ResImage(R.drawable.logo_monochrome),
            modifier = Modifier
                .size(AppDimens.EmptyScreen.imageSize),
            colorFilter = ColorFilter.tint(CTTheme.color.onPrimary),
        )

        SpacerExtraLarge()

        CTTextView(
            text = uiState.message,
            style = CTTheme.typography.body,
            color = CTTheme.color.onPrimary,
        )
    }
}