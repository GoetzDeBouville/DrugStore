package com.hellcorp.drugstore.di

import com.hellcorp.drugstore.domain.api.Converter
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoInteractor
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoRepository
import com.hellcorp.drugstore.druginfo.domain.impl.DrugInfoInteractorImpl
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchInteractor
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchRepository
import com.hellcorp.drugstore.druglist.domain.impl.DrugSearchInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class InteractorModule {
    @Provides
    fun provideDrugInfoInteractor(
        repository: DrugInfoRepository,
        converter: Converter
    ): DrugInfoInteractor =
        DrugInfoInteractorImpl(repository, converter)

    @Provides
    fun provideDrugSearchInteractor(
        repository: DrugSearchRepository,
        converter: Converter
    ): DrugSearchInteractor =
        DrugSearchInteractorImpl(repository, converter)
}