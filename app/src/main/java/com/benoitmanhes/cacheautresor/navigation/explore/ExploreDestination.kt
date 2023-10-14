package com.benoitmanhes.cacheautresor.navigation.explore

import com.benoitmanhes.cacheautresor.navigation.CTDestination

sealed interface ExploreDestination : CTDestination {

    object CacheDetails : ExploreDestination {
        const val cacheDetailsArgument: String = "cacheId"
        override val route: String = "cache-details/{$cacheDetailsArgument}"
        fun getRoute(cacheId: String): String = route.replace("{$cacheDetailsArgument}", cacheId)
    }

    object EditNote : ExploreDestination {
        const val editNoteArgument: String = "cacheId"
        override val route: String = "edit-note/{$editNoteArgument}"
        fun getRoute(cacheId: String): String = route.replace("{$editNoteArgument}", cacheId)
    }
}
