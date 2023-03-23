package com.benoitmanhes.domain.model

enum class CacheSize {
    Micro, Small, Regular, Big, Undefined;

    val value: String = name

    companion object {
        fun build(value: String): CacheSize = when (value.lowercase()) {
            Small.value.lowercase() -> Small
            Regular.value.lowercase() -> Regular
            Big.value.lowercase() -> Big
            else -> Undefined
        }
    }
}
