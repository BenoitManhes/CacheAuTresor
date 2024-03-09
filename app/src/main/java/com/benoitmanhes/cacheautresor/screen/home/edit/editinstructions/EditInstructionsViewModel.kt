package com.benoitmanhes.cacheautresor.screen.home.edit.editinstructions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showOnFailure
import com.benoitmanhes.common.kotlin.extensions.nullIfBlank
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.draftcache.GetUIDraftStepDetailUseCase
import com.benoitmanhes.domain.usecase.draftcache.SaveDraftStepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditInstructionsViewModel @Inject constructor(
    private val saveDraftStepUseCase: SaveDraftStepUseCase,
    private val snackbarManager: SnackbarManager,
    private val loadingManager: LoadingManager,
    getUIDraftStepDetailUseCase: GetUIDraftStepDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.EditInstructions.draftCacheIdArg
    ).orEmpty()
    private val draftStepId: String = savedStateHandle.get<String>(
        EditCacheDestination.EditInstructions.draftStepIdArg
    ).orEmpty()

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    val uiState: StateFlow<EditInstructionsViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        EditInstructionsViewModelState(
            bottomBar = BottomActionBarState(
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_save),
                    onClick = ::saveInstructions,
                    status = ButtonStatus.DISABLE,
                ),
                secondaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_reinitialize),
                    onClick = ::resetInstructions,
                    status = ButtonStatus.DISABLE,
                ),
            )
        )
    )

    init {
        viewModelScope.launch {
            getUIDraftStepDetailUseCase(draftCacheId = draftCacheId, draftStepId = draftStepId).collect { result ->
                loadingManager.handleFromResult(result)
                snackbarManager.showOnFailure(result)
                result.data?.draftStep?.let { step ->
                    _uiState.value = _uiState.value.copy(
                        rawText = step.instruction.orEmpty(),
                        formattedText = step.instruction.orEmpty().textSpec(),
                        draftStep = step,
                        headerText = result.data?.type?.let(::getHeaderText),
                    )
                }
            }
        }
    }

    fun updateInstructions(value: String) {
        val hasChange = value != uiState.value.draftStep?.instruction && value.isNotBlank()
        val buttonStatus = if (hasChange) ButtonStatus.ENABLE else ButtonStatus.DISABLE
        _uiState.value = uiState.value.copy(
            rawText = value,
            formattedText = value.textSpec(),
            bottomBar = uiState.value.bottomBar.copy(
                primaryButton = uiState.value.bottomBar.primaryButton?.copy(
                    status = buttonStatus,
                ),
                secondaryButton = uiState.value.bottomBar.secondaryButton?.copy(
                    status = buttonStatus,
                )
            ),
        )
    }

    private fun saveInstructions() {
        viewModelScope.launch {
            uiState.value.draftStep?.let { draftStep ->
                saveDraftStepUseCase(
                    draftCacheId = draftCacheId,
                    draftStep = draftStep.copy(
                        instruction = uiState.value.rawText.nullIfBlank(),
                    ),
                ).collect { saveResult ->
                    loadingManager.handleFromResult(saveResult)
                    snackbarManager.showOnFailure(saveResult)
                    if (saveResult is CTResult.Success) {
                        _navigateBack.value = true
                    }
                }
            }
        }
    }

    private fun resetInstructions() {
        updateInstructions(uiState.value.draftStep?.instruction.orEmpty())
    }

    fun consumeNavigation() {
        _navigateBack.value = null
    }

    private fun getHeaderText(stepType: UIDraftStepDetail.Type): TextSpec = when (stepType) {
        UIDraftStepDetail.Type.Classical -> TextSpec.Resources(R.string.editInstructions_header_classical)
        UIDraftStepDetail.Type.MysteryEnigma -> TextSpec.Resources(R.string.editInstructions_header_mystery)
        UIDraftStepDetail.Type.Final -> TextSpec.Resources(R.string.editInstructions_header_final)
        is UIDraftStepDetail.Type.Piste -> TextSpec.Resources(
            R.string.editInstructions_header_piste,
            stepType.index + 1
        )
        is UIDraftStepDetail.Type.Coop -> TextSpec.Resources(
            R.string.editInstructions_header_piste,
            stepType.index + 1,
            stepType.crewRef,
        )
    }
}
