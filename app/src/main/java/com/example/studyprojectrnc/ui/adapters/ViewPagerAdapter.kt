package com.example.studyprojectrnc.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.studyprojectrnc.googleMaps.MapsFragment
import com.example.studyprojectrnc.ui.fragments.CameraFragment
import com.example.studyprojectrnc.ui.fragments.FirstFragment
import com.example.studyprojectrnc.ui.fragments.SecondFragment

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FirstFragment()
            1 -> MapsFragment()
            2 -> SecondFragment()
            3 -> CameraFragment()
            else -> Fragment()
        }
    }
}