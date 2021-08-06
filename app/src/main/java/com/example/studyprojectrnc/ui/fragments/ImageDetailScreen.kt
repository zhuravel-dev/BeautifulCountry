package com.example.studyprojectrnc.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.studyprojectrnc.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

private val URL = null

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
        arguments?.getString("abc", URL)?.let {
            Picasso.get()
                .load(it)
                .into(image, object : Callback {
                    override fun onSuccess() {
                        Log.d("TAG", "All is fine")
                    }

                    override fun onError(e: Exception?) {
                        throw Exception("Error")
                    }
                })
        }
    }
}