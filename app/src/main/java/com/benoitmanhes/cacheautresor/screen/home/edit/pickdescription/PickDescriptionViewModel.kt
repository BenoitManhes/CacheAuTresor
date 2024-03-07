package com.benoitmanhes.cacheautresor.screen.home.edit.pickdescription

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
class PickDescriptionViewModel @Inject constructor(
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val getDraftCacheUseCase: GetDraftCacheUseCase,
    private val loadingManager: LoadingManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(EditCacheDestination.PickDescription.arg).orEmpty()

    private var initialDescription: String? = null

    val uiState: StateFlow<PickDescriptionViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        PickDescriptionViewModelState(
            onTextChanged = ::updateDescription,
            bottomActionBar = BottomActionBarState(
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::saveCache,
                    status = ButtonStatus.DISABLE,
                ),
                secondaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_reinitialize),
                    onClick = { updateDescription(initialDescription) },
                    status = ButtonStatus.DISABLE,
                ),
            )
        )
    )

    init {
        viewModelScope.launch {
            getDraftCacheUseCase.asFlow(draftCacheId).collect { draftCache ->
                initialDescription = draftCache?.description
                updateDescription(draftCache?.description)
            }
        }
    }

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    private fun saveCache() {
        viewModelScope.launch {
            uiState.value.descriptionValue?.takeIf { it.isNotBlank() }?.let { description ->
                loadingManager.showLoading()
                getDraftCacheUseCase(draftCacheId)?.also { draftCache ->
                    saveDraftCacheUseCase(
                        draftCache.copy(description = description)
                    )
                    _navigateBack.value = true
                }
                loadingManager.hideLoading()
            }
        }
    }

    private fun updateDescription(value: String?) {
        val buttonStatus = if (value == initialDescription) ButtonStatus.DISABLE else ButtonStatus.ENABLE
        _uiState.value = uiState.value.copy(
            descriptionValue = value,
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
