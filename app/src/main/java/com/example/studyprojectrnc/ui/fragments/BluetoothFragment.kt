package com.example.studyprojectrnc.ui.fragments

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.DeviceListActivity
import kotlinx.android.synthetic.main.fragment_bluetooth.*

class BluetoothFragment : Fragment() {

    private lateinit var btAdapter: BluetoothAdapter
    private val REQUEST_ENABLE_BT = 1
    private val REQUEST_CODE = 2
    private val REQUEST_CODE_DISCOVERABLE_BT = 3
    private val REQUEST_CONNECT_DEVICE_INSECURE = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btAdapter = BluetoothAdapter.getDefaultAdapter()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), REQUEST_CODE)
                }
            }
            Log.i("TAG", "Permissions verified")
        }

    override fun onStart() {
        super.onStart()
        if (!btAdapter.isEnabled) {
            enableBt()
            Toast.makeText(requireContext(), "Turn on Bluetooth, please", Toast.LENGTH_SHORT).show()
        } else {
            btAdapter.disable()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bluetooth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDiscoverable.setOnClickListener {
            val intent = Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
            startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT)
            Toast.makeText(requireContext(), "Making your device discoverable...", Toast.LENGTH_LONG).show()
        }
    }

    private fun enableBt(){
        val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.connect -> {
                val serverIntent = Intent(activity, DeviceListActivity::class.java)
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE)
                return true
            }
            R.id.btnDiscoverable -> {
                ensureDiscoverable()
                return true
            }
        }
        return false
    }

    private fun ensureDiscoverable() {
        if (btAdapter.scanMode !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE
        ) {
            val discoverableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 500)
            startActivity(discoverableIntent)
            Log.i("TAG", "In fun ensureDiscoverable")
        }
    }
}