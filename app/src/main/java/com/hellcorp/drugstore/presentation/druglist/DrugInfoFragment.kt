package com.hellcorp.drugstore.presentation.druglist

import androidx.fragment.app.viewModels
import com.hellcorp.drugstore.base.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDrugInfoBinding

class DrugInfoFragment :
    BaseFragment<FragmentDrugInfoBinding, DrugInfoViewModel>(FragmentDrugInfoBinding::inflate) {
    override val viewModel: DrugInfoViewModel by viewModels<DrugInfoViewModel>()
}