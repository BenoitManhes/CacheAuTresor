package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.AppControlLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.AppControlRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.AppControlRepository
import com.benoitmanhes.domain.model.AppControl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppControlRepositoryImpl @Inject constructor(
    private val remoteDataSource: AppControlRemoteDataSource,
    private val localDataSource: AppControlLocalDataSource,
) : AppControlRepository {

    override suspend fun fetchAppControl() {
        val remoteValue = remoteDataSource.fetchAppControl()
        remoteValue?.let { localDataSource.saveAppControl(it) }
    }

    override fun getAppControlAsFlow(): Flow<AppControl?> =
        localDataSource.getAppControlFlow()
}
