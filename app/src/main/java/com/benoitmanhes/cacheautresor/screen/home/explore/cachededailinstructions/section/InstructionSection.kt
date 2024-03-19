package com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.benoitmanhes.cacheautresor.BuildConfig
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.cacheautresor.common.composable.section.SectionHeader
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.spacer.SpacerSmall
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.secondaryButton.SecondaryButtonState
import com.benoitmanhes.designsystem.molecule.button.secondaryButton.SecondaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.benoitmanhes.domain.model.CacheInstructions
import com.benoitmanhes.domain.model.InstructionContent
import com.benoitmanhes.domain.uimodel.UIStep

@Composable
fun InstructionSection(
    state: InstructionSectionState,
) {
    Section(
        title = TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_classical),
    ) {
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
                        CTMarkdownText(
                            markdown = instructionContent.value.textSpec(),
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = CTTheme.typography.body,
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
        )
        SpacerSmall()
        CTTextView(
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            text = clue.text,
            style = CTTheme.typography.body,
        )
        SpacerLarge()
    }
    state.code?.let {
        SectionHeader(title = TextSpec.Resources(R.string.cacheDetail_codeSection_title))
        SpacerSmall()
        CTTextView(
            modifier = Modifier.padding(horizontal = CTTheme.spacing.large),
            text = state.code,
            style = CTTheme.typography.body,
        )
        SpacerLarge()
    }
    Row(
        modifier = Modifier
            .padding(horizontal = CTTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
    ) {
        (state.clue as? InstructionSectionState.Clue.Unrevealed)?.let { clue ->
            SecondaryButtonState(
                text = TextSpec.Resources(R.string.cacheDetail_clueButton),
                onClick = clue.onClickClue,
                type = SecondaryButtonType.Outlined,
            ).Composable(
                modifier = Modifier.weight(1f),
            )
        }
        if (BuildConfig.DEBUG) {
            SecondaryButtonState(
                text = TextSpec.Resources(R.string.cacheDetail_reportButton),
                onClick = state.onReport,
                type = SecondaryButtonType.Text,
                color = { CTTheme.color.textCritical },
                leadingIcon = CTTheme.icon.Flag,
            ).Composable(
                modifier = Modifier.weight(1f),
            )
        } else {
            Box(modifier = Modifier.weight(1f))
        }
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
    val status: UIStep.Status,
    val code: TextSpec?,
) {
    val enabled: Boolean = status != UIStep.Status.Lock

    sealed interface Clue {
        data class Unrevealed(val onClickClue: () -> Unit) : Clue
        data class Revealed(val text: TextSpec) : Clue
    }
}
