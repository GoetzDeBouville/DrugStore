package com.hellcorp.drugstore.core.data.network.dto

data class DrugDto(
    val id: Int,
    val categories: CategoriesDto?,
    val description: String?,
    val documentation: Any?,
    val gtin: String?,
    val image: String?,
    val name: String?,
    val fields: List<FieldDto>?
)
