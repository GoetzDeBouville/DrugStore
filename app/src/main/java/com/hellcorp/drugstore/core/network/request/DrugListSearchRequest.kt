package com.hellcorp.drugstore.core.network.request

data class DrugListSearchRequest(
    val searchExpression: String? = null,
)