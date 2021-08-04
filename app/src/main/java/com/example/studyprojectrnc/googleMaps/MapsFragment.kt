package com.example.studyprojectrnc.googleMaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.fragments.Communicator
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = activity as Communicator
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onMapReady(map: GoogleMap) {

        map.setInfoWindowAdapter(InfoWindowCustomAdapter(requireContext()))

        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(
            MarkerOptions().position(sydney)
                .icon(bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_sydney))
                .infoWindowAnchor(0.5f, 0.5f)
        )

        val canberra = LatLng(-35.28, 149.13)
        map.addMarker(
            MarkerOptions().position(canberra).title("Marker in Canberra")
                .icon(bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_canberra))
                .infoWindowAnchor(0.5f, 0.5f)
        )

        val melbourne = LatLng(-37.815, 144.946)
        map.addMarker(
            MarkerOptions().position(melbourne).title("Marker in Melbourne")
                .icon(bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_melbourne))
                .infoWindowAnchor(0.5f, 0.5f)
        )

        map.moveCamera(CameraUpdateFactory.newLatLng(melbourne))
        map.setOnInfoWindowClickListener { clickedMarker ->
            onMarkerClick(clickedMarker)
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
//        communicator.navigateToAnimationFragment()
        Log.i("TAG", "In onMarkerClick")
        return true
    }
}