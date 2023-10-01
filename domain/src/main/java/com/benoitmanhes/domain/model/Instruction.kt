package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable

typealias CacheInstructions = List<InstructionContent>

@Serializable
sealed interface InstructionContent : Model {
    @Serializable
    data class Text(val value: String) : InstructionContent

    @Serializable
    data class Image(val imageUrl: String) : InstructionContent
}
