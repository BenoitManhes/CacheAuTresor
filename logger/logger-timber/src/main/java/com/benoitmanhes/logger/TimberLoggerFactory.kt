package com.benoitmanhes.logger

import org.slf4j.ILoggerFactory
import org.slf4j.Logger
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * Inspired from https://github.com/qos-ch/slf4j/blob/d0836be06f72363a975d6d719b53e577b0701021/slf4j-simple/src/main/java/org/slf4j/simple/SimpleLoggerFactory.java
 */
class TimberLoggerFactory : ILoggerFactory {
    private val loggerMap: ConcurrentMap<String, Logger> = ConcurrentHashMap()

    override fun getLogger(tag: String): Logger {
        var logger = loggerMap[tag]
        if (logger == null) {
            val newInstance: Logger = TimberLoggerAdapter(tag)
            val oldInstance = loggerMap.putIfAbsent(tag, newInstance)
            logger = oldInstance ?: newInstance
        }
        return logger
    }
}
