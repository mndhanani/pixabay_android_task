package com.task.pixabay.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.task.pixabay.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the Navigation Controller
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_fragment -> {
                    // Hide back button on Home Fragment
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                else -> {
                    // Show back button on other fragments
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handle navigation back actions
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}