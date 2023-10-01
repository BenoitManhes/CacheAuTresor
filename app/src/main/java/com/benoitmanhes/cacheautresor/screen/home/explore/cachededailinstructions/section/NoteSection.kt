package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonOption
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.molecule.textfield.ZoneTextField
import com.benoitmanhes.designsystem.molecule.textfield.ZoneTextFieldState
import com.benoitmanhes.designsystem.res.icons.iconpack.Add
import com.benoitmanhes.designsystem.res.icons.iconpack.Compass
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun NoteSection(
    state: NoteSectionState,
) {
    Section(title = TextSpec.Resources(R.string.cacheDetail_noteSection_title)) {
        ZoneTextField(
            state = ZoneTextFieldState(
                initialValue = state.initialNoteValue,
                onValueSaved = state.onNoteSaved,
            ),
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
        )
        SpacerLarge()
        Row(
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
        ) {
            CTPrimaryButton(
                text = TextSpec.Resources(R.string.cacheDetail_addMarkerButton),
                onClick = state.onClickMarker,
                modifier = Modifier.weight(1f),
                type = PrimaryButtonType.COLORED,
                options = setOf(PrimaryButtonOption.LeadingIcon(CTTheme.icon.Add.toIconSpec())),
            )
            CTPrimaryButton(
                text = TextSpec.Resources(R.string.cacheDetail_instrumentsButton),
                onClick = state.onClickMarker,
                modifier = Modifier.weight(1f),
                type = PrimaryButtonType.OUTLINED,
                options = setOf(PrimaryButtonOption.LeadingIcon(CTTheme.icon.Compass.toIconSpec())),
            )
        }
    }
}

object NoteSection {
    private const val contentType: String = "NoteSection"

    fun item(
        scope: LazyListScope,
        state: NoteSectionState,
        key: Any? = contentType,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            NoteSection(state)
        }
    }
}

@Stable
data class NoteSectionState(
    val initialNoteValue: String?,
    val onNoteSaved: (String?) -> Unit,
    val onClickMarker: () -> Unit,
    val onClickInstruments: () -> Unit,
)
