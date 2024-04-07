package com.hellcorp.drugstore.domain.models

data class Flags(
    val html: Int,
    val noImage: Int?,
    val noName: Int?,
    val noValue: Int?,
    val noWrap: Int?,
    val noWrapName: Int?,
    val system: Int?
)