package com.hellcorp.drugstore.druglist.domain.api

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.data.network.response.DrugListSearchResponse
import kotlinx.coroutines.flow.Flow

interface DrugSearchRepository {
    fun getDrugList(expression: String): Flow<Resource<DrugListSearchResponse>>
}