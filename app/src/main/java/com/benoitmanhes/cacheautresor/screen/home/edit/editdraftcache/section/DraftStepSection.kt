package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable.CrewStepsCardPlaceHolder
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable.CrewStepsCardState
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.extensions.thenIf
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.google.accompanist.pager.HorizontalPagerIndicator

private fun classicalDraftStepSection(
    scope: LazyListScope,
    section: DraftStepSectionState.Classical,
) {
    section.finalStep.lazyItem(scope)
}

private fun mysteryDraftStepSection(
    scope: LazyListScope,
    section: DraftStepSectionState.Mystery,
) {
    section.enigmaStep.lazyItem(scope)
    section.finalStep.lazyItem(scope)
}

private fun pisteDraftStepSection(
    scope: LazyListScope,
    section: DraftStepSectionState.Piste,
) {
    section.intermediarySteps.map { it.lazyItem(scope) }
    section.addStep?.let {
        it.item(
            scope = scope,
            modifier = Modifier
                .fillMaxWidth()
                .composed { padding(horizontal = CTTheme.spacing.large) },
        )
    }
    section.finalStep.lazyItem(scope)
}

@OptIn(ExperimentalFoundationApi::class)
private fun coopDraftStepSection(
    scope: LazyListScope,
    section: DraftStepSectionState.Coop,
) {
    scope.item(
        key = "pager-coop",
        contentType = "pager-coop",
    ) {
        val density = LocalDensity.current
        var lastCardHeight by remember { mutableStateOf(AppDimens.EditCache.crewAddMemberPlaceHolderHeight) }
        val pagerState = rememberPagerState(
            pageCount = { section.crewStepsCards.size + 1 },
            initialPage = 0,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AnimatedVisibility(visible = pagerState.pageCount > 1) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = pagerState.pageCount,
                    activeColor = CTTheme.color.primary,
                    inactiveColor = CTTheme.color.strokeDivider,
                )
            }

            HorizontalPager(
                state = pagerState,
                pageSpacing = CTTheme.spacing.medium,
                contentPadding = PaddingValues(horizontal = CTTheme.spacing.large),
                beyondBoundsPageCount = 1,
                pageSize = PageSize.Fill,
                verticalAlignment = Alignment.Top,
                pageContent = { page ->
                    if (page < section.crewStepsCards.size) {
                        section.crewStepsCards.getOrNull(page)?.Content(
                            modifier = Modifier
                                .thenIf(page == section.crewStepsCards.size - 1) {
                                    onGloballyPositioned {
                                        with(density) {
                                            lastCardHeight = it.size.height.toDp()
                                        }
                                    }
                                }
                        )
                    } else {
                        CrewStepsCardPlaceHolder(
                            modifier = Modifier.height(lastCardHeight),
                            onClick = section.addCrewMember,
                        )
                    }
                }
            )
        }
    }
    section.finalStep.lazyItem(scope)
}

@Immutable
sealed interface DraftStepSectionState {
    val finalStep: MapRowPickerState

    data class Classical(
        override val finalStep: MapRowPickerState,
    ) : DraftStepSectionState

    data class Mystery(
        val enigmaStep: MapRowPickerState,
        override val finalStep: MapRowPickerState,
    ) : DraftStepSectionState

    data class Piste(
        val intermediarySteps: List<MapRowPickerState>,
        val addStep: PrimaryButtonState?,
        override val finalStep: MapRowPickerState,
    ) : DraftStepSectionState

    data class Coop(
        val crewStepsCards: List<CrewStepsCardState>,
        override val finalStep: MapRowPickerState,
        val addCrewMember: () -> Unit,
    ) : DraftStepSectionState

    fun lazyItem(scope: LazyListScope) {
        when (this) {
            is Classical -> classicalDraftStepSection(scope, this)
            is Mystery -> mysteryDraftStepSection(scope, this)
            is Piste -> pisteDraftStepSection(scope, this)
            is Coop -> coopDraftStepSection(scope, this)
        }
    }
}
