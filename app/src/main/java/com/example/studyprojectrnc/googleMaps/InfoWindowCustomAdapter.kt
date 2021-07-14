package com.example.studyprojectrnc.googleMaps

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.databinding.InfoWindowBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class InfoWindowCustomAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    private fun getUrl() = "https://loremflickr.com/320/240"

    override fun getInfoWindow(p0: Marker): View? = null

    override fun getInfoContents(p0: Marker): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.info_window, null)
        val image:ImageView = view.findViewById<ImageView>(R.id.ivRandom)
        Glide.with(context).clear(image)
        Log.e("TAG", "URL")
        Glide.with(context).load(getUrl()).into(image)
        return view
    }
}