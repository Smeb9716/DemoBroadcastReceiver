package com.example.demobroadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainAct : AppCompatActivity(), ConnectReceiverListener, CustomReceiverListener,
    LocalReceiverListener {

    companion object{
        const val customActionBroadcast: String = "customActionBroadcast"
        const val localBroadcast: String = "localBroadcast"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // event connected internet
        // using callback + broadcast receiver
        handleStaticBroadcastReceiver()

        handleCustomBroadcastReceiver()

        handleLocalBroadcastReceiver()
    }

    private fun handleLocalBroadcastReceiver() {
        tvLocalBroadcast.setOnClickListener(){
            LocalReceiver.localReceiverListener = this

            val intent = Intent()
            intent.action = localBroadcast
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    private fun handleCustomBroadcastReceiver() {
        tvCustomBroadcast.setOnClickListener(){
            CustomReceiver.customReceiverListener = this

            val intent = Intent()
            intent.action = customActionBroadcast
            sendBroadcast(intent)
        }
    }


    private fun handleStaticBroadcastReceiver() {
        tvStaticBroadcast.setOnClickListener(){
            ConnectReceiver.connectReceiverListener = this

            // register listener connected internet
            registerReceiver(ConnectReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            Log.e("Smeb","Register Success")
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if(isConnected){
           Toast.makeText(this,"Connected Internet",Toast.LENGTH_SHORT).show()
        }else{
           Toast.makeText(this,"Disconnected Internet",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCustomChanged() {
        Toast.makeText(this,"Custom BroadcastReceiver",Toast.LENGTH_SHORT).show()
    }

    override fun onLocalChanged() {
        Toast.makeText(this,"Local BroadcastReceiver",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(CustomReceiver(), IntentFilter(customActionBroadcast))
        LocalBroadcastManager.getInstance(this).registerReceiver(LocalReceiver(), IntentFilter(localBroadcast))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(ConnectReceiver())
        unregisterReceiver(CustomReceiver())
        LocalBroadcastManager.getInstance(this).unregisterReceiver(LocalReceiver())
    }


}