package com.hellcorp.druginfo

import androidx.fragment.app.viewModels
import com.hellcorp.druginfo.databinding.FragmentDrugInfoBinding
import com.hellcorp.base.BaseFragment

class DrugInfoFragment :
    BaseFragment<FragmentDrugInfoBinding, DrugInfoViewModel>(FragmentDrugInfoBinding::inflate) {
    override val viewModel: DrugInfoViewModel by viewModels<DrugInfoViewModel>()
}