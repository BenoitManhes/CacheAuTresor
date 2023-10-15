package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.CacheUserProgress
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.Timestamp

@IgnoreExtraProperties
data class FSCacheUserProgress(
    override val id: String? = null,
    val cacheId: String? = null,
    val explorerId: String? = null,
    val stepDoneRefs: List<String>? = null,
    val currentStepRef: String? = null,
    val clueUnlockedStepRef: List<String>? = null,
    val coopMemberRef: String? = null,
    val ptsWin: Int? = null,
    val foundDate: Timestamp? = null,
) : FirestoreModel<CacheUserProgress> {

    constructor(appModel: CacheUserProgress) : this(
        id = appModel.id,
        explorerId = appModel.explorerId,
        cacheId = appModel.cacheId,
        stepDoneRefs = appModel.stepDoneRefs.toList().ifEmpty { null },
        currentStepRef = appModel.currentStepRef,
        clueUnlockedStepRef = appModel.clueUnlockedStepRef.toList().ifEmpty { null },
        coopMemberRef = appModel.coopMemberRef,
        ptsWin = appModel.ptsWin,
        foundDate = appModel.foundDate?.let(::Timestamp),
    )

    override fun toAppModel(): CacheUserProgress = CacheUserProgress(
        id = id.requiredField(),
        explorerId = explorerId.requiredField(),
        cacheId = cacheId.requiredField(),
        stepDoneRefs = stepDoneRefs?.toSet() ?: emptySet(),
        currentStepRef = currentStepRef,
        clueUnlockedStepRef = clueUnlockedStepRef?.toSet() ?: emptySet(),
        coopMemberRef = coopMemberRef,
        ptsWin = ptsWin,
        foundDate = foundDate?.toDate(),
    )
}
