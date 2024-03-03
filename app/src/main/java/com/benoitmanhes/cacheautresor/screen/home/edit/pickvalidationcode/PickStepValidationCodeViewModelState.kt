package com.benoitmanhes.cacheautresor.screen.home.edit.pickvalidationcode

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.common.compose.text.TextSpec

@Immutable
data class PickStepValidationCodeViewModelState(
    val topBarTitle: TextSpec = TextSpec.RawString(""),
    val validationCodeValue: String? = null,
    val onTextChanged: (String) -> Unit,
    val bottomActionBar: BottomActionBarState,
)
