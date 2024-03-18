package com.benoitmanhes.cacheautresor.navigation.root

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.common.extensions.appVersionCode
import com.benoitmanhes.cacheautresor.common.extensions.toTextSpec
import com.benoitmanhes.cacheautresor.navigation.root.holder.AppContentState
import com.benoitmanhes.domain.synchronization.SynchronizeAllUsersUseCase
import com.benoitmanhes.domain.synchronization.SynchronizeCacheUseCase
import com.benoitmanhes.domain.synchronization.SynchronizeUserUseCase
import com.benoitmanhes.domain.usecase.appcontrol.FetchAppControlUseCase
import com.benoitmanhes.domain.usecase.appcontrol.GetAppControlUseCase
import com.benoitmanhes.domain.usecase.authentication.IsAuthenticatedUseCase
import com.benoitmanhes.logger.CTLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

private val logger = CTLogger.get<AppContentViewModel>()

@HiltViewModel
class AppContentViewModel @Inject constructor(
    private val isAuthenticatedUseCase: IsAuthenticatedUseCase,
    private val getAppControlUseCase: GetAppControlUseCase,
    private val synchronizeCacheUseCase: SynchronizeCacheUseCase,
    private val synchronizeUserUseCase: SynchronizeUserUseCase,
    private val synchronizeAllUsersUseCase: SynchronizeAllUsersUseCase,
    private val fetchAppControlUseCase: FetchAppControlUseCase,
) : ViewModel() {

    private val _appContentState = MutableStateFlow<AppContentState>(AppContentState.Idle)
    val appContentState: StateFlow<AppContentState> get() = _appContentState.asStateFlow()

    fun initialize(context: Context, locale: String) {
        viewModelScope.launch(Dispatchers.Default) {
            combine(
                isAuthenticatedUseCase().distinctUntilChanged(),
                getAppControlUseCase(),
            ) { isAuthenticated, appControl ->

                when {
                    appControl != null && appControl.forceToUpgradeMinVersion > context.appVersionCode() -> {
                        AppContentState.ForceToUpgrade(appControl.forceToUpgradeMessage.toTextSpec(locale))
                    }

                    appControl != null && appControl.maintenanceActive -> {
                        AppContentState.Maintenance(appControl.maintenanceMessage.toTextSpec(locale))
                    }

                    isAuthenticated -> {
                        synchronizeCache()
                        synchronizeUser()
                        synchronizeAllUsers()
                        AppContentState.Authenticated
                    }

                    else -> AppContentState.UnAuthenticated
                }
            }.collect {
                _appContentState.value = it
            }
        }
    }

    private fun synchronizeCache() {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeCacheUseCase()
        }
    }

    private fun synchronizeUser() {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeUserUseCase()
        }
    }

    private fun synchronizeAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            synchronizeAllUsersUseCase()
        }
    }
}
