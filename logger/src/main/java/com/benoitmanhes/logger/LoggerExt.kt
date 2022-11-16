package com.benoitmanhes.logger

import org.jetbrains.annotations.NonNls
import org.slf4j.Logger

/**
 * Timber like extensions
 */

/** Log a verbose message with one format arg. */
fun Logger.v(@NonNls message: String?, arg: Any?) {
    this.trace(message, arg)
}

/** Log a verbose message with optional format args. */
fun Logger.v(@NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.trace(formattedMessage)
}

/** Log a verbose exception and a message with optional format args. */
fun Logger.v(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.trace(formattedMessage, t)
}

/** Log a verbose exception. */
fun Logger.v(t: Throwable?) {
    this.trace("", t)
}

/** Log a debug message with one format arg. */
fun Logger.d(@NonNls message: String?, arg: Any?) {
    this.debug(message, arg)
}

/** Log a debug message with optional format args. */
fun Logger.d(@NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.debug(formattedMessage)
}

/** Log a debug exception and a message with optional format args. */
fun Logger.d(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.debug(formattedMessage, t)
}

/** Log a debug exception. */
fun Logger.d(t: Throwable?) {
    this.debug("", t)
}

/** Log a info message with one format arg. */
fun Logger.i(@NonNls message: String?, arg: Any?) {
    this.info(message, arg)
}

/** Log an info message with optional format args. */
fun Logger.i(@NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.info(formattedMessage)
}

/** Log an info exception and a message with optional format args. */
fun Logger.i(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.info(formattedMessage, t)
}

/** Log an info exception. */
fun Logger.i(t: Throwable?) {
    this.info("", t)
}

/** Log a warning message with one format arg. */
fun Logger.w(@NonNls message: String?, arg: Any?) {
    this.warn(message, arg)
}

/** Log a warning message with optional format args. */
fun Logger.w(@NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.warn(formattedMessage)
}

/** Log a warning exception and a message with optional format args. */
fun Logger.w(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.warn(formattedMessage, t)
}

/** Log a warning exception. */
fun Logger.w(t: Throwable?) {
    this.warn("", t)
}

/** Log a error message with one format arg. */
fun Logger.e(@NonNls message: String?, arg: Any?) {
    this.error(message, arg)
}

/** Log an error message with optional format args. */
fun Logger.e(@NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.error(formattedMessage)
}

/** Log an error exception and a message with optional format args. */
fun Logger.e(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
    val formattedMessage = formatMessage(args, message)
    this.error(formattedMessage, t)
}

/** Log an error exception. */
fun Logger.e(t: Throwable?) {
    this.error("", t)
}

@Suppress("SpreadOperator")
private fun formatMessage(args: Array<out Any?>, @NonNls message: String?): String? {
    val formattedMessage = if (args.isNotEmpty()) {
        message?.format(*args)
    } else {
        message
    }
    return formattedMessage
}
