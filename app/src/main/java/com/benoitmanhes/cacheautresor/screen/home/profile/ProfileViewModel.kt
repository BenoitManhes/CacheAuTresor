package com.benoitmanhes.cacheautresor.screen.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.screen.home.profile.section.ExplorerCardState
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.authentication.LogoutUseCase
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getMyExplorerUseCase: GetMyExplorerUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val modalBottomSheetManager: ModalBottomSheetManager,
) : ViewModel() {

    val uiState: StateFlow<ProfileViewModelState?> = getMyExplorerUseCase().map { result ->
        result.data?.let { myExplorer ->
            ProfileViewModelState(
                explorerCard = myExplorer.toExplorerCard(),
                logoutButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.profile_logoutButton),
                    onClick = ::showLogoutModal,
                    type = PrimaryButtonType.TEXT,
                ),
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(AppConstants.ViewModel.defaultStopTimeOut),
        initialValue = null,
    )

    private fun Explorer.toExplorerCard(): ExplorerCardState = ExplorerCardState(
        profileImage = ImageSpec.ResImage(R.drawable.explorer),
        explorerName = name.textSpec(),
        explorationPts = cachesFoundMap.values.sum(),
        cartographyPts = cachesMap.values.sum(),
        numberCacheFounded = cachesFoundMap.count(),
        numberCacheCreated = cachesMap.count(),
    )

    private fun showLogoutModal() {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = { CTTheme.icon.Disconnect },
                title = TextSpec.Resources(R.string.profile_logoutModal_title),
                message = TextSpec.Resources(R.string.profile_logoutModal_message),
                color = CTTheme.composed { color.critical },
                cancelAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_alertDialog_action_no),
                    onClick = {},
                ),
                confirmAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.profile_logoutModal_confirmAction),
                    gradientBackground = CTTheme.composed { gradient.surfaceCritical },
                    onClick = ::logout,
                )
            )
        )
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase().collect()
        }
    }
}
