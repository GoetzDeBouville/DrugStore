package com.hellcorp.drugstore.druginfo.domain.api

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.data.network.response.SingleDrugResponse
import kotlinx.coroutines.flow.Flow

interface DrugInfoRepository {
    fun getDrugInfo(id: Int): Flow<Resource<SingleDrugResponse>>
}