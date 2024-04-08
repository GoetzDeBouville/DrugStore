package com.hellcorp.drugstore.druglist.domain.impl

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.domain.api.Converter
import com.hellcorp.drugstore.domain.models.DrugListSearchResult
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchInteractor
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DrugSearchInteractorImpl(
    private val repository: DrugSearchRepository,
    private val converter: Converter
) : DrugSearchInteractor {
    override suspend fun getDrugList(expression: String): Flow<Resource<DrugListSearchResult>> =
        repository.getDrugList(expression).map {
            converter.mapDrugList(it)
        }
}