package com.benoitmanhes.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object CTLogger {
    /**
     * Get or create a [Logger] with name inferred from type [T]
     */
    inline fun <reified T : Any> get(): Logger = LoggerFactory.getLogger(T::class.java)

    /**
     * Get or create a [Logger] with name [name]
     */
    fun get(name: String): Logger = LoggerFactory.getLogger(name)
}
