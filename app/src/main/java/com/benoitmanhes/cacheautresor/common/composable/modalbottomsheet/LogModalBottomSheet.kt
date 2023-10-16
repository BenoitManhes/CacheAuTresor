package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.designsystem.atoms.spacer.SpacerLarge
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

data class LogModalBottomSheet(
    val validateCode: (String) -> Unit,
    override val onDismiss: () -> Unit = {},
) : ModalBottomSheetState {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content(
        scope: ColumnScope,
        hide: () -> Unit,
    ): Unit = with(scope) {
        var textValue: String by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .heightIn(min = Dimens.ModalBottomSheet.minHeight)
                .padding(CTTheme.spacing.large),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            CTOutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                modifier = Modifier.fillMaxWidth(),
            )

            CTPrimaryButton(
                text = TextSpec.Resources(R.string.common_validate),
                onClick = {
                    validateCode(textValue)
                    hide()
                },
                modifier = Modifier.fillMaxWidth().imePadding(),
                status = if (textValue.isBlank()) ButtonStatus.DISABLE else ButtonStatus.ENABLE,
            )
        }
    }
}