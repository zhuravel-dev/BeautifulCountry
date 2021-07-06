package com.example.studyprojectrnc.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.studyprojectrnc.ui.fragments.Communicator
import com.example.studyprojectrnc.ui.viewModel.SecondFragmentViewModel
import com.example.studyprojectrnc.ui.fragments.FirstFragment
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.fragments.SecondFragment
import com.example.studyprojectrnc.databinding.ActivityMainBinding
import com.example.studyprojectrnc.data.db.ModelRealm


class MainActivity : AppCompatActivity(), Communicator {

    lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null
    private var viewModel: SecondFragmentViewModel? = null
    private var list: List<ModelRealm>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, firstFragment)
            .commit()

        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        viewModel?.getData()
        viewModel?.models?.observe(this, { list = it })
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

        list?.let(secondFragment::updateAdapter)
    }
}


