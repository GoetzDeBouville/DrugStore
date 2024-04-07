package com.hellcorp.drugstore.core.network.response

import com.hellcorp.drugstore.core.network.dto.DrugDto
import com.hellcorp.drugstore.core.network.dto.Response

data class DrugListSearchResponse(
    val drugList: List<DrugDto>
) : Response()
