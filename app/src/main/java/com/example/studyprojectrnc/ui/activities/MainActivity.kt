package com.example.studyprojectrnc.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.studyprojectrnc.googleMaps.MapsFragment
import com.example.studyprojectrnc.ui.fragments.Communicator
import com.example.studyprojectrnc.ui.fragments.FirstFragment
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.fragments.SecondFragment
import com.example.studyprojectrnc.databinding.ActivityMainBinding
import com.example.studyprojectrnc.googleMaps.AnimationFragment
import com.example.studyprojectrnc.googleMaps.DownloadImage


class MainActivity : AppCompatActivity(), Communicator {

    lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, firstFragment)
            .commit()

        DownloadImage(this).execute("https://www.bookbell.in/wp-content/uploads/2018/08/sea-01-1920x960.jpg")
    }

    override fun passAndNavigateToSecondFragment(txtView: String) {
        val bundle = Bundle()
        val transaction = this.supportFragmentManager.beginTransaction()
        val secondFragment = SecondFragment().apply {
            arguments = bundle
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
        val transaction = this.supportFragmentManager.beginTransaction()
        val animation = AnimationFragment()
        transaction.setCustomAnimations(R.anim.animation_entry, R.anim.animation_exit)
        transaction.replace(R.id.fragment_container, animation)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}



