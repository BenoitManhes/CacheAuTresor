package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.CommonModalAction
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.cacheautresor.common.extensions.format
import com.benoitmanhes.cacheautresor.common.extensions.orPlaceHolder
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep.section.EditDraftStepInstructionSection
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.cacheautresor.screen.snackbar.showOnFailure
import com.benoitmanhes.common.kotlin.extensions.nullIfBlank
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.draftcache.DeleteDraftStepUseCase
import com.benoitmanhes.domain.usecase.draftcache.GetUIDraftStepDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditDraftStepViewModel @Inject constructor(
    private val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
    private val deleteDraftStepUseCase: DeleteDraftStepUseCase,
    private val modalBottomSheetManager: ModalBottomSheetManager,
    getUIDraftStepDetailUseCase: GetUIDraftStepDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val draftCacheId = savedStateHandle.get<String>(
        EditCacheDestination.EditDraftStep.draftCacheIdArg
    ).orEmpty()
    private val draftStepId = savedStateHandle.get<String>(EditCacheDestination.EditDraftStep.draftStepIdArg).orEmpty()

    val uiState: StateFlow<EditDraftStepViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        EditDraftStepViewModelState(
            topBarTitle = TextSpec.Resources(R.string.common_noValue_placeHolder),
        )
    )

    private val _navigate = MutableStateFlow<EditDraftStepNavigation?>(null)
    val navigate: StateFlow<EditDraftStepNavigation?> get() = _navigate.asStateFlow()

    private var deleteAction: Boolean = false

    init {
        viewModelScope.launch {
            getUIDraftStepDetailUseCase(
                draftCacheId = draftCacheId,
                draftStepId = draftStepId
            ).collectLatest { result ->
                if (deleteAction) return@collectLatest

                loadingManager.handleFromResult(result)
                snackbarManager.showError((result as? CTResult.Failure)?.error)

                result.data?.let { uiDraftStepDetail ->
                    _uiState.value = EditDraftStepViewModelState(
                        topBarTitle = getStepName(uiDraftStepDetail.type),
                        bottomBar = getBottomActionBar(uiDraftStepDetail.draftStep, uiDraftStepDetail.type),
                        stepCoordinates = MapRowPickerState(
                            uiMarker = getStepMarker(uiDraftStepDetail),
                            text = uiDraftStepDetail.draftStep.coordinates?.format(Coordinates.Format.DM).orPlaceHolder(),
                            key = "step.coordinates",
                            enabled = coordinatesEnabled(uiDraftStepDetail.type),
                            onClick = {
                                _navigate.value = EditDraftStepNavigation.PickStepCoordinates(draftCacheId, draftStepId)
                            },
                        ),
                        instructions = EditDraftStepInstructionSection(
                            instructions = uiDraftStepDetail.draftStep.instruction?.textSpec().orPlaceHolder(),
                            editInstructions = {
                                _navigate.value = EditDraftStepNavigation.EditInstructions(draftCacheId, draftStepId)
                            },
                        ),
                        clue = TextRowPickerState(
                            text = uiDraftStepDetail.draftStep.clue.nullIfBlank()?.textSpec()
                                ?: TextSpec.Resources(R.string.stepEditor_clue_placeholder),
                            onClick = {
                                _navigate.value = EditDraftStepNavigation.EditClue(draftCacheId, draftStepId)
                            },
                        ),
                        validationCode = TextRowPickerState(
                            text = uiDraftStepDetail.draftStep.validationCode?.textSpec().orPlaceHolder(),
                            onClick = {
                                _navigate.value = EditDraftStepNavigation.EditValidationCode(draftCacheId, draftStepId)
                            },
                        ),
                        onClickDelete = getDeleteMessage(uiDraftStepDetail.type)?.let { message ->
                            {
                                showDeleteModal(message)
                            }
                        },
                    )
                }
            }
        }
    }

    private fun showDeleteModal(message: TextSpec) {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = CTTheme.composed { icon.RemoveRoad },
                title = TextSpec.Resources(R.string.modalDeleteStep_title),
                message = message,
                color = CTTheme.composed { color.critical },
                cancelAction = CommonModalAction.finallyNo(),
                confirmAction = CommonModalAction.delete(::deleteStep),
            ),
        )
    }

    private fun deleteStep() {
        viewModelScope.launch {
            deleteAction = true
            deleteDraftStepUseCase(draftCacheId = draftCacheId, draftStepId = draftStepId).collect { result ->
                loadingManager.handleFromResult(result)
                snackbarManager.showOnFailure(result)
                when (result) {
                    is CTResult.Success -> _navigate.value = EditDraftStepNavigation.Back
                    is CTResult.Failure -> deleteAction = false
                    is CTResult.Loading -> {} // nothing
                }
            }
        }
    }

    private fun coordinatesEnabled(stepType: UIDraftStepDetail.Type): Boolean = when (stepType) {
        UIDraftStepDetail.Type.Classical,
        UIDraftStepDetail.Type.MysteryEnigma,
        -> false

        UIDraftStepDetail.Type.Final,
        is UIDraftStepDetail.Type.Coop,
        is UIDraftStepDetail.Type.Piste,
        -> true
    }

    private fun getDeleteMessage(stepType: UIDraftStepDetail.Type): TextSpec? = when (stepType) {
        UIDraftStepDetail.Type.Classical,
        UIDraftStepDetail.Type.MysteryEnigma,
        UIDraftStepDetail.Type.Final,
        -> null

        is UIDraftStepDetail.Type.Coop -> TextSpec.Resources(
            R.string.modalDeleteStep_message_coop,
            stepType.index + 1,
            stepType.crewRef,
        )

        is UIDraftStepDetail.Type.Piste -> TextSpec.Resources(
            R.string.modalDeleteStep_message_piste,
            stepType.index + 1,
        )
    }

    private fun getBottomActionBar(draftStep: DraftCacheStep, type: UIDraftStepDetail.Type): BottomActionBarState? = when {
        coordinatesEnabled(type) && draftStep.coordinates == null -> BottomActionBarState(
            title = TextSpec.Resources(R.string.stepEditor_bottomActionBar_title_incomplete),
            message = TextSpec.Resources(R.string.stepEditor_bottomActionBar_message_coordinates),
            primaryButton = PrimaryButtonState(
                text = TextSpec.Resources(R.string.stepEditor_bottomActionBar_button_coordinates),
                onClick = {
                    _navigate.value = EditDraftStepNavigation.PickStepCoordinates(draftCacheId, draftStepId)
                }
            )
        )

        draftStep.instruction == null -> BottomActionBarState(
            title = TextSpec.Resources(R.string.stepEditor_bottomActionBar_title_incomplete),
            message = TextSpec.Resources(R.string.stepEditor_bottomActionBar_message_instructions),
            primaryButton = PrimaryButtonState(
                text = TextSpec.Resources(R.string.stepEditor_bottomActionBar_button_instructions),
                onClick = {
                    _navigate.value = EditDraftStepNavigation.EditInstructions(draftCacheId, draftStepId)
                }
            )
        )

        draftStep.validationCode == null -> BottomActionBarState(
            title = TextSpec.Resources(R.string.stepEditor_bottomActionBar_title_incomplete),
            message = TextSpec.Resources(R.string.stepEditor_bottomActionBar_message_validationCode),
            primaryButton = PrimaryButtonState(
                text = TextSpec.Resources(R.string.stepEditor_bottomActionBar_button_validationCode),
                onClick = {
                    _navigate.value = EditDraftStepNavigation.EditValidationCode(draftCacheId, draftStepId)
                },
            ),
        )

        else -> BottomActionBarState(
            primaryButton = PrimaryButtonState(
                text = TextSpec.Resources(R.string.common_validate),
                onClick = {
                    _navigate.value = EditDraftStepNavigation.Back
                },
            ),
        )
    }

    fun consumeNavigation() {
        _navigate.value = null
    }
}

sealed interface EditDraftStepNavigation {
    data class PickStepCoordinates(
        val draftCacheId: String,
        val draftStepId: String,
    ) : EditDraftStepNavigation

    data class EditInstructions(
        val draftCacheId: String,
        val draftStepId: String,
    ) : EditDraftStepNavigation

    data class EditClue(
        val draftCacheId: String,
        val draftStepId: String,
    ) : EditDraftStepNavigation

    data class EditValidationCode(
        val draftCacheId: String,
        val draftStepId: String,
    ) : EditDraftStepNavigation

    data object Back : EditDraftStepNavigation
}
