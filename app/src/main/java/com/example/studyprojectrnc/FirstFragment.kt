package com.example.studyprojectrnc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        communicator = activity as Communicator
        view.button.setOnClickListener {
            communicator.passAndNavigateToSecondFragment(view.tvWelcomeText.text.toString())
        }
        return view
    }
}




