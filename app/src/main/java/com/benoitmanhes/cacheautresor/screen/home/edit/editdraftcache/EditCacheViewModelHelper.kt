package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.EditCrewNameModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getMarkerIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable.CrewStepsCardState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section.DraftStepSectionState
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonOption
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.molecule.sticker.CTStickerIconState
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.model.DraftCacheStep
import com.benoitmanhes.domain.uimodel.UIDraftCache
import kotlinx.coroutines.launch

internal fun EditCacheViewModel.getTypeSticker(type: DraftCache.Type): CTStickerIconState =
    CTStickerIconState(
        icon = type.toCacheType().getIcon(),
        text = type.toCacheType().getTypeText(),
    )

internal fun EditCacheViewModel.getInitCoordinatesUIMarker(draftCache: DraftCache): UIMarker? =
    draftCache.coordinates?.let { coordinates ->
        UIMarker(
            coordinates = coordinates,
            iconMarker = draftCache.type.getMarkerIcon(),
            isSelected = false,
        )
    }

internal fun EditCacheViewModel.getStepSection(steps: UIDraftCache.Steps, draftCacheId: String): DraftStepSectionState =
    when (steps) {
        is UIDraftCache.Steps.Classical -> DraftStepSectionState.Classical(
            finalStep = pickerRow(
                draftStep = steps.instruction,
                draftCacheId = draftCacheId,
                label = TextSpec.Resources(R.string.cacheEditor_stepsClassical_label),
                iconMarker = CacheMarkerIcon.Classical(CTColorTheme.Classical.dayColorScheme.primary),
            )
        )

        is UIDraftCache.Steps.Mystery -> DraftStepSectionState.Mystery(
            enigmaStep = pickerRow(
                draftStep = steps.enigma,
                draftCacheId = draftCacheId,
                label = TextSpec.Resources(R.string.cacheEditor_stepsMystery_label),
                iconMarker = CacheMarkerIcon.Mystery(CTColorTheme.Mystery.dayColorScheme.primary),
            ),
            finalStep = pickerRow(
                draftStep = steps.finalStep,
                draftCacheId = draftCacheId,
                label = TextSpec.Resources(R.string.cacheEditor_stepsFinal_label),
                iconMarker = CacheMarkerIcon.Owner(CTColorTheme.Mystery.dayColorScheme.primary),
            ),
        )

        is UIDraftCache.Steps.Piste -> DraftStepSectionState.Piste(
            intermediarySteps = steps.intermediarySteps.mapIndexed { index, draftStep ->
                pickerRow(
                    draftStep = draftStep,
                    draftCacheId = draftCacheId,
                    label = TextSpec.Resources(R.string.cacheEditor_stepsPiste_label, index + 1),
                    iconMarker = CacheMarkerIcon.Empty(
                        color = CTColorTheme.Piste.dayColorScheme.primary,
                        iconText = (index + 1).toString()
                    )
                )
            },
            addStep = PrimaryButtonState(
                text = TextSpec.Resources(R.string.cacheEditor_addStepButton_title),
                onClick = ::showAddStepModal,
                type = PrimaryButtonType.TEXT,
                options = setOf(
                    PrimaryButtonOption.LeadingIcon(CTTheme.composed { icon.Add }),
                ),
            ),
            finalStep = pickerRow(
                draftStep = steps.finalStep,
                draftCacheId = draftCacheId,
                label = TextSpec.Resources(R.string.cacheEditor_stepsFinal_label),
                iconMarker = CacheMarkerIcon.Owner(CTColorTheme.Piste.dayColorScheme.primary),
            ),
        )

        is UIDraftCache.Steps.Coop -> DraftStepSectionState.Coop(
            crewStepsCards = steps.crewSteps.map {
                crewStepsCard(
                    crewName = it.key,
                    steps = it.value,
                    draftCacheId = draftCacheId,
                )
            },
            finalStep = pickerRow(
                draftStep = steps.finalStep,
                draftCacheId = draftCacheId,
                label = TextSpec.Resources(R.string.cacheEditor_stepsFinal_label),
                iconMarker = CacheMarkerIcon.Owner(CTColorTheme.Piste.dayColorScheme.primary),
            ),
            addCrewMember = {
                showEditCrewMemberNameModal(
                    crewRef = "",
                    error = false,
                ) { newCrewRef ->
                    addCrewMemberDraftCacheUseCase(draftCacheId = draftCacheId, crewMemberRef = newCrewRef)
                }
            }
        )
    }

internal fun EditCacheViewModel.showEditCrewMemberNameModal(
    crewRef: String,
    error: Boolean,
    crewMemberOperation: suspend (String) -> CTSuspendResult<Unit>,
) {
    modalBottomSheetManager.showModal(
        EditCrewNameModalBottomSheet(
            initialText = crewRef,
            errorMessage = TextSpec.Resources(R.string.modalEditCrewName_error).takeIf { error },
            onValidate = { newCrewName, hide ->
                viewModelScope.launch {
                    val result = crewMemberOperation(newCrewName)

                    when ((result as? CTSuspendResult.Failure)?.error?.code) {
                        CTDomainError.Code.CREW_MEMBER_NAME_UNAVAILABLE -> showEditCrewMemberNameModal(
                            crewRef = crewRef,
                            error = true,
                            crewMemberOperation = crewMemberOperation,
                        )

                        else -> hide()
                    }
                }
            },
        )
    )
}

private fun EditCacheViewModel.pickerRow(
    draftStep: DraftCacheStep,
    draftCacheId: String,
    label: TextSpec,
    iconMarker: CacheMarkerIcon,
): MapRowPickerState = MapRowPickerState(
    uiMarker = draftStep.coordinates?.let {
        UIMarker(
            coordinates = it,
            iconMarker = iconMarker,
            isSelected = false,
        )
    },
    text = label,
    key = draftStep.stepDraftId,
    onClick = {
        _navigation.value = EditCacheNavigation.EditDraftStep(
            draftStepId = draftStep.stepDraftId,
            draftCacheId = draftCacheId,
        )
    },
)

private fun EditCacheViewModel.crewStepsCard(
    crewName: String,
    steps: List<DraftCacheStep>,
    draftCacheId: String,
): CrewStepsCardState = CrewStepsCardState(
    crewName = crewName.textSpec(),
    steps = steps.mapIndexed { index, draftCacheStep ->
        pickerRow(
            draftStep = draftCacheStep,
            draftCacheId = draftCacheId,
            label = TextSpec.Resources(R.string.cacheEditor_stepsPiste_label, index + 1),
            iconMarker = CacheMarkerIcon.Empty(
                color = CTColorTheme.Coop.dayColorScheme.primary,
                iconText = (index + 1).toString()
            ),
        )
    },
    onClickAddStep = {
        showAddStepModal(crewName)
    },
    onClickEdit = {
        showEditCrewMemberNameModal(crewRef = crewName, error = false) { newCrewValue ->
            editCrewMemberNameUseCase(
                draftCacheId = draftCacheId,
                crewMemberRef = crewName,
                newCrewMemberName = newCrewValue,
            )
        }
    },
    onClickDelete = { showRemoveCrewMemberModal(crewName, draftCacheId) },
)
