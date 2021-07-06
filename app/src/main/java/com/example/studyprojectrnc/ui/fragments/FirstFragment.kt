package com.example.studyprojectrnc.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.studyprojectrnc.databinding.FragmentFirstBinding
import com.example.studyprojectrnc.ui.viewModel.FirstFragmentViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var communicator: Communicator
    private var viewModel: FirstFragmentViewModel? = null

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var needShowLocationRationale: Boolean = false
    private var isExplanationDialogAlreadyShow: Boolean = false

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        initLocationServices()
        startLocationUpdates()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.permissionsGranted()) viewModel?.trackLocation()
                else {
                    if (needShowLocationRationale && isExplanationDialogAlreadyShow.not()) {
                        isExplanationDialogAlreadyShow = true
                        showPermissionExplanationDialog(LOCATION_PERMISSION_REQUEST_CODE)
                    } else showNeedPermissionSnackbar(binding.root)
                }
            }
        }
    }

    private fun initLocationServices() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        needShowLocationRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private inline fun checkLocationPermission(onPermissionGranted: () -> Unit) {
        if (locationIsNotGranted()) {
            if (needShowLocationRationale && isExplanationDialogAlreadyShow.not()) {
                isExplanationDialogAlreadyShow = true
                showPermissionExplanationDialog(LOCATION_PERMISSION_REQUEST_CODE)
            } else requestLocationPermission(LOCATION_PERMISSION_REQUEST_CODE)
        } else onPermissionGranted()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        checkLocationPermission {
            viewModel?.trackLocation()
        }
    }

    private fun locationIsNotGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(requestCode: Int) {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            requestCode
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.stopTrackLocation()
    }

    private fun IntArray.permissionsGranted() =
        getOrNull(0) == PackageManager.PERMISSION_GRANTED

    private fun showNeedPermissionSnackbar(anchorView: View) {
        Snackbar.make(
            anchorView, "Enable location",
            Snackbar.LENGTH_LONG
        ).setAction("Settings") {
            try {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:" + requireActivity().application.packageName)
                requireActivity().startActivityForResult(intent, REQ_LOCATION_PERMISSIONS_SETTINGS)
            } catch (e: Throwable) {
                val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
                requireActivity().startActivityForResult(intent, REQ_LOCATION_PERMISSIONS_SETTINGS)
            }
        }.show()
    }

    private fun showPermissionExplanationDialog(requestCode: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to \\\"Off\\\". Please enable location to use this app.")
            .setPositiveButton("OK") { _, _ ->
                requestLocationPermission(requestCode)
            }
            .show()
    }


    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1003
        const val REQ_LOCATION_PERMISSIONS_SETTINGS = 1004
    }
}




