package com.benoitmanhes.cacheautresor.screen.loading

import com.benoitmanhes.domain.utils.DomainConstants
import com.benoitmanhes.domain.utils.combineStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadingManager @Inject constructor() {

    private val _isLoading = MutableStateFlow(false)
    private val forceLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = combineStates(
            _isLoading.asStateFlow(),
            forceLoading.asStateFlow(),
        ) { (a, b) -> a || b }

    fun showLoading() {
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            forceLoading.emit(true)
            delay(DomainConstants.Loading.minLoadingDuration.inWholeMilliseconds)
            forceLoading.emit(false)
        }
    }

    fun hideLoading() {
        _isLoading.value = false
    }
}
