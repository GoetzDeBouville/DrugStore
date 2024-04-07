package com.hellcorp.drugstore.druglist.presentation

import androidx.fragment.app.viewModels
import com.hellcorp.drugstore.core.ui.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDruglistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DruglistFragment :
    BaseFragment<FragmentDruglistBinding, DrugListViewModel>(FragmentDruglistBinding::inflate) {
    override val viewModel: DrugListViewModel by viewModels<DrugListViewModel>()
}