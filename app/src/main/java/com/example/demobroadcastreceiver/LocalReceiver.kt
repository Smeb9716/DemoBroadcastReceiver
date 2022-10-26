package com.example.demobroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocalReceiver : BroadcastReceiver() {
    companion object{
        var localReceiverListener : LocalReceiverListener? = null
    }

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action.equals(MainAct.localBroadcast)){
            localReceiverListener!!.onLocalChanged()
        }
    }
}