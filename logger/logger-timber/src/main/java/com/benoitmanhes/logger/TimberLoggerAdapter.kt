package com.benoitmanhes.logger

import android.util.Log
import com.benoitmanhes.logger.timber.BuildConfig
import org.slf4j.Logger
import org.slf4j.Marker
import timber.log.Timber

class TimberLoggerAdapter(private val tag: String) : Logger {

    override fun getName(): String = tag

    override fun isTraceEnabled(): Boolean = ENABLE_DEBUG
    override fun trace(msg: String): Unit = log(Log.VERBOSE, msg)
    override fun trace(format: String, arg: Any): Unit = log(Log.VERBOSE, format, arg)
    override fun trace(format: String, arg1: Any, arg2: Any): Unit = log(Log.VERBOSE, format, arg1, arg2)
    override fun trace(format: String, vararg argArray: Any): Unit = log(Log.VERBOSE, format, *argArray)
    override fun trace(msg: String, t: Throwable): Unit = log(Log.VERBOSE, msg, t)

    override fun isDebugEnabled(): Boolean = ENABLE_DEBUG
    override fun debug(msg: String): Unit = log(Log.DEBUG, msg)
    override fun debug(format: String, arg: Any): Unit = log(Log.DEBUG, format, arg)
    override fun debug(format: String, arg1: Any, arg2: Any): Unit = log(Log.DEBUG, format, arg1, arg2)
    override fun debug(format: String, vararg argArray: Any): Unit = log(Log.DEBUG, format, *argArray)
    override fun debug(msg: String, t: Throwable): Unit = log(Log.DEBUG, msg, t)

    override fun isInfoEnabled(): Boolean = true
    override fun info(msg: String): Unit = log(Log.INFO, msg)
    override fun info(format: String, arg: Any): Unit = log(Log.INFO, format, arg)
    override fun info(format: String, arg1: Any, arg2: Any): Unit = log(Log.INFO, format, arg1, arg2)
    override fun info(format: String, vararg argArray: Any): Unit = log(Log.INFO, format, *argArray)
    override fun info(msg: String, t: Throwable): Unit = log(Log.INFO, msg, t)

    override fun isWarnEnabled(): Boolean = true
    override fun warn(msg: String): Unit = log(Log.WARN, msg)
    override fun warn(format: String, arg: Any): Unit = log(Log.WARN, format, arg)
    override fun warn(format: String, arg1: Any, arg2: Any): Unit = log(Log.WARN, format, arg1, arg2)
    override fun warn(format: String, vararg argArray: Any): Unit = log(Log.WARN, format, *argArray)
    override fun warn(msg: String, t: Throwable): Unit = log(Log.WARN, msg, t)

    override fun isErrorEnabled(): Boolean = true
    override fun error(msg: String): Unit = log(Log.ERROR, msg)
    override fun error(format: String, arg: Any): Unit = log(Log.ERROR, format, arg)
    override fun error(format: String, arg1: Any, arg2: Any): Unit = log(Log.ERROR, format, arg1, arg2)
    override fun error(format: String, vararg argArray: Any): Unit = log(Log.ERROR, format, *argArray)
    override fun error(msg: String, t: Throwable): Unit = log(Log.ERROR, msg, t)

    private fun log(priority: Int, message: String, arg: Any) {
        Timber.tag(name).log(priority, message, arg)
    }

    private fun log(priority: Int, message: String, arg1: Any, arg2: Any) {
        Timber.tag(name).log(priority, message, arg1, arg2)
    }

    private fun log(priority: Int, message: String, vararg argArray: Any) {
        Timber.tag(name).log(priority, message, argArray)
    }

    private fun log(priority: Int, message: String, throwable: Throwable) {
        Timber.tag(name).log(priority, throwable, message)
    }

    companion object {
        var ENABLE_DEBUG: Boolean = BuildConfig.DEBUG
    }

    // No marker impl //

    override fun isTraceEnabled(marker: Marker): Boolean {
        return isTraceEnabled
    }

    override fun trace(marker: Marker, msg: String) {
        trace(msg)
    }

    override fun trace(marker: Marker, format: String, arg: Any) {
        trace(format, arg)
    }

    override fun trace(marker: Marker, format: String, arg1: Any, arg2: Any) {
        trace(format, arg1, arg2)
    }

    override fun trace(marker: Marker, format: String, vararg arguments: Any) {
        trace(format, *arguments)
    }

    override fun trace(marker: Marker, msg: String, t: Throwable) {
        trace(msg, t)
    }

    override fun isDebugEnabled(marker: Marker): Boolean {
        return isDebugEnabled
    }

    override fun debug(marker: Marker, msg: String) {
        debug(msg)
    }

    override fun debug(marker: Marker, format: String, arg: Any) {
        debug(format, arg)
    }

    override fun debug(marker: Marker, format: String, arg1: Any, arg2: Any) {
        debug(format, arg1, arg2)
    }

    override fun debug(marker: Marker, format: String, vararg arguments: Any) {
        debug(format, *arguments)
    }

    override fun debug(marker: Marker, msg: String, t: Throwable) {
        debug(msg, t)
    }

    override fun isInfoEnabled(marker: Marker): Boolean {
        return isInfoEnabled
    }

    override fun info(marker: Marker, msg: String) {
        info(msg)
    }

    override fun info(marker: Marker, format: String, arg: Any) {
        info(format, arg)
    }

    override fun info(marker: Marker, format: String, arg1: Any, arg2: Any) {
        info(format, arg1, arg2)
    }

    override fun info(marker: Marker, format: String, vararg arguments: Any) {
        info(format, *arguments)
    }

    override fun info(marker: Marker, msg: String, t: Throwable) {
        info(msg, t)
    }

    override fun isWarnEnabled(marker: Marker): Boolean {
        return isWarnEnabled
    }

    override fun warn(marker: Marker, msg: String) {
        warn(msg)
    }

    override fun warn(marker: Marker, format: String, arg: Any) {
        warn(format, arg)
    }

    override fun warn(marker: Marker, format: String, arg1: Any, arg2: Any) {
        warn(format, arg1, arg2)
    }

    override fun warn(marker: Marker, format: String, vararg arguments: Any) {
        warn(format, *arguments)
    }

    override fun warn(marker: Marker, msg: String, t: Throwable) {
        warn(msg, t)
    }

    override fun isErrorEnabled(marker: Marker): Boolean {
        return isErrorEnabled
    }

    override fun error(marker: Marker, msg: String) {
        kotlin.error(msg)
    }

    override fun error(marker: Marker, format: String, arg: Any) {
        error(format, arg)
    }

    override fun error(marker: Marker, format: String, arg1: Any, arg2: Any) {
        error(format, arg1, arg2)
    }

    override fun error(marker: Marker, format: String, vararg arguments: Any) {
        error(format, *arguments)
    }

    override fun error(marker: Marker, msg: String, t: Throwable) {
        error(msg, t)
    }

    override fun toString(): String {
        return this.javaClass.name + "(" + name + ")"
    }
}
