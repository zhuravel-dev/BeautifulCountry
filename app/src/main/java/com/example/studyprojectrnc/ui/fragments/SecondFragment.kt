package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.PeriodicWorkRequest
import com.example.studyprojectrnc.*
import com.example.studyprojectrnc.databinding.FragmentSecondBinding
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.ui.viewModel.SecondFragmentViewModel
import java.util.concurrent.TimeUnit

class SecondFragment : Fragment(R.layout.fragment_second) {

    lateinit var viewBinding: FragmentSecondBinding
    private var viewModel: SecondFragmentViewModel? = null
    private val customAdapter by lazy { ImageAdapter() }

    private var list: List<ModelImageRealm>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSecondBinding.bind(view)

        viewBinding.rcView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customAdapter
        }

        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        viewModel?.getData()
        subscribeToLiveData()
        startWorker()
    }

    private fun startWorker() {
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(WorkManager::class.java, 15, TimeUnit.MINUTES)
            .build()
        subscribeToLiveData()
        WorkManager.getInstance().enqueue(periodicWorkRequest)
    }

    private fun subscribeToLiveData() {
        viewModel?.models?.observe(viewLifecycleOwner, {
            it?.let(::updateAdapter)
        })
    }

    private fun updateAdapter(images: List<ModelImageRealm>?) {
        images?.let(customAdapter::updateTitleData)
    }
}
