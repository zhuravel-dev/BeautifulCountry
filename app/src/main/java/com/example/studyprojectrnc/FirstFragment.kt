package com.example.studyprojectrnc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.studyprojectrnc.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.first_button)?.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        navController?.navigate(R.id.action_firstFragment_to_secondFragment)
    }
}