package com.benoitmanhes.cacheautresor.screen.home.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.RankModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.row.RankRowState
import com.benoitmanhes.cacheautresor.screen.home.news.section.EliteCardState
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.explorer.GetBestCartographersUseCase
import com.benoitmanhes.domain.usecase.explorer.GetBestExplorersUseCase
import com.benoitmanhes.domain.usecase.explorer.GetMyExplorerUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.benoitmanhes.cacheautresor.screen.home.news.section.EliteCardState.Explorer as UIExplorer

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val modalBottomSheetManager: ModalBottomSheetManager,
    getMyExplorerUseCase: GetMyExplorerUseCase,
    getBestCartographersUseCase: GetBestCartographersUseCase,
    getBestExplorersUseCase: GetBestExplorersUseCase,
) : ViewModel() {

    val uiState: StateFlow<NewsViewModelState> = combine(
        getBestExplorersUseCase(),
        getBestCartographersUseCase(),
        getMyExplorerUseCase(),
    ) { eliteExplorers, eliteCartographers, myExplorer ->
        val myExplorerId = myExplorer.data?.explorerId.orEmpty()
        NewsViewModelState(
            eliteCards = listOfNotNull(
                EliteCardState(
                    explorers = eliteExplorers
                        .take(DomainConstants.News.numberExplorerElite)
                        .map { it.mapToUIExplorer() },
                    headerIcon = { CTTheme.icon.Crown },
                    headerTitle = TextSpec.Resources(R.string.news_eliteExplorersCard_title),
                    colorTheme = CTColorTheme.Explore,
                    onClickRank = {
                        modalBottomSheetManager.showModal(
                            RankModalBottomSheet(
                                title = TextSpec.Resources(R.string.rankModal_exploraters_title),
                                explorerRanks = eliteExplorers.mapIndexed { index, explorer ->
                                    explorer.mapToRankRow(
                                        myExplorerId = myExplorerId,
                                        colorTheme = CTColorTheme.Explore,
                                        rank = index,
                                        points = explorer.cachesFoundMap.values.sum(),
                                    )
                                }
                            )
                        )
                    },
                ).takeUnless { eliteExplorers.isEmpty() },
                EliteCardState(
                    explorers = eliteCartographers
                        .take(DomainConstants.News.numberExplorerElite)
                        .map { it.mapToUICartographer() },
                    headerIcon = { CTTheme.icon.Ensign },
                    headerTitle = TextSpec.Resources(R.string.news_eliteCartographersCard_title),
                    colorTheme = CTColorTheme.Cartography,
                    onClickRank = {
                        modalBottomSheetManager.showModal(
                            RankModalBottomSheet(
                                title = TextSpec.Resources(R.string.rankModal_cartographers_title),
                                explorerRanks = eliteCartographers.mapIndexed { index, explorer ->
                                    explorer.mapToRankRow(
                                        myExplorerId = myExplorerId,
                                        colorTheme = CTColorTheme.Cartography,
                                        rank = index,
                                        points = explorer.cachesMap.values.sum(),
                                    )
                                }
                            )
                        )
                    },
                ).takeUnless { eliteCartographers.isEmpty() },
            ).takeUnless { it.isEmpty() },
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(StopTimeOut),
        initialValue = NewsViewModelState(),
    )

    private fun Explorer.mapToUIExplorer(): UIExplorer = UIExplorer(
        image = ImageSpec.ResImage(R.drawable.explorer),
        name = name.textSpec(),
        points = cachesFoundMap.values.sum().toString().textSpec(),
    )

    private fun Explorer.mapToUICartographer(): UIExplorer = UIExplorer(
        image = ImageSpec.ResImage(R.drawable.explorer),
        name = name.textSpec(),
        points = cachesMap.values.sum().toString().textSpec(),
    )

    private fun Explorer.mapToRankRow(
        myExplorerId: String,
        colorTheme: CTColorTheme,
        rank: Int,
        points: Int,
    ): RankRowState {
        val isMe = explorerId == myExplorerId
        return RankRowState(
            rank = (rank + 1).toString().textSpec(),
            explorerImage = ImageSpec.ResImage(R.drawable.explorer),
            explorerName = name.textSpec(),
            points = points.toString().textSpec(),
            colorTheme = colorTheme,
            highlight = isMe,
        )
    }

    companion object {
        private const val StopTimeOut: Long = 5000
    }
}
