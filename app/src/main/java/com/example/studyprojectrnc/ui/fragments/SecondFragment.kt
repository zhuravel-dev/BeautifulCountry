package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.data.repository.ImagesRepositoryRealm
import com.example.studyprojectrnc.location.MyWorker
import com.example.studyprojectrnc.ui.activities.SORT
import com.example.studyprojectrnc.ui.adapters.ImageAdapter
import com.example.studyprojectrnc.ui.paging3.ImagePaging
import com.example.studyprojectrnc.ui.viewModel.SecondFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.TimeUnit

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var viewModel: SecondFragmentViewModel? = null
    private val customAdapter by lazy { ImageAdapter() }

    private val myService = ImagesRepositoryRealm()
    private lateinit var sliding: SlidingPaneLayout
    private lateinit var rcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    private fun initAdapter() {
        customAdapter.onItemClick = { largeImage ->
            println("${largeImage.largeImageURL}")
            openDetails(largeImage.largeImageURL)
        }
        rcView.adapter = customAdapter
    }

    private fun openDetails(largeImageURL: String?) {
        childFragmentManager.commit {
            val navHostFragment = ImageDetailScreen()
            val bundle = Bundle()
            bundle.putString("abc", largeImageURL)
            navHostFragment.arguments = bundle
            replace(R.id.detail_container, navHostFragment)
            if (sliding.isOpen) {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
            sliding.open()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        viewModel?.fetchData()
        //  sliding = view.findViewById(R.id.sliding)
        rcView = view.findViewById(R.id.rcView)
        subscribeToLiveData()
        startWorker()
        initAdapter()
    }

    private fun startWorker() {
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance().enqueue(periodicWorkRequest)
    }

    private fun subscribeToLiveData() {
        lifecycleScope.launchWhenCreated {
            Pager(config = PagingConfig(pageSize = SORT, maxSize = 200),
                pagingSourceFactory = { ImagePaging(myService) }).flow.collectLatest {
                customAdapter.submitData(it)
            }
        }
    }
}

