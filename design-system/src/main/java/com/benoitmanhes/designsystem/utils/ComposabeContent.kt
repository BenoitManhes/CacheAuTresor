package com.benoitmanhes.designsystem.utils

import androidx.compose.runtime.Composable

typealias ComposableContent = @Composable () -> Unit

typealias ScopedComposableContent<Scope> = @Composable Scope.() -> Unit
