package com.benoitmanhes.cacheautresor.navigation.home

import androidx.lifecycle.ViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.Create
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val modalBottomSheetManager: ModalBottomSheetManager,
) : ViewModel() {
    fun showCacheCreationModal() {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = CTIconPack.Create.toIconSpec(),
                title = TextSpec.Resources(R.string.startCreationModal_title),
                message = TextSpec.Resources(R.string.startCreationModal_message),
                color = CTTheme.composed { color.primary },
                cancelAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_alertDialog_action_no),
                    onClick = {},
                ),
                confirmAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_alertDialog_action_letsGo),
                    onClick = {},
                )
            )
        )
    }
}
