package com.devhyeon.autoviewpager.infinityAutoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.devhyeon.autoviewpager.R
import com.devhyeon.autoviewpager.databinding.FragmentInfinityAutoViewpagerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InfinityAutoViewPagerFragment: Fragment() {
    //variable for DataBinding
    private lateinit var _binding: FragmentInfinityAutoViewpagerBinding
    private val binding get() = _binding

    var isAutoScroll: Boolean = true

    //Using Log
    companion object {
        private val TAG = "DevHyeon >>> " + InfinityAutoViewPagerFragment::class.java.name
    }

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
            R.layout.fragment_infinity_auto_viewpager, container, false
        )

        //ViewPager Adapter set
        binding.viewPager.adapter = InfinityAutoViewPagerAdapter(this)
        binding.viewPager.setCurrentItem(MAX_PAGE/2,false)

        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)

        return binding.root
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
                    if (binding.viewPager.currentItem == END_PAGE_INDEX) {
                        binding.viewPager.setCurrentItem(MAX_PAGE/2,false)
                    } else if(binding.viewPager.currentItem == START_PAGE_INDEX) {
                        binding.viewPager.setCurrentItem(MAX_PAGE/2,false)
                    }
                    isAutoScroll = true
                }
                ViewPager2.SCROLL_STATE_SETTLING -> {
                    // Pager is automatically settling to the current page.
                }
            }
        }
    }

    /**
    * When View creation is complete, start AutoScroll.
    * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoScrollViewPager()
    }

    /**
     * Move one PAGE for each AUTO_TIME.
     * */
    private fun autoScrollViewPager() {
        viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                val current = binding.viewPager.currentItem
                delay(AUTO_TIME)
                if (isAutoScroll && binding.viewPager.currentItem == current) {
                    binding.viewPager.currentItem = binding.viewPager.currentItem +1
                }
            }
        }
    }
}