package com.example.studyprojectrnc.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyprojectrnc.*
import com.example.studyprojectrnc.databinding.FragmentSecondBinding
import com.example.studyprojectrnc.repository.model.HitsDataList
import retrofit2.Call
import retrofit2.Response

class SecondFragment : Fragment(R.layout.fragment_second) {

    lateinit var service: ImagesService

    lateinit var viewBinding: FragmentSecondBinding

    private val customAdapter by lazy { ImageAdapter() }

    private val imageAdapter: ImageAdapter = ImageAdapter()

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
        service =
            RetrofitClientInstance.getRetrofitInstance().create(ImagesService::class.java)
        getAllData()
        // customAdapter.updateTitleData(mockData.mapIndexed { index, item -> item.toTitleData(index) })
    }

    private fun getAllData() {
        service.getContent()
            .enqueue(object : retrofit2.Callback<HitsDataList> {
                override fun onResponse(
                    call: Call<HitsDataList>,
                    response: Response<HitsDataList>
                ) {
                    Log.v("okhttp", response.toString())
                   val data =  response.body()?.images ?: arrayListOf()
//                    imageAdapter.updateTitleData(data)
                    imageAdapter.addData(data)
                }

                override fun onFailure(call: Call<HitsDataList>, t: Throwable) {
                    Log.v("okhttp", t.message.toString())
                }
            })
    }

}
