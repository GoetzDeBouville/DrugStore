package com.hellcorp.drugstore.druglist.domain.models

import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.domain.models.ErrorsStates

sealed class DrugSearchScreenState {
    data object Loading : DrugSearchScreenState()
    data class Error(
        val error: ErrorsStates
    ) : DrugSearchScreenState()

    data class Content(
        val drugList: List<Drug>
    ) : DrugSearchScreenState()
}