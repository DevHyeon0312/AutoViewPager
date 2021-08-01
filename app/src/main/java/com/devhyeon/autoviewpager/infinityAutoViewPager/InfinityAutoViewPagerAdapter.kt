package com.devhyeon.autoviewpager.infinityAutoViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ARG_OBJECT = "object"
class InfinityAutoViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = MAX_PAGE

    override fun createFragment(position: Int): Fragment {
        val index = position % REAL_MAX_PAGE
        val fragment = InfinityAutoViewPagerFragmentInView()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, index.nextPosition())
        }
        return fragment
    }

    private fun Int.nextPosition(): Int {
        return this + 1
    }
}