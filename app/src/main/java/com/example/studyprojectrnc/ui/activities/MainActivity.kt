package com.example.studyprojectrnc.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.studyprojectrnc.googleMaps.MapsFragment
import com.example.studyprojectrnc.ui.fragments.Communicator
import com.example.studyprojectrnc.ui.fragments.FirstFragment
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.fragments.SecondFragment
import com.example.studyprojectrnc.databinding.ActivityMainBinding
import com.example.studyprojectrnc.googleMaps.AnimationFragment
import com.example.studyprojectrnc.viewPager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(), Communicator {

    lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.ivPager)
        val tabLayout = findViewById<TabLayout>(R.id.ivTabLayout)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout,viewPager){tab,position->
            when(position){
                0->{
                    tab.text="Hello"
                }
                1->{
                    tab.text="Photo"
                }
            }
        }.attach()
    }

    override fun passAndNavigateToSecondFragment(txtView: String) {
        val transaction = this.supportFragmentManager.beginTransaction()
        val secondFragment = SecondFragment().apply {
        }
        transaction.replace(R.id.fragment_container, secondFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun navigateToMapFragment() {
        val transaction = this.supportFragmentManager.beginTransaction()
        val mapsFragment = MapsFragment()
        transaction.replace(R.id.fragment_container, mapsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun navigateToAnimationFragment() {
        val animation = AnimationFragment()
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.animation_entry, R.anim.animation_exit)
        transaction.replace(R.id.fragment_container, animation)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}



