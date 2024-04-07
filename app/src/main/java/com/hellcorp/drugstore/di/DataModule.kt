package com.hellcorp.drugstore.di

import android.content.Context
import com.hellcorp.drugstore.core.ConverterImpl
import com.hellcorp.drugstore.core.data.network.DrugService
import com.hellcorp.drugstore.core.data.network.NetworkClient
import com.hellcorp.drugstore.core.data.network.RetrofitNetworkClient
import com.hellcorp.drugstore.domain.api.Converter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDrugService(): DrugService = Retrofit.Builder()
        .baseUrl("http://shans.d2.i-partner.ru")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DrugService::class.java)

    @Provides
    @Singleton
    fun provideNetworkClient(
        service: DrugService,
        @ApplicationContext context: Context
    ): NetworkClient =
        RetrofitNetworkClient(service, context)

    @Provides
    @Singleton
    fun provideConverter() : Converter = ConverterImpl()
}