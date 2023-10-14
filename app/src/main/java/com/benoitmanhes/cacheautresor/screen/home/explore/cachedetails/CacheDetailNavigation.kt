package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

sealed interface CacheDetailNavigation {
    data class EditNote(val cacheId: String) : CacheDetailNavigation
}
