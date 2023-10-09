package com.benoitmanhes.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.CacheUserProgress
import java.util.Date

@Entity(tableName = "cache-user-progress")
data class RoomCacheUserProgress(
    @PrimaryKey val id: String,
    val explorerId: String,
    val cacheId: String,
    val stepDoneRefs: List<String> = emptyList(),
    val currentStepRef: String? = null,
    val clueUnlockedStepRef: List<String> = emptyList(),
    val coopStepRef: String? = null,
    val ptsWin: Int? = null,
    val foundDate: Date? = null,
) : RoomModel<CacheUserProgress> {

    override fun toAppModel(): CacheUserProgress = CacheUserProgress(
        id = id,
        explorerId = explorerId,
        cacheId = cacheId,
        stepDoneRefs = stepDoneRefs.toSet(),
        currentStepRef = currentStepRef,
        clueUnlockedStepRef = clueUnlockedStepRef.toSet(),
        coopStepRef = coopStepRef,
        ptsWin = ptsWin,
        foundDate = foundDate,
    )
}
