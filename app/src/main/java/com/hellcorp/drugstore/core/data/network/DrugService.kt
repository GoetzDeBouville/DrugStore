package com.hellcorp.drugstore.core.data.network

import com.hellcorp.drugstore.core.data.network.dto.DrugDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DrugService {
    @GET("/api/ppp/index/")
    suspend fun getDrugList(
        @Query("search") searchExpression: String?,
        @Query("id") categoryId: Int? = null,
        @Query("crop_id") cropId: Int? = null,
        @Query("harmful_object_id") harmfulObjectId: Int? = null,
        @Query("ingredient_id") ingredientId: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): List<DrugDto>

    @GET("/api/ppp/item/")
    suspend fun getDrugDetails(
        @Query("id") drugId: Int
    ): DrugDto
}