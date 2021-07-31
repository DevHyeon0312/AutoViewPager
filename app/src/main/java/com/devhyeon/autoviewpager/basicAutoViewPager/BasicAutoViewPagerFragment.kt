package com.devhyeon.autoviewpager.basicAutoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.devhyeon.autoviewpager.R
import com.devhyeon.autoviewpager.databinding.FragmentAutoViewpagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <English>
 * Using Activity
 * Basic Auto ViewPager2 Fragment
 * NextPage after AUTO_TIME
 *
 * <한국어>
 * 액티비티에서 사용
 * 기본 ViewPager2 프래그먼트
 * AUTO_TIME 이후에 다음페이지로 이동합니다.
 * */
class BasicAutoViewPagerFragment : Fragment() {
    //variable for DataBinding
    private lateinit var _binding: FragmentAutoViewpagerBinding
    private val binding get() = _binding

    //Using Log
    companion object {
        private val TAG = "DevHyeon >>> " + BasicAutoViewPagerFragment::class.java.name
    }

    var isAutoScroll: Boolean = true

    /**
     * Data Binding inflate will proceed here.
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_auto_viewpager, container, false
        )

        binding.viewPager.adapter = AutoViewPagerAdapter(this@BasicAutoViewPagerFragment)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = (position + 1).toString()    //Tab Text
//            tab.setIcon(R.drawable.ic_launcher_foreground)    //Tab Icon
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)

        return binding.root
    }

    /**
     * When View creation is complete, start AutoScroll.
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoScrollViewPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {}
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageScrollStateChanged(state: Int) {
            when (state) {
                ViewPager2.SCROLL_STATE_DRAGGING -> {
                    // User begins dragging or a fake drag is started.
                    isAutoScroll = false
                }
                ViewPager2.SCROLL_STATE_IDLE -> {
                    // Pager is fully stopped/idle.
                    isAutoScroll = true
                }
                ViewPager2.SCROLL_STATE_SETTLING -> {
                    // Pager is automatically settling to the current page.
                }
            }
        }
    }

    /**
     * Move one PAGE for each AUTO_TIME.
     * When you arrive on the last page, go to the first PAGE.
     * */
    private fun autoScrollViewPager() {
        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                delay(AUTO_TIME)
                if (isAutoScroll) {
                    if (binding.viewPager.currentItem == END_PAGE_INDEX) {
                        binding.viewPager.currentItem = START_PAGE_INDEX
                    } else {
                        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                    }
                }
            }
        }
    }
}