package com.example.studyprojectrnc.googleMaps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.studyprojectrnc.R
import com.squareup.picasso.Picasso

private const val URL = "https://loremflickr.com/320/240/sea"

class AnimationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = view.findViewById<ImageView>(R.id.ivKangaroo)
        Picasso.get()
            .load(URL)
            .into(image)
    }
}