package com.benoitmanhes.designsystem.molecule.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CTInfoCard(
    state: InfoCardState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = CTTheme.spacing.large),
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        color = CTTheme.color.surfacePrimarySoft,
        contentColor = CTTheme.color.textOnSurfacePrimarySoft,
        shape = CTTheme.shape.medium,
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(CTTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
        ) {
            state.icon?.let {
                CTIcon(
                    icon = state.icon,
                    size = Dimens.IconSize.Medium,
                    color = CTTheme.color.textOnSurfacePrimarySoft,
                )
            }

            CTTextView(
                text = state.message,
                modifier = Modifier.weight(1f),
                style = CTTheme.typography.body,
                color = CTTheme.color.textOnSurfacePrimarySoft,
            )

            state.trailingText?.let {
                CTTextView(
                    text = state.trailingText,
                    style = CTTheme.typography.body,
                    color = CTTheme.color.textOnSurfacePrimarySoft,
                )
            }
        }
    }
}

object CTInfoCard {
    private const val contentType: String = "InfoCard"

    fun item(
        scope: LazyListScope,
        state: InfoCardState,
        key: Any? = state.hashCode(),
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CTInfoCard(
                state = state,
                modifier = modifier,
            )
        }
    }
}

data class InfoCardState(
    val icon: IconSpec?,
    val message: TextSpec,
    val trailingText: TextSpec? = null,
)

@Preview
@Composable
private fun PreviewInfoCard() {
    CTTheme {
        CTInfoCard(
            state = InfoCardState(
                icon = IconSpec.VectorIcon(CTTheme.icon.Crown, null),
                message = TextSpec.RawString(LoremIpsum(4).values.joinToString()),
                trailingText = TextSpec.RawString("end"),
            )
        )
    }
}
