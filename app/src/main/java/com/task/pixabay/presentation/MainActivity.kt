package com.task.pixabay.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.task.pixabay.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the Navigation Controller
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)

        // Set the ActionBar title based on the current fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.login_fragment -> {
                    supportActionBar?.title = getString(R.string.title_login)
                }

                R.id.register_fragment -> {
                    supportActionBar?.title = getString(R.string.title_register)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handle navigation back actions
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}