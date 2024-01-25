package com.benoitmanhes.cacheautresor.screen.home.profile.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.card.CTCardLarge
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Flag
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun ExplorerCard(
    explorerName: TextSpec,
    profileImage: ImageSpec,
    explorationPts: Int,
    numberCacheFounded: Int,
    cartographyPts: Int,
    numberCacheCreated: Int,
    modifier: Modifier = Modifier,
) {
    CTCardLarge(
        modifier = modifier.composed { padding(horizontal = CTTheme.spacing.extraLarge) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = CTTheme.spacing.extraLarge,
                    horizontal = CTTheme.spacing.large,
                ),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.veryLarge),
        ) {
            // Explorer
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CTImage(
                    image = profileImage,
                    modifier = Modifier
                        .size(AppDimens.Profile.explorerImageSize)
                        .clip(CTTheme.shape.circle),
                    contentScale = ContentScale.Crop,
                )
                CTTextView(
                    text = explorerName,
                    style = CTTheme.typography.header1,
                )
            }

            // Exploration stats
            CTTheme(CTColorTheme.Explore) {
                PointsSectionProfile(
                    header = TextSpec.Resources(R.string.profile_explorationSection_title),
                    icon = CTTheme.icon.Crown.toIconSpec(),
                    points = explorationPts,
                    cacheNumber = numberCacheFounded,
                )
            }

            // Cartography stats
            CTTheme(CTColorTheme.Cartography) {
                PointsSectionProfile(
                    header = TextSpec.Resources(R.string.profile_cartographySection_title),
                    icon = CTTheme.icon.Flag.toIconSpec(),
                    points = cartographyPts,
                    cacheNumber = numberCacheCreated,
                )
            }
        }
    }
}

@Stable
data class ExplorerCardState(
    val explorerName: TextSpec,
    val profileImage: ImageSpec,
    val explorationPts: Int,
    val numberCacheFounded: Int,
    val cartographyPts: Int,
    val numberCacheCreated: Int,
) {
    companion object {
        private const val contentType: String = "explorerCard"
    }

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
    ) {
        ExplorerCard(
            explorerName = explorerName,
            profileImage = profileImage,
            explorationPts = explorationPts,
            numberCacheFounded = numberCacheFounded,
            cartographyPts = cartographyPts,
            numberCacheCreated = numberCacheCreated,
            modifier = modifier,
        )
    }

    fun item(
        scope: LazyListScope,
        key: Any = contentType,
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            Content(modifier)
        }
    }
}
