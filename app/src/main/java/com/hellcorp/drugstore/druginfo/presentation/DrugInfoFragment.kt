package com.hellcorp.drugstore.druginfo.presentation

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.hellcorp.drugstore.R
import com.hellcorp.drugstore.core.ui.BaseFragment
import com.hellcorp.drugstore.databinding.FragmentDrugInfoBinding
import com.hellcorp.drugstore.domain.models.Drug
import com.hellcorp.drugstore.druginfo.domain.DrugInfoState
import com.hellcorp.drugstore.druglist.presentation.DruglistFragment.Companion.ARG_ID
import com.hellcorp.drugstore.utils.Constants.Companion.BASE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DrugInfoFragment :
    BaseFragment<FragmentDrugInfoBinding, DrugInfoViewModel>(FragmentDrugInfoBinding::inflate) {
    override val viewModel: DrugInfoViewModel by viewModels<DrugInfoViewModel>()

    private var id: Int? = null


    override fun initViews() {
        super.initViews()
        id = arguments?.getInt(ARG_ID)
        id?.let {
            viewModel.getDrugInfo(id!!)
        }
    }

    override fun subscribe() {
        super.subscribe()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                manageState(state)
            }
        }
    }

    private fun manageState(state: DrugInfoState) {
        when (state) {
            is DrugInfoState.Content -> {
                fetchInfo(state.drug)
                Log.i("MyLog", "state.drug = ${state.drug}")
                showContent(contentVisible = true)
            }

            is DrugInfoState.Error -> {
                buildErrorMessage(state)
                showContent(errorVisible = true)
            }

            else -> showContent(contentVisible = true) // place for loading status
        }
    }

    private fun showContent(contentVisible: Boolean = false, errorVisible: Boolean = false) =
        with(binding) {
            listOf(tvItemInfo, tvItemTitle, cvGeo, ivIconCategory, ivItemImage).forEach {
                it.isVisible = contentVisible
            }
            errorMessage.root.isVisible = errorVisible
        }

    private fun buildErrorMessage(state: DrugInfoState.Error) = with(binding.errorMessage) {
        tvErrorMessage.text = getString(state.error.messageResource)
        ivErrorIcon.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                state.error.imageResource
            )
        )
    }

    private fun fetchInfo(data: Drug) = with(binding) {
        tvItemTitle.text = data.name
        tvItemInfo.text = data.description
        ivItemImage.load(data.imageUrl) {
            placeholder(R.drawable.ic_empty_search)
            error(R.drawable.ic_server_error)
        }
        ivIconCategory.load(data.categories?.iconUrl) {
            placeholder(R.drawable.ic_not_found)
            error(R.drawable.ic_not_found)
            transformations(CircleCropTransformation())
        }
    }
}