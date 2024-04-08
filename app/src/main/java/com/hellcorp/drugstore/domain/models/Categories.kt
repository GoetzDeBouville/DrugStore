package com.hellcorp.drugstore.domain.models

import com.hellcorp.drugstore.utils.Constants

data class Categories(
    val id: Int,
    val icon: String?,
    val image: String?,
    val name: String?,
    val iconUrl: String? = icon?.let { "${Constants.BASE_URL}$it" }
)
