package com.hellcorp.drugstore.core.data.network

import com.hellcorp.drugstore.core.data.network.dto.Response


interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}
