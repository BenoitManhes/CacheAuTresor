package com.benoitmanhes.cacheautresor.navigation.home

import androidx.lifecycle.ViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val modalBottomSheetManager: ModalBottomSheetManager,
) : ViewModel() {
    private val _navigate = MutableStateFlow<Boolean?>(null)
    val navigate: StateFlow<Boolean?> get() = _navigate.asStateFlow()

    fun showCacheCreationModal() {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = { CTTheme.icon.Create },
                title = TextSpec.Resources(R.string.startCreationModal_title),
                message = TextSpec.Resources(R.string.startCreationModal_message),
                color = CTTheme.composed { color.primary },
                cancelAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_alertDialog_action_no),
                    onClick = {},
                ),
                confirmAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_alertDialog_action_letsGo),
                    onClick = { _navigate.value = true },
                )
            )
        )
    }

    fun consumeNavigation() {
        _navigate.value = null
    }
}
