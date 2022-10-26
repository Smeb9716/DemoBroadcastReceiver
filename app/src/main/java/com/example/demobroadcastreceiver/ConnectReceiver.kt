package com.example.demobroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectReceiver : BroadcastReceiver() {

    companion object{
        // using callback to handle logic MainAct
        lateinit var connectReceiverListener : ConnectReceiverListener
    }

    // receive event when register broadcast
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            connectReceiverListener.onNetworkConnectionChanged(isConnect(context))
        }
    }

    //
    private fun isConnect(context: Context) : Boolean{
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}