package com.devhyeon.autoviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devhyeon.autoviewpager.databinding.FragmentAutoViewpagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AutoViewPagerFragment : Fragment() {
    private lateinit var _binding: FragmentAutoViewpagerBinding
    private val binding get() = _binding

    companion object {
        private val TAG = "DevHyeon >>> " + AutoViewPagerFragment::class.java.name
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auto_viewpager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = AutoViewPagerAdapter(this@AutoViewPagerFragment)

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            tab.text = (position+1).toString()
            tab.setIcon(R.drawable.ic_launcher_foreground)
        }.attach()

        autoScrollViewPager()
    }


    private fun autoScrollViewPager() {
        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                delay(1500)
                if(binding.viewPager.currentItem == 4) {
                    binding.viewPager.currentItem = 0
                } else {
                    binding.viewPager.currentItem = binding.viewPager.currentItem+1
                }
            }
        }
    }
}