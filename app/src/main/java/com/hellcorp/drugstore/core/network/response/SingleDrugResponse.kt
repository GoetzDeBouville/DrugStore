package com.hellcorp.drugstore.core.network.response

import com.hellcorp.drugstore.core.network.dto.DrugDto
import com.hellcorp.drugstore.core.network.dto.Response

data class SingleDrugResponse(
    val drug: DrugDto
) : Response()
