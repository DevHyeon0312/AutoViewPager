package com.devhyeon.autoviewpager.indicatorAutoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devhyeon.autoviewpager.R
import com.devhyeon.autoviewpager.databinding.FragmentIndicatorAutoViewpagerInviewBinding

class IndicatorAutoViewPagerFragmentInView : Fragment() {
    //variable for DataBinding
    private lateinit var _binding: FragmentIndicatorAutoViewpagerInviewBinding
    private val binding get() = _binding

    companion object {
        private val TAG = "DevHyeon >>> " + IndicatorAutoViewPagerFragmentInView::class.java.name
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_indicator_auto_viewpager_inview, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.textView.text = it.getInt("object").toString()
        }
    }
}