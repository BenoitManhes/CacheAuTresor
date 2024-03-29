package com.benoitmanhes.cacheautresor.navigation.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

val slideVerticalEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    slideInVertically(
        initialOffsetY = { fullHeight -> (fullHeight * SlideOffsetRatio).toInt() },
        animationSpec = tween(SlideVerticalDurationMs),
    ) + fadeIn(animationSpec = tween(FadeDurationMs))
}

val slideVerticalExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    fadeOut(animationSpec = tween(FadeDurationMs, SlideDelayMs))
}

val slideVerticalPopEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    fadeIn(animationSpec = tween(FadeDurationMs, SlideDelayMs))
}

val slideVerticalPopExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    slideOutVertically(
        targetOffsetY = { fullHeight -> (fullHeight * SlideOffsetRatio).toInt() },
        animationSpec = tween(SlideVerticalDurationMs),
    ) + fadeOut(animationSpec = tween(FadeDurationMs))
}
