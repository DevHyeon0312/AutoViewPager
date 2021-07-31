package com.devhyeon.autoviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devhyeon.autoviewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        supportFragmentManager.beginTransaction()
            .add(_binding.frameLayout.id, AutoViewPagerFragment()).commit()
    }
}