package com.example.idea_team7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.idea_team7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initNavigator()
        initBottomNavigation()
    }

    private fun initNavigator() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.activityMainBnv.setupWithNavController(navController)
    }

    private fun initBottomNavigation(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_fragment_container,HomeFragment())
            commitAllowingStateLoss()
        }

        binding.activityMainBnv.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.mnu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment_container, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.mnu_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment_container, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}