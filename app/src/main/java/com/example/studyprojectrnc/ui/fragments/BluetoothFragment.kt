package com.example.studyprojectrnc.ui.fragments

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment

class BluetoothFragment : Fragment() {

    private var mBtAdapter: BluetoothAdapter? = null
    private var mNewDevicesArrayAdapter: ArrayAdapter<String>? = null

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            if (BluetoothDevice.ACTION_FOUND == action) {
                //             viewModel.onScaningStarted()
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                if (device != null) {
                    mNewDevicesArrayAdapter?.add("""${device.name} ${device.address} """.trimIndent())
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                //            viewModel.onScaningStoped()
            }
        }
    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bluetooth)
        mNewDevicesArrayAdapter = ArrayAdapter(this, R.layout.device_name)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBtAdapter?.cancelDiscovery()
        unregisterReceiver(mReceiver)
    }

       this.registerReceiver(mReceiver, IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED))
*/
}

