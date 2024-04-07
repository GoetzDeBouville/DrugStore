package com.hellcorp.drugstore.druginfo.domain.impl

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.domain.api.Converter
import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoInteractor
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DrugInfoInteractorImpl(
    private val repository: DrugInfoRepository,
    private val converter: Converter
) : DrugInfoInteractor {
    override suspend fun getDrugInfo(id: Int): Flow<Resource<Drug>> =
        repository.getDrugInfo(id).map {
            converter.map(it)
        }
}