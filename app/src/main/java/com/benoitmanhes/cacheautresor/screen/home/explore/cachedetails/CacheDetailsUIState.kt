package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.domain.uimodel.UICacheDetails

sealed interface CacheDetailsUIState {
    object Initialize : CacheDetailsUIState

    data class Error(val error: CTDomainError?) : CacheDetailsUIState

    data class Data(
        val uiCacheDetails: UICacheDetails,
        val distance: Double?,
    ) : CacheDetailsUIState
}
