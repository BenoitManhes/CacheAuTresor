package com.benoitmanhes.cacheautresor.navigation.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

val fadeInEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    fadeIn(animationSpec = tween(FadeDurationMs))
}

val fadeOutExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    fadeOut(animationSpec = tween(FadeDurationMs))
}

val fadeInPopEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    fadeIn(animationSpec = tween(FadeDurationMs))
}

val fadeOutPopExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    fadeOut(animationSpec = tween(FadeDurationMs))
}