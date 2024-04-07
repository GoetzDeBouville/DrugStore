package com.hellcorp.drugstore.druglist.domain.api

import com.hellcorp.drugstore.core.network.dto.Resource
import com.hellcorp.drugstore.core.network.response.DrugListSearchResponse
import com.hellcorp.drugstore.domain.models.DrugListSearchResult

interface DrugSearchConverter {
    fun mapSearchResponse(from : Resource<DrugListSearchResponse>) : Resource<DrugListSearchResult>
}