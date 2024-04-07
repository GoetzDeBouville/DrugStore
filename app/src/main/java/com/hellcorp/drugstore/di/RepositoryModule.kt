package com.hellcorp.drugstore.di

import com.hellcorp.drugstore.core.data.DrugRepositoryImpl
import com.hellcorp.drugstore.core.data.network.NetworkClient
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoRepository
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDrugSearchRepository(client: NetworkClient) : DrugSearchRepository =
        DrugRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideDrugInfoRepository(client: NetworkClient) : DrugInfoRepository =
        DrugRepositoryImpl(client)
}