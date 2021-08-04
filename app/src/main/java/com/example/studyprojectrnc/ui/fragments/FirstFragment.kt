package com.example.studyprojectrnc.ui.fragments

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.location.Util
import com.example.studyprojectrnc.databinding.FragmentFirstBinding
import com.example.studyprojectrnc.location.LocationService

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var communicator: Communicator
    private lateinit var mServiceIntent: Intent
    private var mLocationService: LocationService = LocationService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        communicator = activity as Communicator
        binding.btnNext.setOnClickListener {
            communicator.passAndNavigateToSecondFragment(binding.tvWelcomeText.text.toString())
        }
        binding.btnMap.setOnClickListener {
            communicator.navigateToMapFragment()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val thisActivity = this@FirstFragment

        if (!Util.isLocationEnabledOrNot(requireContext())) {
            Util.showAlertLocation(
                requireContext(),
                getString(R.string.gps_enable),
                getString(R.string.turn_on_gps),
                getString(R.string.OK)
            )
        }

        requestPermissionsSafely(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 200)

        binding.btnGetLocation.setOnClickListener {
            mServiceIntent = Intent(requireContext(), mLocationService.javaClass)
            if (!Util.isMyServiceRunning(
                    mLocationService.javaClass,
                    thisActivity.requireActivity()
                )
            ) {
                requireActivity().startService(mServiceIntent)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.start_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.service_running),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(
        permissions: Array<String>,
        requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    override fun onDestroyView() {
        if (::mServiceIntent.isInitialized) {
            requireActivity().stopService(mServiceIntent)
        }
        super.onDestroyView()
    }
}





