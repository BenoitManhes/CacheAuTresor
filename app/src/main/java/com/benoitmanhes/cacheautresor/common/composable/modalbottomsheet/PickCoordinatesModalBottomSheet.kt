package com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetOption
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetState
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.modalBottomSheet.CTModalBottomSheetContent
import com.benoitmanhes.designsystem.molecule.textfield.CTOutlinedTextField
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme

data class PickCoordinatesModalBottomSheet(
    val onValidate: (String, () -> Unit) -> Unit,
    val errorText: TextSpec?,
    val initialText: TextSpec?,
    override val onDismiss: () -> Unit = {},
) : ModalBottomSheetState {
    override val option: Set<ModalBottomSheetOption> = emptySet()

    @Composable
    override fun Content(scope: ColumnScope, hide: () -> Unit) {
        CTTheme(CTColorTheme.Cartography) {
            val init = initialText?.string().orEmpty()
            var textValue: String by remember { mutableStateOf(init) }
            var showError by remember(errorText) { mutableStateOf(errorText != null) }

            CTModalBottomSheetContent(
                hide = hide,
                modifier = Modifier
                    .animateContentSize()
                    .imePadding(),
                title = TextSpec.Resources(R.string.modalPickCoordinates_title),
                message = TextSpec.Resources(R.string.modalPickCoordinates_message),
                icon = CTTheme.icon.Globe,
                confirmAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    status = if (textValue.isBlank() || showError) ButtonStatus.DISABLE else ButtonStatus.ENABLE,
                    onClick = {
                        onValidate(textValue, hide)
                    }
                )
            ) {
                CTOutlinedTextField(
                    value = textValue,
                    onValueChange = {
                        textValue = it
                        showError = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = CTTheme.spacing.large),
                    isError = showError,
                    errorText = errorText,
                )
            }
        }
    }
}
