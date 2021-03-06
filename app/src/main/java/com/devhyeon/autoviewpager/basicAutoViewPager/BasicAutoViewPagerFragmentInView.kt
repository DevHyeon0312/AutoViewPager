package com.devhyeon.autoviewpager.basicAutoViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devhyeon.autoviewpager.R
import com.devhyeon.autoviewpager.databinding.FragmentAutoViewpagerInviewBinding

/**
 * <English>
 * Each Fragment that ViewPager2 will show.
 *
 * <한국어>
 * 프래그먼트에서 사용
 * ViewPager2 에서 보여줄 각 Fragment 입니다.
 * */
class BasicAutoViewPagerFragmentInView : Fragment() {
    //variable for DataBinding
    private lateinit var _binding: FragmentAutoViewpagerInviewBinding
    private val binding get() = _binding

    companion object {
        private val TAG = "DevHyeon >>> " + BasicAutoViewPagerFragmentInView::class.java.name
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_auto_viewpager_inview, container, false
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