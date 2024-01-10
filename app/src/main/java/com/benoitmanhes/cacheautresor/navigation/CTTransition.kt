package com.benoitmanhes.cacheautresor.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private typealias AnimatedTransitionProvider<T> = (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> T?)

fun NavGraphBuilder.ctComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: AnimatedTransitionProvider<EnterTransition>? = { CTTransition.defaultEnter },
    exitTransition: AnimatedTransitionProvider<ExitTransition>? = { CTTransition.defaultExit },
    popEnterTransition: AnimatedTransitionProvider<EnterTransition>? = { CTTransition.defaultPopEnter },
    popExitTransition: AnimatedTransitionProvider<ExitTransition>? = { CTTransition.defaultPopExit },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}

object CTTransition {

    private const val defaultTransitionDurationMillis: Int = 300
    private const val defaultPopDurationMillis: Int = 450
    private const val defaultScaleFactor: Float = 0.95f
    private const val defaultFadeFactor: Float = 0.5f
    private val DefaultScaleOrigin = TransformOrigin(1f, 0.5f)

    val defaultEnter: EnterTransition = slideInHorizontally(
        animationSpec = tween(durationMillis = defaultTransitionDurationMillis),
        initialOffsetX = { it }
    )

    val defaultExit: ExitTransition = scaleOut(
        animationSpec = tween(durationMillis = defaultTransitionDurationMillis),
        targetScale = defaultScaleFactor,
        transformOrigin = DefaultScaleOrigin,
    ) + fadeOut(targetAlpha = defaultFadeFactor)

    val defaultPopEnter: EnterTransition = scaleIn(
        animationSpec = tween(durationMillis = defaultPopDurationMillis),
        initialScale = defaultScaleFactor,
        transformOrigin = DefaultScaleOrigin,
    ) + fadeIn(initialAlpha = defaultFadeFactor)

    val defaultPopExit: ExitTransition = slideOutHorizontally(
        animationSpec = tween(durationMillis = defaultPopDurationMillis),
        targetOffsetX = { it }
    )
}
