package com.benoitmanhes.cacheautresor.screen.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.home.profile.section.ExplorerCardState
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
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
) : ViewModel() {

    val uiState: StateFlow<ProfileViewModelState?> = getMyExplorerUseCase().map { result ->
        result.data?.let { myExplorer ->
            ProfileViewModelState(
                explorerCard = myExplorer.toExplorerCard(),
                logoutButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.profile_logoutButton),
                    onClick = ::logout,
                    type = PrimaryButtonType.TEXT,
                ),
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(StopTimeOut),
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

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase().collect()
        }
    }

    companion object {
        private const val StopTimeOut: Long = 5000
    }
}
