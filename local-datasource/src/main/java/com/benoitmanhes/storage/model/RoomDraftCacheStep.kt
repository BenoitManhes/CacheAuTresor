package com.benoitmanhes.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCacheStep

@Entity(tableName = "draft-cache-step")
data class RoomDraftCacheStep(
    @PrimaryKey val stepDraftId: String,
    val instruction: String?,
    val clue: String?,
    val validationCode: String?,
    @Embedded(prefix = "coordinates_") val coordinates: Coordinates?,
) : RoomModel<DraftCacheStep> {

    override fun toAppModel(): DraftCacheStep = DraftCacheStep(
        stepDraftId = stepDraftId,
        instruction = instruction,
        clue = clue,
        validationCode = validationCode,
        coordinates = coordinates
    )

    companion object {
        fun build(appModel: DraftCacheStep): RoomDraftCacheStep = RoomDraftCacheStep(
            instruction = appModel.instruction,
            stepDraftId = appModel.stepDraftId,
            clue = appModel.clue,
            validationCode = appModel.validationCode,
            coordinates = appModel.coordinates,
        )
    }
}
