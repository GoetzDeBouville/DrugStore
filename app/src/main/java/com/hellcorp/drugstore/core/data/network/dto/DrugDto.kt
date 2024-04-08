package com.hellcorp.drugstore.core.data.network.dto

import com.hellcorp.drugstore.utils.Constants.Companion.BASE_URL

data class DrugDto(
    val id: Int,
    val categories: CategoriesDto?,
    val description: String?,
    val documentation: Any?,
    val gtin: String?,
    val image: String?,
    val name: String?,
    val fields: List<FieldDto>?,
    val imageUrl: String? = image?.let { "$BASE_URL$it" }
)
