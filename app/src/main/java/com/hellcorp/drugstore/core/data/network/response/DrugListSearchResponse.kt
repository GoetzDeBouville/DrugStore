package com.hellcorp.drugstore.core.data.network.response

import com.hellcorp.drugstore.core.data.network.dto.DrugDto
import com.hellcorp.drugstore.core.data.network.dto.Response

data class DrugListSearchResponse(
    val drugList: List<DrugDto>
) : Response()
