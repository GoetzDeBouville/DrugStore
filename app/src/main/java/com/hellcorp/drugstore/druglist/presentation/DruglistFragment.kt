package com.hellcorp.drugstore.druglist.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hellcorp.drugstore.core.ui.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDruglistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DruglistFragment :
    BaseFragment<FragmentDruglistBinding, DrugListViewModel>(FragmentDruglistBinding::inflate) {
    override val viewModel: DrugListViewModel by viewModels<DrugListViewModel>()

    override fun subscribe() {
        super.subscribe()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                Log.i("MyLog", "state = $state")
            }
        }
    }
}