package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.sticker.CTStickerIconState
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctClickable

@Composable
fun StickerRowPicker(
    sticker: CTStickerIconState?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .ctClickable(onClick)
            .padding(
                horizontal = CTTheme.spacing.large,
                vertical = CTTheme.spacing.small,
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        sticker?.Content() ?: CTTextView(
            text = TextSpec.Resources(R.string.common_noValue_placeHolder),
            color = CTTheme.color.textOnBackground,
            style = CTTheme.typography.body,
        )

        SpacerLarge()

        CTIcon(
            icon = CTTheme.icon.Chevron,
            size = Dimens.IconSize.Medium,
            color = CTTheme.color.textOnBackground,
        )
    }
}

@Stable
data class StickerRowPickerState(
    val sticker: CTStickerIconState?,
    val colorTheme: CTColorTheme,
    val onClick: () -> Unit,
) {
    companion object {
        private const val contentType: String = "StickerRowPicker"
    }

    @Composable
    fun Content() {
        CTTheme(colorTheme) {
            StickerRowPicker(
                sticker = sticker,
                onClick = onClick,
            )
        }
    }

    fun lazyItem(
        scope: LazyListScope,
        key: Any,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            Content()
        }
    }
}
