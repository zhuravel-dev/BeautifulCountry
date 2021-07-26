package com.example.studyprojectrnc.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.databinding.ActivityMainBinding
import com.example.studyprojectrnc.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

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
                0->{tab.text="Hello"}
                1->{tab.text="Map"}
                2->{tab.text="Photo"}
                3->{tab.text="Camera"}
            }
        }.attach()
    }
}



