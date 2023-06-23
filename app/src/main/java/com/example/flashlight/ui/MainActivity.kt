package com.example.flashlight.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.flashlight.R
import com.example.flashlight.databinding.ActivityMainBinding
import com.example.flashlight.ui.fragments.BottomSheetRatingFrag

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        val config = AppBarConfiguration(navController.graph)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}