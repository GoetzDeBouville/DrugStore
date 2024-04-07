package com.hellcorp.drugstore.domain.api

import com.hellcorp.drugstore.core.network.dto.Resource
import com.hellcorp.drugstore.core.network.response.DrugListSearchResponse
import com.hellcorp.drugstore.core.network.response.SingleDrugResponse
import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.domain.models.DrugListSearchResult

interface Converter {
    fun map(from : Resource<SingleDrugResponse>) : Resource<Drug>
    fun map(from : Resource<DrugListSearchResponse>) : Resource<DrugListSearchResult>
}