package com.hellcorp.drugstore.domain.models

data class Field(
    val typesId: Int,
    val flags: Flags?,
    val group: Int?,
    val image: String?,
    val name: String?,
    val show: Int?,
    val type: String?,
    val value: String?
)