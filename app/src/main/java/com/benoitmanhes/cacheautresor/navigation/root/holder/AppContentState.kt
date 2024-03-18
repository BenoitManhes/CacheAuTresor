package com.benoitmanhes.cacheautresor.navigation.root.holder

import com.benoitmanhes.common.compose.text.TextSpec
import javax.annotation.concurrent.Immutable

sealed interface AppContentState {

    data object Idle : AppContentState

    @Immutable
    data class Maintenance(val message: TextSpec) : AppContentState

    @Immutable
    data class ForceToUpgrade(val message: TextSpec) : AppContentState

    data object Authenticated : AppContentState

    data object UnAuthenticated : AppContentState
}
