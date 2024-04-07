package com.hellcorp.drugstore.core.network.dto

data class FlagsDto(
    val html: Int,
    val noImage: Int?,
    val noName: Int?,
    val noValue: Int?,
    val noWrap: Int?,
    val noWrapName: Int?,
    val system: Int?
)