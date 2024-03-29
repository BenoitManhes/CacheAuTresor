package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.CommonModalAction
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.StickerRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.cacheautresor.common.extensions.format
import com.benoitmanhes.cacheautresor.common.extensions.orPlaceHolder
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogManager
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.cacheautresor.screen.snackbar.showOnFailure
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.getTypeColorTheme
import com.benoitmanhes.domain.model.CacheCreationStep
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.usecase.draftcache.AddCrewMemberDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.CreateCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.DeleteCrewMemberDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.DeleteDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.EditCrewMemberNameUseCase
import com.benoitmanhes.domain.usecase.draftcache.GetUIDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.NewDraftStepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCacheViewModel @Inject constructor(
    internal val modalBottomSheetManager: ModalBottomSheetManager,
    private val deleteCrewMemberDraftCacheUseCase: DeleteCrewMemberDraftCacheUseCase,
    internal val editCrewMemberNameUseCase: EditCrewMemberNameUseCase,
    internal val addCrewMemberDraftCacheUseCase: AddCrewMemberDraftCacheUseCase,
    private val newDraftStepUseCase: NewDraftStepUseCase,
    private val deleteDraftCacheUseCase: DeleteDraftCacheUseCase,
    private val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
    private val createCacheUseCase: CreateCacheUseCase,
    internal val alertDialogManager: AlertDialogManager,
    getUIDraftCacheUseCase: GetUIDraftCacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    internal val draftCacheId: String = savedStateHandle.get<String>(EditCacheDestination.EditDraftCache.arg).orEmpty()

    val editCacheState: StateFlow<EditCacheViewModelState> = getUIDraftCacheUseCase(draftCacheId)
        .map { uiDraftCache ->
            val draftCache = uiDraftCache?.draftCache
            val creationStep = uiDraftCache?.creationStep ?: CacheCreationStep.Name
            EditCacheViewModelState(
                bottomActionBar = bottomActionBar(creationStep),
                cacheName = TextRowPickerState(
                    text = draftCache?.title?.textSpec().orPlaceHolder(),
                    onClick = { _navigation.value = EditCacheNavigation.PickName(draftCacheId) },
                ),

                cacheType = StickerRowPickerState(
                    sticker = draftCache?.type?.let(::getTypeSticker),
                    colorTheme = draftCache?.type?.toCacheType()?.getTypeColorTheme() ?: CTColorTheme.Cartography,
                    onClick = { _navigation.value = EditCacheNavigation.PickType(draftCacheId) },
                ),

                initCoordinates = MapRowPickerState(
                    uiMarker = draftCache?.let(::getInitCoordinatesUIMarker),
                    text = draftCache?.coordinates?.format(Coordinates.Format.DM).orPlaceHolder(),
                    key = "init.coordinates",
                    onClick = { _navigation.value = EditCacheNavigation.PickInitCoordinates(draftCacheId) }
                ),

                stepSection = uiDraftCache?.steps?.let { getStepSection(it, draftCacheId) }
                    ?.takeIf { stepsSectionIsVisible(creationStep) },

                propertiesSection = draftCache?.let(::propertiesSection)
                    ?.takeIf { characteristicsSectionIsVisible(creationStep) },

                descriptionSection = TextRowPickerState(
                    text = draftCache?.description?.textSpec().orPlaceHolder(),
                    onClick = { _navigation.value = EditCacheNavigation.PickDescription(draftCacheId) },
                )
                    .takeIf { characteristicsSectionIsVisible(creationStep) },

                unlockInstructions = TextRowPickerState(
                    text = draftCache?.lockDescription?.textSpec().orPlaceHolder(),
                    onClick = { _navigation.value = EditCacheNavigation.PickUnlockInstructions(draftCacheId) },
                )
                    .takeIf { lockSectionIsVisible(creationStep) },

                unlockCode = TextRowPickerState(
                    text = draftCache?.lockCode?.textSpec().orPlaceHolder(),
                    onClick = { _navigation.value = EditCacheNavigation.PickUnlockCode(draftCacheId) },
                )
                    .takeIf { lockSectionIsVisible(creationStep) },
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = EditCacheViewModelState(),
        )

    internal val _navigation = MutableStateFlow<EditCacheNavigation?>(null)
    val navigation: StateFlow<EditCacheNavigation?> get() = _navigation.asStateFlow()
    fun consumeNavigation() {
        _navigation.value = null
    }

    internal fun showRemoveCrewMemberModal(
        crewRef: String,
        draftCacheId: String,
    ) {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = CTTheme.composed { icon.DeletePerson },
                title = TextSpec.Resources(R.string.modalDeleteCrew_title),
                message = TextSpec.Resources(R.string.modalDeleteCrew_message, crewRef),
                color = CTTheme.composed { color.critical },
                cancelAction = CommonModalAction.finallyNo(),
                confirmAction = CommonModalAction.delete {
                    viewModelScope.launch(Dispatchers.Default) {
                        deleteCrewMemberDraftCacheUseCase(draftCacheId = draftCacheId, crewRef = crewRef)
                    }
                },
            )
        )
    }

    internal fun showAddStepModal(crewRef: String? = null) {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = CTTheme.composed { icon.AddRoad },
                title = TextSpec.Resources(R.string.modalAddStep_title),
                message = if (crewRef.isNullOrBlank()) {
                    TextSpec.Resources(R.string.modalAddStep_message_piste)
                } else {
                    TextSpec.Resources(R.string.modalAddStep_message_coop, crewRef)
                },
                cancelAction = CommonModalAction.finallyNo(),
                confirmAction = CommonModalAction.validate {
                    viewModelScope.launch(Dispatchers.Default) {
                        newDraftStepUseCase(draftCacheId = draftCacheId, crewRef = crewRef).collect { result ->
                            loadingManager.handleFromResult(result)
                            snackbarManager.showError((result as? CTResult.Failure)?.error)
                        }
                    }
                }
            )

        )
    }

    fun showDeleteModal() {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = CTTheme.composed { icon.Delete },
                title = TextSpec.Resources(R.string.editCache_deleteModal_title),
                message = TextSpec.Resources(R.string.editCache_deleteModal_message),
                color = CTTheme.composed { color.critical },
                cancelAction = CommonModalAction.finallyNo(),
                confirmAction = CommonModalAction.delete(::deleteDraftCache),
            ),
        )
    }

    private fun deleteDraftCache() {
        viewModelScope.launch(Dispatchers.Default) {
            deleteDraftCacheUseCase(draftCacheId).collect { result ->
                loadingManager.handleFromResult(result)
                snackbarManager.showOnFailure(result)
                if (result is CTResult.Success) {
                    _navigation.value = EditCacheNavigation.Back
                }
            }
        }
    }

    internal fun showModalActivation() {
        modalBottomSheetManager.showModal(
            ClassicModalBottomSheet(
                icon = CTTheme.composed { icon.BoxBig },
                title = TextSpec.Resources(R.string.modalActivation_title),
                message = TextSpec.Resources(R.string.modalActivation_message),
                confirmAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.modalActivation_action_yes),
                    onClick = ::activate,
                ),
                cancelAction = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.modalActivation_action_no),
                    onClick = {}, // nothing
                ),
            )
        )
    }

    private fun activate() {
        viewModelScope.launch(Dispatchers.Default) {
            createCacheUseCase(draftCacheId).collect { result ->
                loadingManager.handleFromResult(result)
                when (result) {
                    is CTResult.Loading -> {} // nothing
                    is CTResult.Success -> _navigation.value = EditCacheNavigation.CreationSuccess(result.successData.cacheId)
                    is CTResult.Failure -> {
                        when (result.error?.code) {
                            CTDomainError.Code.DRAFT_CACHE_INIT_COORDINATES_INVALID -> {
                                showInvalidCoordinatesAlertDialog(
                                    error = result.error,
                                    onClickChange = { _navigation.value = EditCacheNavigation.PickInitCoordinates(draftCacheId) }
                                )
                            }

                            CTDomainError.Code.DRAFT_CACHE_FINAL_COORDINATES_INVALID -> {
                                showInvalidCoordinatesAlertDialog(
                                    error = result.error,
                                    onClickChange = {
                                        editCacheState.value.stepSection?.finalStep?.onClick?.invoke()
                                    }
                                )
                            }

                            else -> {
                                snackbarManager.showError(result.error)
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed interface EditCacheNavigation {
    data object Back : EditCacheNavigation

    @JvmInline
    value class PickName(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickType(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickInitCoordinates(val draftCacheId: String) : EditCacheNavigation

    data class EditDraftStep(
        val draftCacheId: String,
        val draftStepId: String,
    ) : EditCacheNavigation

    @JvmInline
    value class PickDifficulty(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickGround(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickSize(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickDescription(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickUnlockInstructions(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickUnlockCode(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class CreationSuccess(val cacheId: String) : EditCacheNavigation
}
