package com.hellcorp.drugstore.domain.models

data class Categories(
    val id: Int,
    val icon: String?,
    val image: String?,
    val name: String?,
    val iconUrl: String? = icon?.let { "$baseUrl$it" }
) {
    companion object {
        private const val baseUrl = "http://shans.d2.i-partner.ru"
    }
}
