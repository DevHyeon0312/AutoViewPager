package com.devhyeon.autoviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devhyeon.autoviewpager.basicAutoViewPager.BasicAutoViewPagerFragment
import com.devhyeon.autoviewpager.databinding.ActivityMainBinding
import com.devhyeon.autoviewpager.indicatorAutoViewPager.IndicatorAutoViewPagerFragment
import com.devhyeon.autoviewpager.infinityAutoViewPager.InfinityAutoViewPagerFragment

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //BasicFAutoViewPager
        supportFragmentManager.beginTransaction()
            .add(binding.frameLayout.id, BasicAutoViewPagerFragment()).commit()

        //IndicatorAutoViewPager
        supportFragmentManager.beginTransaction()
            .add(binding.frameLayout2.id, IndicatorAutoViewPagerFragment()).commit()


        //InfinityAutoViewPager
        supportFragmentManager.beginTransaction()
            .add(binding.frameLayout3.id, InfinityAutoViewPagerFragment()).commit()
    }
}