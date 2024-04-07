package com.hellcorp.drugstore.druglist.presentation

import com.hellcorp.drugstore.core.ui.BaseViewModel
import com.hellcorp.drugstore.druglist.domain.api.DrugSearchInteractor
import com.hellcorp.drugstore.druglist.domain.models.DrugSearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DrugListViewModel @Inject constructor(private val interactor: DrugSearchInteractor) :
    BaseViewModel() {
    private val _state = MutableStateFlow<DrugSearchScreenState>(DrugSearchScreenState.Loading)
    val state: StateFlow<DrugSearchScreenState>
        get() = _state
}