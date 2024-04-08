package com.hellcorp.drugstore.core

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
import com.hellcorp.drugstore.utils.Constants.Companion.BASE_URL

class ConverterImpl : Converter {
    override fun mapDrugInfo(from: Resource<SingleDrugResponse>): Resource<Drug> {
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

    override fun mapDrugList(from: Resource<DrugListSearchResponse>): Resource<DrugListSearchResult> {
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
        name = from.name,
        fields = from.fields?.map { map(it) },
        imageUrl = from.image?.let { "$BASE_URL${from.image}" }
    )

    private fun map(from: CategoriesDto): Categories = Categories(
        id = from.id,
        image = from.image,
        name = from.name,
        iconUrl = from.icon?.let { "$BASE_URL${from.icon}" }
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