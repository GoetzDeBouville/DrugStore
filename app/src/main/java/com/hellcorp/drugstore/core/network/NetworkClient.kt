package com.hellcorp.drugstore.core.network

import com.hellcorp.drugstore.core.network.dto.Response


interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}
