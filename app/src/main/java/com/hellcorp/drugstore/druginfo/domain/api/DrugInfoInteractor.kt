package com.hellcorp.drugstore.druginfo.domain.api

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.domain.models.Drug
import kotlinx.coroutines.flow.Flow

interface DrugInfoInteractor {
    suspend fun getDrugInfo(id: Int): Flow<Resource<Drug>>
}