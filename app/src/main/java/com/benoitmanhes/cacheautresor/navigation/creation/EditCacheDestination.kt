package com.benoitmanhes.cacheautresor.navigation.creation

import com.benoitmanhes.cacheautresor.navigation.CTDestination

sealed interface EditCacheDestination : CTDestination {
    data object AvailableFinalPlaces : EditCacheDestination {
        override val route: String = "available-final-places"
    }
}