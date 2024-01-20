package com.benoitmanhes.cacheautresor.screen.home.create.mycaches.available

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.card.CacheCardState
import com.benoitmanhes.cacheautresor.common.composable.card.CacheCardTrailing
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.toDifficultyText
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesViewModelState
import com.benoitmanhes.cacheautresor.screen.home.profile.ProfileViewModel
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.utils.extensions.getCacheColorTheme
import com.benoitmanhes.designsystem.utils.extensions.getColorTheme
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.usecase.cache.GetAllMyCacheUseCase
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyCachesAvailableViewModel @Inject constructor(
    getAllMyCacheUseCase: GetAllMyCacheUseCase,
) : ViewModel() {

    val uiState: StateFlow<MyCachesViewModelState> =
        getAllMyCacheUseCase().map { result ->
            when (result) {
                is CTResult.Loading -> MyCachesViewModelState.Init

                is CTResult.Failure -> {
                    MyCachesViewModelState.Empty(
                        message = TextSpec.Resources(R.string.createAvailable_empty)
                    )
                }

                is CTResult.Success -> {
                    if (result.successData.isEmpty()) {
                        MyCachesViewModelState.Empty(
                            message = TextSpec.Resources(R.string.createAvailable_empty)
                        )
                    } else {
                        MyCachesViewModelState.Data(
                            headerText = TextSpec.Resources(R.string.createAvailable_header),
                            cacheCards = result.successData.map { cacheCard(it.first, it.second) },
                        )
                    }
                }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(AppConstants.ViewModel.defaultStopTimeOut),
                initialValue = MyCachesViewModelState.Init,
            )

    private fun cacheCard(cache: Cache, points: Int): CacheCardState = CacheCardState(
        itemId = cache.cacheId,
        cacheColorTheme = cache.getCacheColorTheme().first,
        name = cache.title.textSpec(),
        icon = cache.getIcon(),
        typeText = cache.getTypeText(),
        cacheIdText = cache.cacheId.textSpec(),
        difficultyText = cache.difficulty.toString().textSpec(),
        groundText = cache.ground.toString().textSpec(),
        sizeText = cache.size.toSizeText(),
        trailingContent = CacheCardTrailing.Point(points.toString().textSpec()),
        onClick = {
            // TODO: navigate to EditCache screen
        },
    )
}