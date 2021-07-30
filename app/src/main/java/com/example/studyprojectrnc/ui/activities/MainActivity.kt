package com.example.studyprojectrnc.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.databinding.ActivityMainBinding
import com.example.studyprojectrnc.googleMaps.AnimationFragment
import com.example.studyprojectrnc.googleMaps.MapsFragment
import com.example.studyprojectrnc.ui.adapters.ViewPagerAdapter
import com.example.studyprojectrnc.ui.fragments.Communicator
import com.example.studyprojectrnc.ui.fragments.FirstFragment
import com.example.studyprojectrnc.ui.fragments.SecondFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

var SORT = 10

class MainActivity : AppCompatActivity(), Communicator {

    lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, firstFragment)
            .commit()

        /*       val viewPager = findViewById<ViewPager2>(R.id.ivPager)
        val tabLayout = findViewById<TabLayout>(R.id.ivTabLayout)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when (position) {
                0 -> {tab.text = "Hello"}
                1 -> {tab.text = "Map"}
                2 -> {tab.text = "Photo"}
                3 -> {tab.text = "Camera"}
            }
        }.attach()
    }*/

        /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }*/

        /*     override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu10 -> {
                    SORT = 10
                    Toast.makeText(this, "Ok, 10 items", Toast.LENGTH_SHORT).show()
                }
                R.id.menu15 -> {
                    SORT = 15
                    Toast.makeText(this, "Ok, 15 items", Toast.LENGTH_SHORT).show()
                }
                R.id.menu20 -> {
                    SORT = 20
                    Toast.makeText(this, "Ok, 20 items", Toast.LENGTH_SHORT).show()
                }
            }
            return true
        }
    }*/
    }

    override fun passAndNavigateToSecondFragment(txtView: String) {
        val transaction = this.supportFragmentManager.beginTransaction()
        val secondFragment = SecondFragment().apply {
        }
        transaction.replace(R.id.mainFrame, secondFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun navigateToMapFragment() {
        val transaction = this.supportFragmentManager.beginTransaction()
        val mapsFragment = MapsFragment()
        transaction.replace(R.id.mainFrame, mapsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun navigateToAnimationFragment() {
        val animation = AnimationFragment()
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.animation_entry, R.anim.animation_exit)
        transaction.replace(R.id.mainFrame, animation)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}




