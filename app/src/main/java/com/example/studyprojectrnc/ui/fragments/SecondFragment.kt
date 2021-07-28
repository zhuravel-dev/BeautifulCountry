package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.studyprojectrnc.*
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.data.repository.ImagesRepositoryRealm
import com.example.studyprojectrnc.location.MyWorker
import com.example.studyprojectrnc.ui.dialogDetail.DialogDetail
import com.example.studyprojectrnc.ui.adapters.ImageAdapter
import com.example.studyprojectrnc.ui.paging3.ImagePaging
import com.example.studyprojectrnc.ui.viewModel.SecondFragmentViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.TimeUnit

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var viewModel: SecondFragmentViewModel? = null
    private val customAdapter by lazy { ImageAdapter() }
    private val dialogDetail = DialogDetail(this)
    val myServis = ImagesRepositoryRealm()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    private fun initAdapter() {
        customAdapter.onItemClick = { largeImage ->
            Log.d("TAG", "${largeImage.largeImageURL}")
            println("${largeImage.largeImageURL}")
            dialogDetail.startShowing(largeImage.largeImageURL)
        }
        rcView.adapter = customAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        viewModel?.fetchData()
        subscribeToLiveData()
        startWorker()
    }

    private fun startWorker() {
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .build()
        subscribeToLiveData()
        WorkManager.getInstance().enqueue(periodicWorkRequest)
    }

    private fun subscribeToLiveData() {
        viewModel?.models?.observe(viewLifecycleOwner, {
            it?.let(::updateAdapter)
        })
        lifecycleScope.launchWhenCreated {
            Pager(config = PagingConfig(pageSize = 10, maxSize = 200),
                pagingSourceFactory = { ImagePaging(myServis) }).flow.collectLatest {
                customAdapter.submitData(it)
            }
        }
    }

    private fun updateAdapter(images: List<ModelImageRealm>?) {
        images?.let(customAdapter::updateTitleData)
    }
}
