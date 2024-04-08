package com.hellcorp.drugstore.druglist.domain.api

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.data.network.response.DrugListSearchResponse
import com.hellcorp.drugstore.domain.models.DrugListSearchResult
import kotlinx.coroutines.flow.Flow

interface DrugSearchInteractor {
    suspend fun getDrugList(expression: String): Flow<Resource<DrugListSearchResult>>
}