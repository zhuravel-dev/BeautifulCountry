package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.studyprojectrnc.R
import com.squareup.picasso.Picasso

private val URL = null
const val ARGUMENTS_KEY = "adc"

class ImageDetailScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_detail_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = view.findViewById<ImageView>(R.id.ivDetailImage)
        arguments?.getString(ARGUMENTS_KEY, URL)?.let {
            Picasso.get()
                .load(it)
                .into(image)
        }
    }
}