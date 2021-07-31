package com.devhyeon.autoviewpager.basicAutoViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ARG_OBJECT = "object"

class AutoViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = MAX_PAGE

    override fun createFragment(position: Int): Fragment {
        val fragment = BasicAutoViewPagerFragmentInView()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position.nextPosition())
        }
        return fragment
    }

    private fun Int.nextPosition(): Int {
        return this + 1
    }

}