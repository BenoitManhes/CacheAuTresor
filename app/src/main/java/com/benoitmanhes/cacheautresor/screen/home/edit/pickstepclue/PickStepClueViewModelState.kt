package com.benoitmanhes.cacheautresor.screen.home.edit.pickstepclue

import androidx.compose.runtime.Immutable
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.SwitchRowState
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec

@Immutable
data class PickStepClueViewModelState(
    val clueValue: String? = null,
    val onTextChanged: (String) -> Unit,
    val bottomActionBar: BottomActionBarState,
    val tobBarTitle: TextSpec = TextSpec.RawString(""),
    val switchRowState: SwitchRowState = SwitchRowState(checked = false, onCheckedChange = {}, label = "".textSpec()),
) {
    val clue: String? get() = clueValue.takeIf { switchRowState.checked }
}
