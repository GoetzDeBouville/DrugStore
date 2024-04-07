package com.hellcorp.drugstore.druginfo.presentation

import com.hellcorp.drugstore.core.ui.BaseViewModel
import com.hellcorp.drugstore.druginfo.domain.DrugInfoState
import com.hellcorp.drugstore.druginfo.domain.api.DrugInfoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DrugInfoViewModel @Inject constructor(interactor: DrugInfoInteractor) : BaseViewModel() {
    private val _state = MutableStateFlow<DrugInfoState>(DrugInfoState.Loading)
    val state: StateFlow<DrugInfoState>
        get() = _state

    
}