package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.SectionHeader
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonOption
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed

@Stable
data class EditDraftStepInstructionSection(
    val instructions: TextSpec,
    val editInstructions: () -> Unit,
) {
    fun sectionItems(
        scope: LazyListScope,
        key: Any = contentType,
    ) {
        with(scope) {
            SpacerLarge.item(this)
            SectionHeader.item(this, title = TextSpec.Resources(R.string.stepEditor_instruction_header))
            item(key = key, contentType = contentType) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = CTTheme.spacing.large),
                    verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
                ) {
                    CTMarkdownText(markdown = instructions)
                    CTPrimaryButton(
                        text = TextSpec.Resources(R.string.stepEditor_instruction_editButton),
                        onClick = editInstructions,
                        modifier = Modifier
                            .fillMaxWidth(),
                        type = PrimaryButtonType.OUTLINED,
                        options = setOf(
                            PrimaryButtonOption.LeadingIcon(CTTheme.composed { icon.Edit }),
                        ),
                    )
                }
            }
        }
    }

    companion object {
        private const val contentType: String = "EditDraftStepInstructionSection"
    }
}
