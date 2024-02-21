package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.uimodel.UIDraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUIDraftCacheStepsUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
) : CTUseCase() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun asFlow(draftCacheId: String): Flow<UIDraftCache.Steps?> =
        draftCacheRepository.getDraftCacheFlow(draftCacheId)
            .distinctUntilChanged { old, new ->
                old?.type == new?.type
                    && old?.finalStepRef == new?.finalStepRef
            }
            .flatMapLatest { draftCache: DraftCache? ->
                when (draftCache?.type) {
                    is DraftCache.Type.Classical -> getClassicalStepFlow(draftCache.finalStepRef)

                    is DraftCache.Type.Mystery -> getMysteryStepFlow(
                        finalStepsRef = draftCache.finalStepRef,
                        mystery = draftCache.type,
                    )

                    is DraftCache.Type.Piste -> getPisteStepFlow(
                        finalStepsRef = draftCache.finalStepRef,
                        piste = draftCache.type,
                    )

                    is DraftCache.Type.Coop -> getCoopStepFlow(
                        finalStepsRef = draftCache.finalStepRef,
                        coop = draftCache.type,
                    )

                    null -> flowOf(null)
                }
            }

    private fun getClassicalStepFlow(finalStepsRef: String?): Flow<UIDraftCache.Steps.Classical?> =
        finalStepsRef?.let {
            draftCacheStepRepository.getDraftCacheStepFlow(finalStepsRef).map { instructions ->
                instructions?.let(UIDraftCache.Steps::Classical)
            }
        } ?: flowOf(null)

    private fun getMysteryStepFlow(finalStepsRef: String?, mystery: DraftCache.Type.Mystery): Flow<UIDraftCache.Steps.Mystery?> =
        combine(
            finalStepsRef?.let(draftCacheStepRepository::getDraftCacheStepFlow) ?: flowOf(null),
            mystery.enigmaDraftStepId?.let(draftCacheStepRepository::getDraftCacheStepFlow) ?: flowOf(null),
        ) { finalStep, enigmaStep ->
            UIDraftCache.Steps.Mystery(
                enigma = enigmaStep ?: return@combine null,
                finalStep = finalStep ?: return@combine null,
            )
        }

    private fun getPisteStepFlow(finalStepsRef: String?, piste: DraftCache.Type.Piste): Flow<UIDraftCache.Steps.Piste?> =
        combine(
            finalStepsRef?.let(draftCacheStepRepository::getDraftCacheStepFlow) ?: flowOf(null),
            draftCacheStepRepository.getDraftCacheStepsFlow(piste.intermediateDraftStepIds),
        ) { finalStep, intermiaries ->
            UIDraftCache.Steps.Piste(
                intermediarySteps = intermiaries.sortedBy { step ->
                    piste.intermediateDraftStepIds.indexOf(
                        step.stepDraftId
                    )
                },
                finalStep = finalStep ?: return@combine null,
            )
        }

    private fun getCoopStepFlow(finalStepsRef: String?, coop: DraftCache.Type.Coop): Flow<UIDraftCache.Steps.Coop?> =
        combine(
            finalStepsRef?.let(draftCacheStepRepository::getDraftCacheStepFlow) ?: flowOf(null),
            draftCacheStepRepository.getDraftCacheStepsFlow(coop.crewDraftStepsMap.values.flatten()),
        ) { finalStep, intermediates ->
            val crewStepsMap = coop.crewDraftStepsMap.mapValues { (_, stepsRefs) ->
                stepsRefs.mapNotNull { stepRef ->
                    intermediates.firstOrNull { it.stepDraftId == stepRef }
                }
            }
            UIDraftCache.Steps.Coop(
                finalStep = finalStep ?: return@combine null,
                crewSteps = crewStepsMap,
            )
        }
}
