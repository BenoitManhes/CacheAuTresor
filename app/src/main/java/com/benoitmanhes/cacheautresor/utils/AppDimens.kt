package com.benoitmanhes.cacheautresor.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object AppDimens {
    object CacheDetail {
        val bottomSheetHeaderHeight: Dp = 64.dp
        val instructionImageMaxHeight: Dp = 380.dp
    }

    object EditCache {
        val crewAddMemberPlaceHolderHeight: Dp = 120.dp
        val editInstructionsZoneTextfieldMinHeight = 200.dp
    }

    object EmptyScreen {
        val imageSize: Dp = LockScreen.imageSize
    }

    object Map {
        val smallMapSize: Dp = 48.dp
    }

    object MyCaches {
        val progressSize: Dp = 36.dp
        val handDrawArrowSize: DpSize = DpSize(width = 55.dp, height = 47.dp)
    }

    object LockScreen {
        val imageSize: Dp = 180.dp
    }

    object Rank {
        val rankTextWidth: Dp = 30.dp
        val explorerImageSize: Dp = 30.dp
    }

    object Profile {
        val explorerImageSize: Dp = 150.dp
    }
}
