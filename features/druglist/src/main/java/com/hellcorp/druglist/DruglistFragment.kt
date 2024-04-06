package com.hellcorp.druglist

import androidx.fragment.app.viewModels
import com.hellcorp.druglist.databinding.FragmentDruglistBinding
import com.hellcorp.base.BaseFragment

class DruglistFragment :
    BaseFragment<FragmentDruglistBinding, DrugListViewModel>(FragmentDruglistBinding::inflate) {
    override val viewModel: DrugListViewModel by viewModels<DrugListViewModel>()
}