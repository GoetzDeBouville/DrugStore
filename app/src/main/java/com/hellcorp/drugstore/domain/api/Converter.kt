package com.hellcorp.drugstore.domain.api

import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.data.network.response.DrugListSearchResponse
import com.hellcorp.drugstore.core.data.network.response.SingleDrugResponse
import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.domain.models.DrugListSearchResult

interface Converter {
    fun mapDrugInfo(from : Resource<SingleDrugResponse>) : Resource<Drug>
    fun mapDrugList(from : Resource<DrugListSearchResponse>) : Resource<DrugListSearchResult>
}