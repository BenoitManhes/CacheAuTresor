package com.benoitmanhes.server.firestore.builder

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.server.extensions.toCTError

object CacheTypeBuilder {
    fun appModel(map: Map<String, Any>): Cache.Type {
        val cacheType = map[Key.cacheType] as String
        @Suppress("UNCHECKED_CAST")
        return when (cacheType) {
            Value.cacheTypeClassical -> Cache.Type.Classical
            Value.cacheTypeMystery -> Cache.Type.Mystery(
                enigmaStepId = map[Key.enigmaStepId] as String,
            )

            Value.cacheTypeCoop -> Cache.Type.Coop(
                crewStepsMap = map[Key.crewStepsMap] as Map<String, List<String>>,
            )

            Value.cacheTypePiste -> Cache.Type.Piste(
                intermediateStepIds = map[Key.intermediateStepIds] as List<String>
            )

            else -> throw CTRemoteError.ParsingFailed("unknown cache type $cacheType").toCTError()
        }
    }

    fun fsModel(cacheType: Cache.Type): Map<String, Any> = buildMap {
        when (cacheType) {
            is Cache.Type.Classical -> {
                put(Key.cacheType, Value.cacheTypeClassical)
            }

            is Cache.Type.Coop -> {
                put(Key.cacheType, Value.cacheTypeCoop)
                put(Key.crewStepsMap, cacheType.crewStepsMap)
            }

            is Cache.Type.Piste -> {
                put(Key.cacheType, Value.cacheTypePiste)
                put(Key.intermediateStepIds, cacheType.intermediateStepIds)
            }

            is Cache.Type.Mystery -> {
                put(Key.cacheType, Value.cacheTypeMystery)
                put(Key.enigmaStepId, cacheType.enigmaStepId)
            }
        }
    }

    private object Key {
        const val cacheType: String = "cacheType"
        const val enigmaStepId: String = "enigmaStepId"
        const val crewStepsMap: String = "crewStepsMap"
        const val intermediateStepIds: String = "intermediateStepIds"
    }

    private object Value {
        const val cacheTypeClassical: String = "classical"
        const val cacheTypeCoop: String = "coop"
        const val cacheTypeMystery: String = "mystery"
        const val cacheTypePiste: String = "piste"
    }
}
