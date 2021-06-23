package com.example.studyprojectrnc.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyprojectrnc.Communicator
import com.example.studyprojectrnc.fragments.FirstFragment
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.fragments.SecondFragment
import com.example.studyprojectrnc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Communicator {
    private val manager = supportFragmentManager
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, firstFragment)
            .commit()
    }

    override fun passAndNavigateToSecondFragment(txtView: String) {
        val bundle = Bundle()
        val transaction = this.supportFragmentManager.beginTransaction()
        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle

        transaction.replace(R.id.fragment_container, secondFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}