package com.hellcorp.drugstore.druglist.presentation

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hellcorp.drugstore.R
import com.hellcorp.drugstore.core.ui.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDruglistBinding
import com.hellcorp.drugstore.druglist.domain.models.DrugSearchScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DruglistFragment :
    BaseFragment<FragmentDruglistBinding, DrugListViewModel>(FragmentDruglistBinding::inflate) {
    override val viewModel: DrugListViewModel by activityViewModels<DrugListViewModel>()

    private val drugListAdapter = DrugListAdapter(
        clickListener = {
            val bundle = Bundle().apply {
                putInt(ARG_ID, it.id)
            }
            findNavController().navigate(R.id.action_druglistFragment_to_drugInfoFragment, bundle)
        }
    )

    override fun subscribe() {
        super.subscribe()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                manageState(state)
            }
        }
    }

    override fun initViews() {
        super.initViews()
        initAdapter()
    }

    private fun initAdapter() = with(binding.rvDruglist) {
        layoutManager = GridLayoutManager(requireContext(),2)
        adapter = drugListAdapter
    }

    private fun manageState(state: DrugSearchScreenState) {
        when (state) {
            is DrugSearchScreenState.Error -> {
                buildErrorMessage(state)
                showContent(errorVisible = true)
            }

            is DrugSearchScreenState.Content -> {
                showContent(contentVisible = true)
                showDrugList(state)
            }
            else -> {
                showContent(contentVisible = true)
            } // place for default and loading status
        }
    }

    private fun showDrugList(data: DrugSearchScreenState.Content) {
        drugListAdapter.setData(data.drugList)
    }
    private fun showContent(contentVisible: Boolean = false, errorVisible: Boolean = false) =
        with(binding) {
            rvDruglist.isVisible = contentVisible
            errorMessage.root.isVisible = errorVisible
        }

    private fun buildErrorMessage(state: DrugSearchScreenState.Error) = with(binding.errorMessage) {
        tvErrorMessage.text = getString(state.error.messageResource)
        ivErrorIcon.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                state.error.imageResource
            )
        )
    }

    companion object {
        const val ARG_ID = "id"
    }
}