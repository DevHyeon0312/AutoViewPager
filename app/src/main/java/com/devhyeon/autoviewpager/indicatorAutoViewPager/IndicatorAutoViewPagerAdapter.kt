package com.devhyeon.autoviewpager.indicatorAutoViewPager


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devhyeon.autoviewpager.basicAutoViewPager.MAX_PAGE

private const val ARG_OBJECT = "object"

class IndicatorAutoViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = MAX_PAGE

    override fun createFragment(position: Int): Fragment {
        val fragment = IndicatorAutoViewPagerFragmentInView()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position.nextPosition())
        }
        return fragment
    }

    private fun Int.nextPosition(): Int {
        return this + 1
    }

}