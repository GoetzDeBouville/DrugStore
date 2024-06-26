package com.hellcorp.drugstore.domain.models

import com.hellcorp.drugstore.core.data.network.dto.Response

data class DrugListSearchResult(
    val drugList: List<Drug>
) : Response()
