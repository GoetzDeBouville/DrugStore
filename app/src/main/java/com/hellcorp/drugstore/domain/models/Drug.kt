package com.hellcorp.drugstore.domain.models

data class Drug(
    val id: Int,
    val categories: Categories?,
    val description: String?,
    val documentation: Any?,
    val gtin: String?,
    val image: String?,
    val name: String?,
    val fields: List<Field>?,
    val imageUrl: String? = image?.let { "$baseUrl$it" }
) {
    companion object {
        private const val baseUrl = "http://shans.d2.i-partner.ru"
    }
}
