package com.benoitmanhes.logger

import com.benoitmanhes.cacheautresor.logger.BuildConfig
import org.slf4j.ILoggerFactory
import org.slf4j.IMarkerFactory
import org.slf4j.helpers.BasicMarkerFactory
import org.slf4j.helpers.NOPMDCAdapter
import org.slf4j.spi.MDCAdapter
import org.slf4j.spi.SLF4JServiceProvider

// https://github.com/qos-ch/slf4j/blob/master/slf4j-simple/src/main/java/org/slf4j/simple/SimpleServiceProvider.java
class TimberServiceProvider : SLF4JServiceProvider {
    private lateinit var loggerFactory: ILoggerFactory
    private lateinit var markerFactory: IMarkerFactory
    private lateinit var mdcAdapter: MDCAdapter

    override fun getLoggerFactory(): ILoggerFactory = loggerFactory
    override fun getMarkerFactory(): IMarkerFactory = markerFactory
    override fun getMDCAdapter(): MDCAdapter = mdcAdapter
    override fun getRequestedApiVersion(): String = REQUESTED_API_VERSION

    override fun initialize() {
        loggerFactory = TimberLoggerFactory()
        markerFactory = BasicMarkerFactory()
        mdcAdapter = NOPMDCAdapter()
    }

    companion object {
        /**
         * Declare the version of the SLF4J API this implementation is compiled against.
         * The value of this field is modified with each major release.
         */
        // to avoid constant folding by the compiler, this field must *not* be final
        const val REQUESTED_API_VERSION: String = BuildConfig.SLF4j_VERSION // !final
    }
}
