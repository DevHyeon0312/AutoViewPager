package com.devhyeon.autoviewpager.indicatorAutoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devhyeon.autoviewpager.R
import com.devhyeon.autoviewpager.basicAutoViewPager.AUTO_TIME
import com.devhyeon.autoviewpager.basicAutoViewPager.END_PAGE_INDEX
import com.devhyeon.autoviewpager.basicAutoViewPager.START_PAGE_INDEX
import com.devhyeon.autoviewpager.databinding.FragmentIndicatorAutoViewpagerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IndicatorAutoViewPagerFragment : Fragment() {
    //variable for DataBinding
    private lateinit var _binding: FragmentIndicatorAutoViewpagerBinding
    private val binding get() = _binding

    //Using Log
    companion object {
        private val TAG = "DevHyeon >>> " + IndicatorAutoViewPagerFragment::class.java.name
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
            R.layout.fragment_indicator_auto_viewpager, container, false
        )

        //ViewPager Adapter set
        binding.viewPager.adapter = IndicatorAutoViewPagerAdapter(this)
        //Indicator set
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        return binding.root
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
     * When you arrive on the last page, go to the first PAGE.
     * */
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