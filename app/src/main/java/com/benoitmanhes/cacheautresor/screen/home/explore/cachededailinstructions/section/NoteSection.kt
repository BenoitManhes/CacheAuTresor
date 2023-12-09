package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.BuildConfig
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.secondaryButton.SecondaryButtonState
import com.benoitmanhes.designsystem.molecule.button.secondaryButton.SecondaryButtonType
import com.benoitmanhes.designsystem.res.icons.iconpack.Add
import com.benoitmanhes.designsystem.res.icons.iconpack.Compass
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteSection(
    state: NoteSectionState,
) {
    Section(title = TextSpec.Resources(R.string.cacheDetail_noteSection_title)) {
        Surface(
            modifier = Modifier
                .padding(horizontal = CTTheme.spacing.large)
                .wrapContentHeight(),
            shape = CTTheme.shape.medium,
            border = BorderStroke(CTTheme.stroke.thin, CTTheme.color.placeholder),
            onClick = state.onClickNote,
        ) {
            CTTextView(
                text = state.initialNoteValue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = CTTheme.spacing.medium,
                        horizontal = CTTheme.spacing.large,
                    ),
                minLines = 3,
            )
        }
        SpacerLarge()
        if (BuildConfig.DEBUG) {
            Row(
                modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
                horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
            ) {
                SecondaryButtonState(
                    text = TextSpec.Resources(R.string.cacheDetail_addMarkerButton),
                    onClick = state.onClickMarker,
                    type = SecondaryButtonType.Outlined,
                    leadingIcon = CTTheme.icon.Add.toIconSpec(),
                ).Composable(
                    modifier = Modifier.weight(1f),
                )
                SecondaryButtonState(
                    text = TextSpec.Resources(R.string.cacheDetail_instrumentsButton),
                    onClick = state.onClickMarker,
                    type = SecondaryButtonType.Outlined,
                    leadingIcon = CTTheme.icon.Compass.toIconSpec(),
                ).Composable(
                    modifier = Modifier.weight(1f),
                )
            }
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
    val initialNoteValue: TextSpec,
    val onClickNote: () -> Unit,
    val onClickMarker: () -> Unit,
    val onClickInstruments: () -> Unit,
)
