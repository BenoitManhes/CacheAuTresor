package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.designsystem.molecule.sticker.CTStickerIconState
import com.benoitmanhes.domain.model.DraftCache

internal fun EditCacheViewModel.getTypeSticker(type: DraftCache.Type): CTStickerIconState =
    CTStickerIconState(
        icon = type.toCacheType().getIcon(),
        text = type.toCacheType().getTypeText(),
    )
