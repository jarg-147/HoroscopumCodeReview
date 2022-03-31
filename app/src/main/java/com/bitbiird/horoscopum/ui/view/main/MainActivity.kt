package com.bitbiird.horoscopum.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.bitbiird.horoscopum.R
import com.bitbiird.horoscopum.databinding.ActivityMainBinding
import com.bitbiird.horoscopum.ui.view.home.HomeFragment
import com.bitbiird.horoscopum.ui.view.signslist.HoroscopeListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        when (navHostFragment.childFragmentManager.fragments[0]) {
            is HomeFragment -> finish()
            is HoroscopeListFragment -> navHostFragment.navController.navigate(R.id.action_SignListFragment_to_HomeFragment)
        }
    }

}