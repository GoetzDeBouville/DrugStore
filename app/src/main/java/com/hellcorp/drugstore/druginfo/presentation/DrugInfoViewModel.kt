package com.hellcorp.drugstore.druginfo.presentation

import androidx.lifecycle.viewModelScope
import com.hellcorp.drugstore.core.data.network.dto.Resource
import com.hellcorp.drugstore.core.ui.BaseViewModel
import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.domain.models.ErrorsStates
import com.hellcorp.drugstore.domain.models.NetworkErrors
import com.hellcorp.drugstore.druginfo.domain.DrugInfoState
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugInfoViewModel @Inject constructor(private val interactor: DrugInfoInteractor) :
    BaseViewModel() {
    private val _state = MutableStateFlow<DrugInfoState>(DrugInfoState.Loading)
    val state: StateFlow<DrugInfoState>
        get() = _state


    fun getDrugInfo(id: Int) {
        viewModelScope.launch {
            interactor.getDrugInfo(id).collect {
                handleResponse(it)
            }
        }
    }

    private fun handleResponse(result: Resource<Drug>) {
        when (result) {
            is Resource.Success -> {
                if (result.data == null) {
                    _state.value = DrugInfoState.Error(ErrorsStates.EMPTY)
                } else {
                    _state.value = DrugInfoState.Content(result.data)
                }
            }

            is Resource.Error -> {
                _state.value = DrugInfoState.Error(
                    when (result.error) {
                        NetworkErrors.NoInternet -> ErrorsStates.NO_INTERNET
                        else -> ErrorsStates.SERVER_ERROR
                    }
                )
            }
        }
    }
}