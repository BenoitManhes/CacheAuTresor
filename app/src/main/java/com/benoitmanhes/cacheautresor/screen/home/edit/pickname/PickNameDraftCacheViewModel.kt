package com.benoitmanhes.cacheautresor.screen.home.edit.pickname

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.usecase.draftcache.GetDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.SaveDraftCacheUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickNameDraftCacheViewModel @Inject constructor(
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val getDraftCacheUseCase: GetDraftCacheUseCase,
    private val loadingManager: LoadingManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickNameDraftCache.arg
    ).orEmpty()

    val uiState: StateFlow<PickNameDraftCacheViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        PickNameDraftCacheViewModelState(
            onTextChanged = ::updateName,
            bottomActionBar = BottomActionBarState(
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::saveNote,
                )
            )
        )
    )

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    init {
        viewModelScope.launch {
            getDraftCacheUseCase(draftCacheId)?.let { draftCache ->
                updateName(draftCache.title)
            }
        }
    }

    fun consumeNavigation() {
        _navigateBack.value = null
    }

    private fun saveNote() {
        viewModelScope.launch {
            loadingManager.showLoading()
            getDraftCacheUseCase(draftCacheId)?.also { draftCache ->
                saveDraftCacheUseCase(
                    draftCache.copy(title = uiState.value.nameValue?.takeIf { it.isNotBlank() })
                )
                _navigateBack.value = true
            }
            loadingManager.hideLoading()
        }
    }

    private fun updateName(value: String?) {
        _uiState.value = uiState.value.copy(
            nameValue = value?.take(DomainConstants.EditCache.maxCacheNameLenght),
        )
    }
}
