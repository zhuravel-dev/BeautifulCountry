package com.example.studyprojectrnc.googleMaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.studyprojectrnc.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso

private const val URL = "https://loremflickr.com/320/240/sea"

class InfoWindowCustomAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(p0: Marker): View? = null

    override fun getInfoContents(p0: Marker): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.info_window, null)
        val image = view.findViewById<ImageView>(R.id.ivRandom)
        Picasso.get()
            .load(URL)
            .into(image)
        return view
    }
}