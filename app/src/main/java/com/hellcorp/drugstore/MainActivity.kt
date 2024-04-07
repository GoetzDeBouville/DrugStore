package com.hellcorp.drugstore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.hellcorp.drugstore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setToolbar()
        setContainerFragment()
    }

    private fun setContainerFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.druglistFragment -> supportActionBar?.setTitle(R.string.list)
                else -> actionBar?.title = ""
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
}