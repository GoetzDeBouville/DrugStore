package com.hellcorp.drugstore.domain.impl

import com.hellcorp.drugstore.core.data.network.dto.CategoriesDto
import com.hellcorp.drugstore.core.data.network.dto.DrugDto
import com.hellcorp.drugstore.core.data.network.dto.FieldDto
import com.hellcorp.drugstore.core.data.network.dto.FlagsDto
import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.data.network.response.DrugListSearchResponse
import com.hellcorp.drugstore.core.data.network.response.SingleDrugResponse
import com.hellcorp.drugstore.domain.api.Converter
import com.hellcorp.drugstore.domain.models.Categories
import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.domain.models.DrugListSearchResult
import com.hellcorp.drugstore.domain.models.Field
import com.hellcorp.drugstore.domain.models.Flags
import com.hellcorp.drugstore.domain.models.NetworkErrors

class ConverterImpl : Converter {
    override fun map(from: Resource<SingleDrugResponse>): Resource<Drug> {
        return when (from) {
            is Resource.Success -> {
                from.data?.let {
                    Resource.Success(map(it.drug))
                } ?: Resource.Error(error = NetworkErrors.UnknownError)
            }

            is Resource.Error -> {
                Resource.Error(from.error ?: NetworkErrors.UnknownError)
            }
        }
    }

    override fun map(from: Resource<DrugListSearchResponse>): Resource<DrugListSearchResult> {
        return when (from) {
            is Resource.Success -> {
                Resource.Success(
                    DrugListSearchResult(
                        drugList = from.data?.drugList?.map { map(it) } ?: emptyList()
                    )
                )
            }

            is Resource.Error -> {
                Resource.Error(from.error ?: NetworkErrors.UnknownError)
            }
        }
    }

    private fun map(from: DrugDto): Drug = Drug(
        id = from.id,
        categories = from.categories?.let { map(it) },
        description = from.description,
        documentation = from.documentation,
        gtin = from.gtin,
        image = from.image,
        name = from.name,
        fields = from.fields?.map { map(it) },
        imageUrl = from.imageUrl
    )

    private fun map(from: CategoriesDto): Categories = Categories(
        id = from.id,
        icon = from.icon,
        image = from.image,
        name = from.name,
        iconUrl = from.iconUrl
    )

    private fun map(from: FieldDto): Field = Field(
        typesId = from.typesId,
        flags = from.flags?.let { mapFlag(it) },
        group = from.group,
        image = from.image,
        name = from.name,
        show = from.show,
        type = from.type,
        value = from.value
    )

    private fun mapFlag(from: FlagsDto): Flags = Flags(
        html = from.html,
        noImage = from.noImage,
        noName = from.noName,
        noValue = from.noValue,
        noWrap = from.noWrap,
        noWrapName = from.noWrapName,
        system = from.system
    )
}