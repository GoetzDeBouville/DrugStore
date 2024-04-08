package com.hellcorp.drugstore.core.data.network.dto

data class FieldDto(
    val typesId: Int,
    val flags: FlagsDto?,
    val group: Int?,
    val image: String?,
    val name: String?,
    val show: Int?,
    val type: String?,
    val value: String?
)