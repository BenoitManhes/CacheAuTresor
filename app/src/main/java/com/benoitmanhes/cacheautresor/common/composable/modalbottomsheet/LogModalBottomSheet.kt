package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

data class LogModalBottomSheet(
    val message: TextSpec,
    val errorMessage: TextSpec,
    val onValidate: (String) -> Unit,
    val isError: Boolean,
    val hideError: () -> Unit,
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
                .padding(CTTheme.spacing.large)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        ) {
            CTTextView(text = message)
            CTOutlinedTextField(
                value = textValue,
                onValueChange = {
                    textValue = it
                    hideError()
                },
                modifier = Modifier.fillMaxWidth(),
                isError = isError,
                errorText = errorMessage,
            )

            CTPrimaryButton(
                text = TextSpec.Resources(R.string.common_validate),
                onClick = {
                    onValidate(textValue)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding(),
                status = if (textValue.isBlank()) ButtonStatus.DISABLE else ButtonStatus.ENABLE,
            )
        }
    }
}
