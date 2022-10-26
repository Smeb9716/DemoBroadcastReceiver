package com.example.demobroadcastreceiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainAct : AppCompatActivity(), ConnectReceiverListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // event connected internet
        // using callback + broadcast receiver
        handleStaticBroadcastReceiver()
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
}