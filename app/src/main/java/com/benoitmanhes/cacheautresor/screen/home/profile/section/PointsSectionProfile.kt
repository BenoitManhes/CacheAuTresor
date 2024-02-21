package com.benoitmanhes.cacheautresor.screen.home.profile.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.SectionHeader
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIconSlot
import com.benoitmanhes.designsystem.atoms.CTVerticalDivider
import com.benoitmanhes.designsystem.atoms.text.CTTextMultiSize
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun PointsSectionProfile(
    header: TextSpec,
    icon: IconSpec,
    points: Int,
    cacheNumber: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
    ) {
        SectionHeader(title = header, horizontalPadding = false)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        ) {
            CTIconSlot(
                icon = icon,
                size = Dimens.IconSlotSize.Medium,
                backgroundColor = CTTheme.color.surfacePrimarySoft,
                contentColor = CTTheme.color.textOnSurfacePrimarySoft,
            )

            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
            ) {
                CTTextMultiSize(
                    firstText = points.toString().textSpec(),
                    secondText = TextSpec.Resources(R.string.common_pointsUnit)
                )
                CTVerticalDivider(modifier = Modifier.fillMaxHeight())
                CTTextMultiSize(
                    firstText = cacheNumber.toString().textSpec(),
                    secondText = TextSpec.PluralResources(R.plurals.common_cache, count = cacheNumber),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPointsSectionProfile() {
    CTTheme {
        PointsSectionProfile(
            header = "Title".textSpec(),
            icon = CTTheme.icon.Crown,
            points = 180,
            cacheNumber = 36,
        )
    }
}
