package com.hellcorp.drugstore.druglist.presentation

import androidx.lifecycle.viewModelScope
import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.ui.BaseViewModel
import com.hellcorp.drugstore.domain.models.DrugListSearchResult
import com.hellcorp.drugstore.domain.models.ErrorsStates
import com.hellcorp.drugstore.domain.models.NetworkErrors
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchInteractor
import com.hellcorp.drugstore.druglist.domain.models.DrugSearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugListViewModel @Inject constructor(private val interactor: DrugSearchInteractor) :
    BaseViewModel() {
    private val _state = MutableStateFlow<DrugSearchScreenState>(DrugSearchScreenState.Default)
    val state: StateFlow<DrugSearchScreenState>
        get() = _state

    fun getVacancies(expression: String) {
        if (expression.isNotEmpty()) {
            _state.value = DrugSearchScreenState.Loading
            viewModelScope.launch {
                interactor.getDrugList(expression).collect {
                    handleResponse(it)
                }
            }
        }
    }

    private fun handleResponse(result: Resource<DrugListSearchResult>) {
        when (result) {
            is Resource.Success -> {
                if (result.data?.drugList.isNullOrEmpty()) {
                    _state.value = DrugSearchScreenState.Error(ErrorsStates.EMPTY)
                } else {
                    _state.value = DrugSearchScreenState.Content(result.data!!.drugList)
                }
            }

            is Resource.Error -> {
                _state.value = DrugSearchScreenState.Error(
                    when (result.error) {
                        NetworkErrors.NoInternet -> ErrorsStates.NO_INTERNET
                        else -> ErrorsStates.SERVER_ERROR
                    }
                )
            }
        }
    }
}