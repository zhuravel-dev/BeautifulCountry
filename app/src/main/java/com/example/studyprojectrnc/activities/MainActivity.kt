package com.example.studyprojectrnc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.studyprojectrnc.Communicator
import com.example.studyprojectrnc.SecondFragmentViewModel
import com.example.studyprojectrnc.fragments.FirstFragment
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.fragments.SecondFragment
import com.example.studyprojectrnc.databinding.ActivityMainBinding
import com.example.studyprojectrnc.db.RealmObject
import io.realm.Realm


class MainActivity : AppCompatActivity(), Communicator {

    lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null
    private var viewModel: SecondFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        Realm.init(this)

//      binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
//      binding = ActivityMainBinding.bind(pbView)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, firstFragment)
            .commit()

        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        viewModel?.getData()
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
}


