package com.example.studyprojectrnc.presentation.firstScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.studyprojectrnc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireView())

        val buttonNext = view.findViewById<FloatingActionButton>(R.id.btnNext)
        buttonNext.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
            Timber.i("Navigation to SecondFragment")
        }

        val buttonCamera = view.findViewById<FloatingActionButton>(R.id.btnCamera)
        buttonCamera.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_cameraFragment)
            Timber.i("Navigation to CameraFragment")
        }

        val buttonBluetooth = view.findViewById<FloatingActionButton>(R.id.btnBluetooth)
        buttonBluetooth.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_bluetoothFragment)
            Timber.i("Navigation to BluetoothFragment")
        }

        val buttonMap = view.findViewById<FloatingActionButton>(R.id.btnMap)
        buttonMap.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_mapsFragment)
            Timber.i("Navigation to MapFragment")
        }

    }
}