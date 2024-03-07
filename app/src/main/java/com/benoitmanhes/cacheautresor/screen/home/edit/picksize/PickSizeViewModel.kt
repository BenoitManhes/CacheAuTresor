package com.benoitmanhes.cacheautresor.screen.home.edit.picksize

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.extensions.getSizeIcon
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.card.CTSelectionCardState
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.draftcache.GetDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.SaveDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickSizeViewModel @Inject constructor(
    private val getDraftCacheUseCase: GetDraftCacheUseCase,
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val snackbarManager: SnackbarManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(EditCacheDestination.PickSize.arg).orEmpty()

    val uiState: StateFlow<PickSizeViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        PickSizeViewModelState(
            sizeSelection = sizesMap,
            bottomActionBar = BottomActionBarState(
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::validate,
                    status = ButtonStatus.DISABLE,
                ),
                secondaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_reinitialize),
                    onClick = { currentDraftCache?.size?.let(::updateSize) },
                    status = ButtonStatus.DISABLE,
                ),
            ),
        )
    )

    private val _navigate = MutableStateFlow<Boolean?>(null)
    val navigate: StateFlow<Boolean?> get() = _navigate.asStateFlow()

    private var currentDraftCache: DraftCache? = null

    init {
        viewModelScope.launch {
            getDraftCacheUseCase.asFlow(draftCacheId).collect {
                currentDraftCache = it
                it?.size?.let(::updateSize)
            }
        }
    }

    fun consumeNavigation() {
        _navigate.value = null
    }

    private fun updateSize(newCacheSize: CacheSize) {
        _uiState.value = uiState.value.copy(
            sizeSelection = uiState.value.sizeSelection.mapValues { (cacheSize, card) ->
                card.copy(
                    isSelected = cacheSize == newCacheSize,
                )
            },
            bottomActionBar = uiState.value.bottomActionBar?.copy(
                primaryButton = uiState.value.bottomActionBar?.primaryButton?.copy(
                    status = if (newCacheSize != currentDraftCache?.size) ButtonStatus.ENABLE else ButtonStatus.DISABLE,
                ),
                secondaryButton = uiState.value.bottomActionBar?.secondaryButton?.copy(
                    status = if (newCacheSize != currentDraftCache?.size) ButtonStatus.ENABLE else ButtonStatus.DISABLE,
                ),
            ),
        )
    }

    private fun validate() {
        viewModelScope.launch {
            uiState.value.sizeSelected?.let { newSize ->
                currentDraftCache?.copy(
                    size = newSize,
                )?.let { newDraftCache ->
                    val result = saveDraftCacheUseCase(newDraftCache)
                    when (result) {
                        is CTSuspendResult.Success -> {
                            _navigate.value = true
                        }

                        is CTSuspendResult.Failure -> {
                            snackbarManager.showError(result.error)
                        }
                    }
                }
            }
        }
    }

    private val sizesMap: Map<CacheSize, CTSelectionCardState>
        get() = CacheSize.entries.associateWith { size ->
            CTSelectionCardState(
                icon = size.getSizeIcon(),
                title = size.getCardLabel(),
                description = size.getCardMessage(),
                isSelected = false,
                onClick = { updateSize(size) },
            )
        }

    private fun CacheSize.getCardLabel(): TextSpec = when (this) {
        CacheSize.Micro -> R.string.pickSize_micro_title
        CacheSize.Small -> R.string.pickSize_small_title
        CacheSize.Regular -> R.string.pickSize_regular_title
        CacheSize.Big -> R.string.pickSize_large_title
        CacheSize.Undefined -> R.string.pickSize_unknown_title
    }.let(TextSpec::Resources)

    private fun CacheSize.getCardMessage(): TextSpec = when (this) {
        CacheSize.Micro -> R.string.pickSize_micro_message
        CacheSize.Small -> R.string.pickSize_small_message
        CacheSize.Regular -> R.string.pickSize_regular_message
        CacheSize.Big -> R.string.pickSize_large_message
        CacheSize.Undefined -> R.string.pickSize_unknown_message
    }.let(TextSpec::Resources)
}
