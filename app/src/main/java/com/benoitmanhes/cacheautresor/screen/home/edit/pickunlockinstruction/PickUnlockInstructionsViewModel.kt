package com.benoitmanhes.cacheautresor.screen.home.edit.pickunlockinstruction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.usecase.draftcache.GetDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.SaveDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickUnlockInstructionsViewModel @Inject constructor(
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val getDraftCacheUseCase: GetDraftCacheUseCase,
    private val loadingManager: LoadingManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickUnlockInstructions.arg
    ).orEmpty()

    private var initialValue: String? = null

    val uiState: StateFlow<PickUnlockInstructionsViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        PickUnlockInstructionsViewModelState(
            onTextChanged = ::updateUnlockInstructions,
            bottomActionBar = BottomActionBarState(
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::saveCache,
                    status = ButtonStatus.DISABLE,
                ),
                secondaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_reinitialize),
                    onClick = { updateUnlockInstructions(initialValue) },
                    status = ButtonStatus.DISABLE,
                ),
            )
        )
    )

    init {
        viewModelScope.launch {
            getDraftCacheUseCase.asFlow(draftCacheId).collect { draftCache ->
                initialValue = draftCache?.lockDescription
                updateUnlockInstructions(draftCache?.lockDescription)
            }
        }
    }

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    private fun saveCache() {
        viewModelScope.launch {
            uiState.value.textValue?.takeIf { it.isNotBlank() }?.let { lockInstructions ->
                loadingManager.showLoading()
                getDraftCacheUseCase(draftCacheId)?.also { draftCache ->
                    saveDraftCacheUseCase(
                        draftCache.copy(lockDescription = lockInstructions)
                    )
                    _navigateBack.value = true
                }
                loadingManager.hideLoading()
            }
        }
    }

    private fun updateUnlockInstructions(value: String?) {
        val buttonStatus = if (value == initialValue) ButtonStatus.DISABLE else ButtonStatus.ENABLE
        _uiState.value = uiState.value.copy(
            textValue = value,
            bottomActionBar = uiState.value.bottomActionBar.copy(
                primaryButton = uiState.value.bottomActionBar.primaryButton?.copy(
                    status = buttonStatus,
                ),
                secondaryButton = uiState.value.bottomActionBar.secondaryButton?.copy(
                    status = buttonStatus,
                ),
            ),
        )
    }

    fun consumeNavigation() {
        _navigateBack.value = null
    }
}
