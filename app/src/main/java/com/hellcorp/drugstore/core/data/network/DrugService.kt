package com.hellcorp.drugstore.core.data.network

import com.hellcorp.drugstore.core.data.network.dto.DrugDto
import com.hellcorp.drugstore.core.data.network.response.DrugListSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrugService {
    @GET("/api/ppp/index/")
    fun getDrugList(
        @Query("id") categoryId: Int? = null,
        @Query("search") searchExpression: String?,
        @Query("crop_id") cropId: Int? = null,
        @Query("harmful_object_id") harmfulObjectId: Int? = null,
        @Query("ingredient_id") ingredientId: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): DrugListSearchResponse

    @GET("/api/ppp/item/")
    fun getDrugDetails(
        @Query("id") drugId: Int
    ): DrugDto
}