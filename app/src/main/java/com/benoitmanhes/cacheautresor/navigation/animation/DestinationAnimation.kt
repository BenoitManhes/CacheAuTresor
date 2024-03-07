package com.benoitmanhes.cacheautresor.navigation.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import com.benoitmanhes.cacheautresor.navigation.authenticated.HomeRootDestination
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.navigation.home.HomeDestination

enum class DestinationAnimation(
    val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition),
    val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition),
    val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition),
    val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition),
) {
    STATIC(
        fadeInEnterTransition,
        fadeOutExitTransition,
        fadeInPopEnterTransition,
        fadeOutPopExitTransition,
    ),

    VERTICAL(
        slideVerticalEnterTransition,
        slideVerticalExitTransition,
        slideVerticalPopEnterTransition,
        slideVerticalPopExitTransition,
    ),

    HORIZONTAL(
        slideHorizontalEnterTransition,
        slideHorizontalExitTransition,
        slideHorizontalPopEnterTransition,
        slideHorizontalPopExitTransition,
    ),
    ;

    companion object {
        private val destinationVerticalAnimation: List<String> = listOf(
            EditCacheDestination.AvailableFinalPlaces.route,
            EditCacheDestination.CreationSuccess.route,
        )

        private val destinationStaticAnimation: List<String> = listOf(
            EditCacheDestination.EditDraftCache.route,
            HomeRootDestination.route,
            HomeDestination.Create.route,
            HomeDestination.News.route,
            HomeDestination.Explore.route,
            HomeDestination.Encyclopedia.route,
            HomeDestination.Profile.route,
        )

        fun getEnterTransitionFromRoute(
            route: String?,
        ): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) {
            return when {
                destinationVerticalAnimation.contains(route) -> VERTICAL.enterTransition
                destinationStaticAnimation.contains(route) -> STATIC.enterTransition
                else -> HORIZONTAL.enterTransition
            }
        }

        fun getExitTransitionFromRoute(
            route: String?,
        ): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) {
            return when {
                destinationVerticalAnimation.contains(route) -> VERTICAL.exitTransition
                destinationStaticAnimation.contains(route) -> STATIC.exitTransition
                else -> HORIZONTAL.exitTransition
            }
        }

        fun getPopEnterTransitionFromRoute(
            route: String?,
        ): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) {
            return when {
                destinationVerticalAnimation.contains(route) -> VERTICAL.popEnterTransition
                destinationStaticAnimation.contains(route) -> STATIC.popEnterTransition
                else -> HORIZONTAL.popEnterTransition
            }
        }

        fun getPopExitTransitionFromRoute(
            route: String?,
        ): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) {
            return when {
                destinationVerticalAnimation.contains(route) -> VERTICAL.popExitTransition
                destinationStaticAnimation.contains(route) -> STATIC.popExitTransition
                else -> HORIZONTAL.popExitTransition
            }
        }
    }
}
