package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.cacheautresor.common.composable.section.SectionHeader
import com.benoitmanhes.cacheautresor.common.extensions.textSpec
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonOption
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.res.icons.iconpack.Flag
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec
import com.benoitmanhes.domain.model.CacheInstructions
import com.benoitmanhes.domain.model.InstructionContent

@Composable
fun InstructionSection(
    state: InstructionSectionState,
) {
    Section(title = state.title) {
        Column(
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.cacheInstructions.forEach { instructionContent ->
                when (instructionContent) {
                    is InstructionContent.Image -> {
                        CTImage(
                            image = ImageSpec.UrlImage(instructionContent.imageUrl),
                            modifier = Modifier.heightIn(max = AppDimens.CacheDetail.instructionImageMaxHeight)
                        )
                    }

                    is InstructionContent.Text -> {
                        CTTextView(
                            text = instructionContent.value.textSpec(),
                            modifier = Modifier
                                .fillMaxWidth(),
                            maxLine = Int.MAX_VALUE,
                        )
                    }
                }
            }
        }
    }
    SpacerLarge()
    (state.clue as? InstructionSectionState.Clue.Revealed)?.let { clue ->
        SectionHeader(
            title = TextSpec.Resources(R.string.cacheDetail_clueSection_title),
            horizontalPadding = false,
        )
        SpacerLarge()
    }
    Row(
        modifier = Modifier
            .padding(horizontal = CTTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
    ) {
        (state.clue as? InstructionSectionState.Clue.Unrevealed)?.let { clue ->
            CTPrimaryButton(
                text = TextSpec.Resources(R.string.cacheDetail_clueButton),
                onClick = clue.onClickClue,
                modifier = Modifier.weight(1f),
                type = PrimaryButtonType.OUTLINED,
            )
        }
        CTPrimaryButton(
            text = TextSpec.Resources(R.string.cacheDetail_reportButton),
            onClick = state.onReport,
            modifier = Modifier.weight(1f),
            type = PrimaryButtonType.TEXT,
            color = CTTheme.color.critical,
            options = setOf(
                PrimaryButtonOption.LeadingIcon(CTTheme.icon.Flag.toIconSpec()),
            )
        )
    }
}

object InstructionSection {
    private const val contentType: String = "InstructionSection"

    fun item(
        scope: LazyListScope,
        state: InstructionSectionState,
        key: Any? = contentType,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            InstructionSection(state)
        }
    }
}

@Stable
data class InstructionSectionState(
    val title: TextSpec,
    val cacheInstructions: CacheInstructions,
    val clue: Clue?,
    val onReport: () -> Unit,
) {
    sealed interface Clue {
        data class Unrevealed(val onClickClue: () -> Unit) : Clue
        data class Revealed(val text: TextSpec) : Clue
    }
}
