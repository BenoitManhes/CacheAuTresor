package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.cell.LabelCell
import com.benoitmanhes.cacheautresor.common.composable.cell.LabelCellState
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.sticker.CTSticker
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CacheTypeSection(
    state: CacheTypeSectionState,
    modifier: Modifier = Modifier,
) {
    Section(title = TextSpec.Resources(R.string.cacheDetail_cacheTypeSection_title)) {
        Row(
            modifier = modifier.padding(horizontal = CTTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        ) {
            // Type
            LabelCell(
                state = LabelCellState(
                    leadingIcon = state.typeIcon,
                    label = state.typeText,
                )
            )

            // Sticker
            state.stickerLabel?.let {
                CTSticker(label = state.stickerLabel)
            }
        }
    }
}

object CacheTypeSection {
    private const val contentType: String = "TypeSection"

    fun item(
        scope: LazyListScope,
        state: CacheTypeSectionState,
        key: Any? = state.typeText.hashCode(),
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CacheTypeSection(
                state = state,
                modifier = modifier,
            )
        }
    }
}

@Stable
data class CacheTypeSectionState(
    val typeIcon: IconSpec,
    val typeText: TextSpec,
    val stickerLabel: TextSpec?,
)

@Preview
@Composable
private fun PreviewCacheTypeSection() {
    CTTheme {
        CacheTypeSection(
            state = CacheTypeSectionState(
                typeIcon = IconSpec.VectorIcon(CTTheme.icon.Parchment, null),
                typeText = TextSpec.loreumIpsum(2),
                stickerLabel = TextSpec.RawString("en cours...")
            )
        )
    }
}