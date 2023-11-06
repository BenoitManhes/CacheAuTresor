package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Event
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.cell.LabelCell
import com.benoitmanhes.cacheautresor.common.composable.cell.LabelCellState
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.res.icons.iconpack.ProfileFilled
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun CartographerSection(
    state: CartographerSectionState,
) {
    Section(title = TextSpec.Resources(R.string.cacheDetail_cartographerSection_title)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CTTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LabelCell(
                state = LabelCellState(
                    leadingIcon = IconSpec.VectorIcon(CTTheme.icon.ProfileFilled),
                    label = state.creatorName,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = CTTheme.spacing.large),
            )
            LabelCell(
                state = LabelCellState(
                    leadingIcon = IconSpec.VectorIcon(Icons.Rounded.Event),
                    label = state.creationDateText,
                    headingText = TextSpec.Resources(R.string.cacheDetail_cartographerSection_creationDate)
                ),
                modifier = Modifier
                    .weight(1f),
            )
        }
    }
}

object CartographerSection {
    private const val contentType: String = "CartographerSection"

    fun item(
        scope: LazyListScope,
        state: CartographerSectionState,
        key: Any? = contentType,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CartographerSection(
                state = state,
            )
        }
    }
}

@Stable
data class CartographerSectionState(
    val creatorName: TextSpec,
    val creationDateText: TextSpec,
)

@Preview
@Composable
private fun PreviewCartographerSection() {
    CTTheme {
        CartographerSection(
            state = CartographerSectionState(
                creatorName = TextSpec.loreumIpsum(2),
                creationDateText = TextSpec.RawString("22 juillet 2021")
            )
        )
    }
}
