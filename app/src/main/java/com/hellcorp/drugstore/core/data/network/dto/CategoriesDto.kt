package com.hellcorp.drugstore.core.data.network.dto

import com.hellcorp.drugstore.utils.Constants.Companion.BASE_URL

data class CategoriesDto(
    val id: Int,
    val icon: String?,
    val image: String?,
    val name: String?,
    val iconUrl: String? = icon?.let { "$BASE_URL$it" }
)
