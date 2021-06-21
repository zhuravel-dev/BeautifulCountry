package com.example.studyprojectrnc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.studyprojectrnc.databinding.FragmentSecondBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.TitleAdapter

class SecondFragment : Fragment(R.layout.fragment_second) {
    lateinit var viewBinding: FragmentSecondBinding
    private val customAdapter by lazy { TitleAdapter() }

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
}



