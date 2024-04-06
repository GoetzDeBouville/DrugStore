package com.hellcorp.drugstore.presentation.druginfo

import androidx.fragment.app.viewModels
import com.hellcorp.drugstore.base.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDruglistBinding

class DruglistFragment :
    BaseFragment<FragmentDruglistBinding, DrugListViewModel>(FragmentDruglistBinding::inflate) {
    override val viewModel: DrugListViewModel by viewModels<DrugListViewModel>()
}