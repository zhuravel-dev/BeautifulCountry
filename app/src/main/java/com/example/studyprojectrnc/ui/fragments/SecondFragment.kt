package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyprojectrnc.*
import com.example.studyprojectrnc.databinding.FragmentSecondBinding
import com.example.studyprojectrnc.data.db.ModelRealm

class SecondFragment : Fragment(R.layout.fragment_second) {

    lateinit var viewBinding: FragmentSecondBinding

    private val customAdapter by lazy { ImageAdapter() }

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
    }

    fun updateAdapter(images: List<ModelRealm>?) {
        images?.let(customAdapter::updateTitleData)
    }

}
