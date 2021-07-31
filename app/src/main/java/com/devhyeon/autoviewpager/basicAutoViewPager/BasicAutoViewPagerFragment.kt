package com.devhyeon.autoviewpager.basicAutoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devhyeon.autoviewpager.R
import com.devhyeon.autoviewpager.databinding.FragmentAutoViewpagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <English>
 * Basic Auto ViewPager2 프래그먼트
 * NextPage after AUTO_TIME
 *
 * <한국어>
 * 기본 뷰페이저2 프래그먼트
 * AUTO_TIME 이후에 다음페이지로 이동합니다.
 * */
class BasicAutoViewPagerFragment : Fragment() {
    private lateinit var _binding: FragmentAutoViewpagerBinding
    private val binding get() = _binding

    companion object {
        private val TAG = "DevHyeon >>> " + BasicAutoViewPagerFragment::class.java.name
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_auto_viewpager, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = AutoViewPagerAdapter(this@BasicAutoViewPagerFragment)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = (position + 1).toString()
            tab.setIcon(R.drawable.ic_launcher_foreground)
        }.attach()

        autoScrollViewPager()
    }


    private fun autoScrollViewPager() {
        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                delay(AUTO_TIME)
                if (binding.viewPager.currentItem == END_PAGE_INDEX) {
                    binding.viewPager.currentItem = START_PAGE_INDEX
                } else {
                    binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                }
            }
        }
    }
}