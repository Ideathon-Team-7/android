package com.example.idea_team7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.idea_team7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_fragment_container,HomeFragment())
            commitAllowingStateLoss()
        }

        binding.actovotyMainBnv.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.mnu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment_container, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.mnu_look -> {
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