package com.hellcorp.drugstore.druginfo.presentation

import androidx.fragment.app.viewModels
import com.hellcorp.drugstore.core.ui.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDrugInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrugInfoFragment :
    BaseFragment<FragmentDrugInfoBinding, DrugInfoViewModel>(FragmentDrugInfoBinding::inflate) {
    override val viewModel: DrugInfoViewModel by viewModels<DrugInfoViewModel>()
}