package com.example.studyprojectrnc.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.studyprojectrnc.databinding.FragmentSecondBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ImageAdapter
import com.example.studyprojectrnc.ImagesService
import com.example.studyprojectrnc.TitleData
import com.example.studyprojectrnc.repository.model.Json4Kotlin_Base
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SecondFragment : Fragment(R.layout.fragment_second) {
    lateinit var viewBinding: FragmentSecondBinding
    private val customAdapter by lazy { ImageAdapter() }
    //private val mockData = listOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6")
    lateinit var service : ImagesService
    private val imageAdapter: ImageAdapter = ImageAdapter()

    private fun getAllData() {
        service.getContent()
            .enqueue(object : Callback<Json4Kotlin_Base> {
                override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
                    Log.v("okhttp", t.message.toString())
                }

                override fun onResponse(
                    call: Call<Json4Kotlin_Base>,
                    response: Response<Json4Kotlin_Base>
                ) {
                    Log.v("okhttp", response.toString())
                    imageAdapter.addData(response.body()?.images)
                }
            })
    }

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
       // customAdapter.updateTitleData(mockData.mapIndexed { index, item -> item.toTitleData(index) })
    }
}

    fun String.toTitleData(index: Int): TitleData {
        return TitleData(index.toLong(), this)
    }