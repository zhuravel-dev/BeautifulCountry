package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyprojectrnc.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        communicator = activity as Communicator
        binding.btnNext.setOnClickListener {
            communicator.passAndNavigateToSecondFragment(binding.tvWelcomeText.text.toString())
        }
        return view
    }
}




