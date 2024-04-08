package com.hellcorp.drugstore.domain.models

import com.hellcorp.drugstore.utils.Constants

data class Drug(
    val id: Int,
    val categories: Categories?,
    val description: String?,
    val documentation: Any?,
    val gtin: String?,
    val name: String?,
    val fields: List<Field>?,
    val imageUrl: String?
)
