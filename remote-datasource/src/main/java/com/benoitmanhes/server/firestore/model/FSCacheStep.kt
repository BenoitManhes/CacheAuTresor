package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.CacheStep
import com.benoitmanhes.domain.model.InstructionContent
import com.benoitmanhes.server.RemoteConstants
import com.benoitmanhes.server.extensions.toFSModel
import com.benoitmanhes.server.extensions.toModel
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class FSCacheStep(
    override val id: String? = null,
    val instruction: List<String>? = null,
    val clue: String? = null,
    val coordinates: GeoPoint? = null,
    val validationCode: String? = null,
) : FirestoreModel<CacheStep> {

    constructor(step: CacheStep) : this(
        id = step.stepId,
        instruction = step.instruction.map { content ->
            when (content) {
                is InstructionContent.Text -> content.value
                is InstructionContent.Image -> RemoteConstants.Instruction.imagePrefix + content.imageUrl
            }
        },
        clue = step.clue,
        coordinates = step.coordinates.toFSModel(),
        validationCode = step.validationCode,
    )

    override fun toAppModel(): CacheStep {
        val instructions = instruction?.map { rawContent ->
            if (rawContent.startsWith(RemoteConstants.Instruction.imagePrefix)) {
                InstructionContent.Image(
                    imageUrl = rawContent.removePrefix(RemoteConstants.Instruction.imagePrefix)
                )
            } else {
                InstructionContent.Text(rawContent)
            }
        }

        return CacheStep(
            stepId = id.requiredField(),
            instruction = instructions.requiredField(),
            clue = clue,
            validationCode = validationCode.requiredField(),
            coordinates = coordinates?.toModel().requiredField(),
        )
    }
}
