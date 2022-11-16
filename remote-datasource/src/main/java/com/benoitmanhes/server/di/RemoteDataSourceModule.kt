package com.benoitmanhes.server.di

import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.server.firestore.AuthRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.ExplorerRemoteDataSourceImpl
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
        firestore: FirebaseFirestore,
    ): AuthRemoteDataSource = AuthRemoteDataSourceImpl(auth, firestore)

    @Singleton
    @Provides
    fun provideExplorerRemoteDataSource(
        firestore: FirebaseFirestore,
    ): ExplorerRemoteDataSource = ExplorerRemoteDataSourceImpl(firestore)
}
