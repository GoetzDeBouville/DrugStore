package com.hellcorp.drugstore.druginfo.domain

import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.domain.models.ErrorsStates

sealed class DrugInfoState {
    data object Loading : DrugInfoState()
    data class Error(
        val error: ErrorsStates
    ) : DrugInfoState()

    data class Content(
        val drug: Drug
    ) : DrugInfoState()
}