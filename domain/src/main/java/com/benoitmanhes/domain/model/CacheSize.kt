package com.benoitmanhes.domain.model

enum class CacheSize {
    Micro, Small, Regular, Big, Undefined;

    val value: String = name

    companion object {
        fun get(value: String): CacheSize = try {
            valueOf(value)
        } catch (e: Exception) {
            Undefined
        }
    }
}
