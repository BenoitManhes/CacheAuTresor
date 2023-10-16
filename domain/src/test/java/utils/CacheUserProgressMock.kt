package utils

import com.benoitmanhes.domain.model.CacheUserProgress
import java.util.Date

object CacheUserProgressMock {
    fun mock(
        id: String = "id",
        explorerId: String = "explorerId",
        cacheId: String = "cacheId",
        stepDoneRefs: Set<String> = emptySet(),
        currentStepRef: String? = null,
        clueUnlockedStepRef: Set<String> = emptySet(),
        coopMemberRef: String? = null,
        ptsWin: Int? = null,
        foundDate: Date? = null,
    ): CacheUserProgress = CacheUserProgress(
        id = id,
        explorerId = explorerId,
        cacheId = cacheId,
        stepDoneRefs = stepDoneRefs,
        currentStepRef = currentStepRef,
        clueUnlockedStepRef = clueUnlockedStepRef,
        coopMemberRef = coopMemberRef,
        ptsWin = ptsWin,
        foundDate = foundDate,
    )
}