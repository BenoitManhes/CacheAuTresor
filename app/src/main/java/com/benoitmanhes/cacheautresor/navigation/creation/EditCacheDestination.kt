package com.benoitmanhes.cacheautresor.navigation.creation

import com.benoitmanhes.cacheautresor.navigation.CTDestination
import com.benoitmanhes.cacheautresor.navigation.CTSingleArgDestination

sealed interface EditCacheDestination : CTDestination {
    data object AvailableFinalPlaces : EditCacheDestination {
        override val route: String = "available-final-places"
    }

    data object EditDraftCache : EditCacheDestination, CTSingleArgDestination {
        override val arg: String = "draftCacheId"
        override val path: String = "edit-cache"
    }
}
