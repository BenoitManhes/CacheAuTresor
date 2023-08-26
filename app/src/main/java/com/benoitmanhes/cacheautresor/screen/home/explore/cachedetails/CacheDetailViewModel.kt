package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeaderState
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonState
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.Logo
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.CTTheme.icon
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.usecase.cache.GetSelectedUICacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CacheDetailViewModel @Inject constructor(
    getSelectedUICacheUseCase: GetSelectedUICacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(ExploreDestination.CacheDetails.cacheDetailsArgument).orEmpty()

    val uiState: StateFlow<CacheDetailsViewModelState> = getSelectedUICacheUseCase(cacheId)
        .map { result ->
            result.mapToUIState()
        }
        .stateIn(
            viewModelScope,
            AppConstants.Flow.DefaultSharingStarted,
            CacheDetailsViewModelState.Initialize,
        )

    private fun CTResult<UICacheDetails>.mapToUIState(): CacheDetailsViewModelState = when (this) {
        is CTResult.Success -> CacheDetailsViewModelState.Data(
            headerState = CacheDetailHeaderState(
                title = TextSpec.RawString(successData.cache.title),
                subTitle = TextSpec.RawString(successData.cache.cacheId),
            ),
            uiMarkers = listOf(),
            bottomBarState = null,
            fabButtonState = FabButtonState(
                icon = IconSpec.VectorIcon(CTIconPack.Logo, null),
                text = TextSpec.RawString("Fab"),
                onClick = {},
            )
        )

        is CTResult.Loading -> CacheDetailsViewModelState.Initialize
        is CTResult.Failure -> CacheDetailsViewModelState.Empty(TextSpec.RawString(error?.message))
    }
}
