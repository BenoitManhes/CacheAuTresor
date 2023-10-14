package com.benoitmanhes.cacheautresor.screen.home.explore.editnote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.usecase.note.GetCacheNoteUseCase
import com.benoitmanhes.domain.usecase.note.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val loadingManager: LoadingManager,
    private val saveNoteUseCase: SaveNoteUseCase,
    getCacheNoteUseCase: GetCacheNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(ExploreDestination.EditNote.editNoteArgument).orEmpty()

    val uiState: StateFlow<EditNoteViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        EditNoteViewModelState(
            onSaved = ::saveNote,
            onTextChanged = ::updateTextNote,
        )
    )

    private val _navigation = MutableStateFlow<EditNoteNavigation?>(null)
    val navigation: StateFlow<EditNoteNavigation?> get() = _navigation.asStateFlow()

    init {
        viewModelScope.launch {
            loadingManager.showLoading()
            val result = getCacheNoteUseCase(cacheId)
            loadingManager.hideLoading()
            when (result) {
                is CTSuspendResult.Success -> {
                    updateTextNote(result.successData)
                }

                is CTSuspendResult.Failure -> {
                }
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            loadingManager.showLoading()
            saveNoteUseCase(cacheId = cacheId, note = uiState.value.note)
            loadingManager.hideLoading()
            _navigation.value = EditNoteNavigation.Back
        }
    }

    private fun updateTextNote(value: String?) {
        _uiState.value = uiState.value.copy(
            note = value,
        )
    }

    fun consumeNavigation() {
        _navigation.value = null
    }
}
