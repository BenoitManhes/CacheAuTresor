package com.benoitmanhes.remote_datasource.di

import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.remote_datasource.firestore.AuthRemoteDataSourceImpl
import com.benoitmanhes.remote_datasource.firestore.ExplorerRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        auth: FirebaseAuth,
    ): AuthRemoteDataSource = AuthRemoteDataSourceImpl(auth)

    @Singleton
    @Provides
    fun provideExplorerRemoteDataSource(
        firestore: FirebaseFirestore,
    ): ExplorerRemoteDataSource = ExplorerRemoteDataSourceImpl(firestore)
}
