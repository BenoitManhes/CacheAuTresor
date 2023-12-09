package com.benoitmanhes.cacheautresor.screen.modalbottomsheet

import androidx.annotation.FloatRange

sealed interface ModalBottomSheetOption {
    object Lock : ModalBottomSheetOption
    data class MaxHeightRatio(
        @FloatRange(0.0, 1.0) val ratio: Float,
    ) : ModalBottomSheetOption
}
