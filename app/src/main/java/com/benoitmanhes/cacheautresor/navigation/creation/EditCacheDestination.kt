package com.benoitmanhes.cacheautresor.navigation.creation

import android.net.Uri
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

    data object PickNameDraftCache : EditCacheDestination, CTSingleArgDestination {
        override val arg: String = "draftCacheId"
        override val path: String = "pick-name-draft-cache"
    }

    data object PickTypeDraftCache : EditCacheDestination, CTSingleArgDestination {
        override val arg: String = "draftCacheId"
        override val path: String = "pick-type-draft-cache"
    }

    data object PickInitCoordinatesDraftCache : EditCacheDestination, CTSingleArgDestination {
        override val arg: String = "draftCacheId"
        override val path: String = "pick-init-coordinates-draft-cache"
    }

    data object EditDraftStep : EditCacheDestination {
        const val draftCacheIdArg: String = "draftCacheId"
        const val draftStepIdArg: String = "draftStepId"
        private const val path: String = "edit-draft-step"
        override val route: String get() = "$path?$draftCacheIdArg={$draftCacheIdArg}&$draftStepIdArg={$draftStepIdArg}"
        fun getRoute(draftCacheId: String, draftStepId: String): String =
            Uri.Builder().apply {
                path(path)
                appendQueryParameter(draftCacheIdArg, draftCacheId)
                appendQueryParameter(draftStepIdArg, draftStepId)
            }.build().toString()
    }

    data object PickStepCoordinates : EditCacheDestination {
        const val draftCacheIdArg: String = "draftCacheId"
        const val draftStepIdArg: String = "draftStepId"
        private const val path: String = "pick-step-coordinates"
        override val route: String get() = "$path?$draftCacheIdArg={$draftCacheIdArg}&$draftStepIdArg={$draftStepIdArg}"
        fun getRoute(draftCacheId: String, draftStepId: String): String =
            Uri.Builder().apply {
                path(path)
                appendQueryParameter(draftCacheIdArg, draftCacheId)
                appendQueryParameter(draftStepIdArg, draftStepId)
            }.build().toString()
    }

    data object EditInstructions : EditCacheDestination {
        const val draftCacheIdArg: String = "draftCacheId"
        const val draftStepIdArg: String = "draftStepId"
        private const val path: String = "edit-instructions"
        override val route: String get() = "$path?$draftCacheIdArg={$draftCacheIdArg}&$draftStepIdArg={$draftStepIdArg}"
        fun getRoute(draftCacheId: String, draftStepId: String): String =
            Uri.Builder().apply {
                path(path)
                appendQueryParameter(draftCacheIdArg, draftCacheId)
                appendQueryParameter(draftStepIdArg, draftStepId)
            }.build().toString()
    }

    data object PickStepClue : EditCacheDestination {
        const val draftCacheIdArg: String = "draftCacheId"
        const val draftStepIdArg: String = "draftStepId"
        private const val path: String = "pick-step-clue"
        override val route: String get() = "$path?$draftCacheIdArg={$draftCacheIdArg}&$draftStepIdArg={$draftStepIdArg}"
        fun getRoute(draftCacheId: String, draftStepId: String): String =
            Uri.Builder().apply {
                path(path)
                appendQueryParameter(draftCacheIdArg, draftCacheId)
                appendQueryParameter(draftStepIdArg, draftStepId)
            }.build().toString()
    }

    data object PickStepValidationCode : EditCacheDestination {
        const val draftCacheIdArg: String = "draftCacheId"
        const val draftStepIdArg: String = "draftStepId"
        private const val path: String = "pick-step-validation-code"
        override val route: String get() = "$path?$draftCacheIdArg={$draftCacheIdArg}&$draftStepIdArg={$draftStepIdArg}"
        fun getRoute(draftCacheId: String, draftStepId: String): String =
            Uri.Builder().apply {
                path(path)
                appendQueryParameter(draftCacheIdArg, draftCacheId)
                appendQueryParameter(draftStepIdArg, draftStepId)
            }.build().toString()
    }
}
