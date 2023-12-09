package com.benoitmanhes.cacheautresor.screen.home.news

import com.benoitmanhes.cacheautresor.screen.home.news.section.EliteCardState

data class NewsViewModelState(
    val eliteCards: List<EliteCardState>? = null,
)
