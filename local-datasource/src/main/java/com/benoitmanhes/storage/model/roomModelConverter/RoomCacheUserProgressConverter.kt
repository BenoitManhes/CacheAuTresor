package com.benoitmanhes.storage.model.roomModelConverter

import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.storage.model.RoomCacheUserProgress

object RoomCacheUserProgressConverter : AbstractRoomModelConverter<CacheUserProgress, RoomCacheUserProgress> {
    override fun buildRoomModel(appModel: CacheUserProgress): RoomCacheUserProgress = RoomCacheUserProgress(
        id = appModel.id,
        explorerId = appModel.explorerId,
        cacheId = appModel.cacheId,
        stepDoneRefs = appModel.stepDoneRefs.toList(),
        currentStepRef = appModel.currentStepRef,
        clueUnlockedStepRef = appModel.clueUnlockedStepRef.toList(),
        coopStepRef = appModel.coopStepRef,
        ptsWin = appModel.ptsWin,
        foundDate = appModel.foundDate,
    )
}
