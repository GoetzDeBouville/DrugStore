package com.hellcorp.drugstore.domain.models

import com.hellcorp.drugstore.core.network.dto.Response

data class DrugListSearchResult(
    val drugList: List<Drug>
) : Response()
