package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec

fun Float.toDifficultyText(): TextSpec {
    val difficultyRes = when {
        this > 5 -> R.string.common_unknown
        this == 5f -> R.string.cache_difficulty_5
        this in 4f..5f -> R.string.cache_difficulty_4
        this in 3f..4f -> R.string.cache_difficulty_3
        this in 2f..3f -> R.string.cache_difficulty_2
        this in 1f..2f -> R.string.cache_difficulty_1
        else -> R.string.common_unknown
    }
    return TextSpec.Resources(difficultyRes)
}

fun Float.toGroundText(): TextSpec {
    val difficultyRes = when {
        this > 5 -> R.string.common_unknown
        this == 5f -> R.string.cache_ground_5
        this in 4f..5f -> R.string.cache_ground_4
        this in 3f..4f -> R.string.cache_ground_3
        this in 2f..3f -> R.string.cache_ground_2
        this in 1f..2f -> R.string.cache_ground_1
        else -> R.string.common_unknown
    }
    return TextSpec.Resources(difficultyRes)
}
