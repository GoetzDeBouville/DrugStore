package com.hellcorp.drugstore

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.NavHostFragment
import com.hellcorp.drugstore.databinding.ActivityMainBinding
import com.hellcorp.drugstore.druglist.presentation.DrugListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DrugListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setToolbar()
        setContainerFragment()
        onSubscribe()
    }

    private fun onSubscribe() = with(binding) {
        clickListeners()

        tiSearchField.doOnTextChanged { text, _, _, _ ->
            viewModel.getDrugList(text.toString())
            if (text.toString().isNotEmpty()) {
                tlSearchField.endIconDrawable = getDrawable(R.drawable.ic_clear_24px)
            } else {
                tlSearchField.endIconDrawable = getDrawable(R.drawable.ic_search_black_24dp)
            }
        }

        tlSearchField.setEndIconOnClickListener {
            if (tiSearchField.text.toString().isNotEmpty()) tiSearchField.text?.clear()
        }
    }

    private fun setContainerFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.druglistFragment -> supportActionBar?.setTitle(R.string.list)
                else -> {
                    supportActionBar?.title = ""
                    binding.tlSearchField.visibility = View.GONE
                }
            }
        }
    }

    private fun setToolbar() = with(binding.toolbar) {
        setSupportActionBar(binding.toolbar)

        setTitleTextAppearance(
            this@MainActivity,
            R.style.Text_Medium_AppearanceToolbar
        )

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
            title = ""
        }

        setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun clickListeners() = with(binding) {
        ivSearch.setOnClickListener {
            tlSearchField.visibility = View.VISIBLE
        }
    }
}