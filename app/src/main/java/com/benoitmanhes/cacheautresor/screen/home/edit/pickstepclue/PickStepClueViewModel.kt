package com.benoitmanhes.cacheautresor.screen.home.edit.pickstepclue

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.row.SwitchRowState
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showOnFailure
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.common.kotlin.extensions.nullIfBlank
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.model.DraftCacheStep
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
class PickStepClueViewModel @Inject constructor(
    private val getUIDraftStepDetailUseCase: GetUIDraftStepDetailUseCase,
    private val saveDraftStepUseCase: SaveDraftStepUseCase,
    private val snackbarManager: SnackbarManager,
    private val loadingManager: LoadingManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickStepClue.draftCacheIdArg
    ).orEmpty()
    private val draftStepId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickStepClue.draftStepIdArg
    ).orEmpty()

    private var currentStep: DraftCacheStep? = null

    val uiState: StateFlow<PickStepClueViewModelState> get() = _uiState.asStateFlow()
    val _uiState = MutableStateFlow(
        PickStepClueViewModelState(
            onTextChanged = ::updateClue,
            bottomActionBar = BottomActionBarState(
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::saveClue,
                    status = ButtonStatus.DISABLE,
                ),
            ),
            switchRowState = SwitchRowState(
                checked = false,
                onCheckedChange = ::switchClueAvailability,
                label = TextSpec.Resources(R.string.pickStepClue_switchRow_label),
            )
        )
    )

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    init {
        viewModelScope.launch {
            getUIDraftStepDetailUseCase(draftCacheId = draftCacheId, draftStepId = draftStepId).collect { result ->
                loadingManager.handleFromResult(result)
                snackbarManager.showOnFailure(result)
                result.data?.let { stepDetail ->
                    currentStep = stepDetail.draftStep
                    _uiState.value = uiState.value.copy(
                        clueValue = stepDetail.draftStep.clue,
                        switchRowState = uiState.value.switchRowState.copy(checked = !stepDetail.draftStep.clue.isNullOrBlank()),
                        tobBarTitle = getTopBarTitle(stepDetail.type),
                        bottomActionBar = uiState.value.bottomActionBar.copy(
                            primaryButton = uiState.value.bottomActionBar.primaryButton?.copy(
                                status = getValidButtonStatus(stepDetail.draftStep.clue),
                            )
                        ),
                    )
                }
            }
        }
    }

    private fun updateClue(value: String?) {
        _uiState.value = uiState.value.copy(
            clueValue = value,
            bottomActionBar = uiState.value.bottomActionBar.copy(
                primaryButton = uiState.value.bottomActionBar.primaryButton?.copy(
                    status = getValidButtonStatus(value),
                )
            ),
        )
    }

    private fun saveClue() {
        viewModelScope.launch {
            currentStep?.let { step ->
                saveDraftStepUseCase(
                    draftCacheId = draftCacheId,
                    draftStep = step.copy(clue = uiState.value.clue),
                ).collect { result ->
                    snackbarManager.showOnFailure(result)
                    loadingManager.handleFromResult(result)
                    if (result is CTResult.Success) {
                        _navigateBack.value = true
                    }
                }
            }
        }
    }

    private fun switchClueAvailability(available: Boolean) {
        _uiState.value = uiState.value.copy(
            switchRowState = uiState.value.switchRowState.copy(checked = available),
            bottomActionBar = uiState.value.bottomActionBar.copy(
                primaryButton = uiState.value.bottomActionBar.primaryButton?.copy(
                    status = getValidButtonStatus(uiState.value.clueValue.takeIf { available }),
                ),
            ),
        )
    }

    private fun getValidButtonStatus(clueValue: String?): ButtonStatus =
        if (clueValue.nullIfBlank() == currentStep?.clue?.nullIfBlank()) {
            ButtonStatus.DISABLE
        } else {
            ButtonStatus.ENABLE
        }

    private fun getTopBarTitle(stepType: UIDraftStepDetail.Type): TextSpec = when (stepType) {
        UIDraftStepDetail.Type.Classical -> TextSpec.Resources(R.string.pickStepClue_topBar_title_classical)
        UIDraftStepDetail.Type.MysteryEnigma -> TextSpec.Resources(R.string.pickStepClue_topBar_title_mystery)
        UIDraftStepDetail.Type.Final -> TextSpec.Resources(R.string.pickStepClue_topBar_title_final)
        is UIDraftStepDetail.Type.Piste -> TextSpec.Resources(
            R.string.pickStepClue_topBar_title_piste,
            stepType.index + 1
        )
        is UIDraftStepDetail.Type.Coop -> TextSpec.Resources(
            R.string.pickStepClue_topBar_title_coop,
            stepType.index + 1,
            stepType.crewRef,
        )
    }

    fun consumeNavigation() {
        _navigateBack.value = null
    }
}
