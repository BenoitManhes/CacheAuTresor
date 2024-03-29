package com.benoitmanhes.cacheautresor.screen.home.create.mycaches.creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.card.CacheCardState
import com.benoitmanhes.cacheautresor.common.composable.card.CacheCardTrailing
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.orPlaceHolder
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.screen.home.create.mycaches.MyCachesViewModelState
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.draftcache.GetAllMyDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyCachesDraftViewModel @Inject constructor(
    getAllMyDraftCacheUseCase: GetAllMyDraftCacheUseCase,
) : ViewModel() {
    val uiState: StateFlow<MyCachesViewModelState> =
        getAllMyDraftCacheUseCase().map { result ->
            when (result) {
                is CTResult.Loading -> MyCachesViewModelState.Init

                is CTResult.Failure -> {
                    MyCachesViewModelState.Empty(
                        message = TextSpec.Resources(R.string.myCachesDraft_empty)
                    )
                }

                is CTResult.Success -> {
                    if (result.successData.isEmpty()) {
                        MyCachesViewModelState.Empty(
                            message = TextSpec.Resources(R.string.myCachesDraft_empty)
                        )
                    } else {
                        MyCachesViewModelState.Data(
                            headerText = TextSpec.Resources(R.string.myCachesDraft_header),
                            cacheCards = result.successData.map(::cacheCard),
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

    private val _navigation = MutableStateFlow<MyCachesDraftNavigation?>(null)
    val navigation: StateFlow<MyCachesDraftNavigation?> get() = _navigation.asStateFlow()

    private fun cacheCard(draftCache: DraftCache): CacheCardState = CacheCardState(
        itemId = draftCache.draftCacheId,
        cacheColorTheme = CTColorTheme.Cartography,
        name = draftCache.title?.textSpec().orPlaceHolder(),
        icon = draftCache.type?.toCacheType()?.getIcon() ?: { CTTheme.icon.Logo },
        typeText = draftCache.type?.toCacheType()?.getTypeText().orPlaceHolder(),
        cacheIdText = null,
        difficultyText = draftCache.difficulty?.toString()?.textSpec().orPlaceHolder(),
        groundText = draftCache.ground?.toString()?.textSpec().orPlaceHolder(),
        sizeText = draftCache.size?.toSizeText().orPlaceHolder(),
        trailingContent = CacheCardTrailing.Progress(draftCache.progress),
        onClick = {
            _navigation.value = MyCachesDraftNavigation.EditDraftCache(draftCache.draftCacheId)
        },
    )

    fun consumeNavigation() {
        _navigation.value = null
    }
}

sealed interface MyCachesDraftNavigation {
    @JvmInline
    value class EditDraftCache(val draftCacheId: String) : MyCachesDraftNavigation
}
