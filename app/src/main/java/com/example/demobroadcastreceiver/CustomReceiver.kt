package com.example.demobroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    companion object{
        var customReceiverListener : CustomReceiverListener? = null
    }

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action.equals(MainAct.customActionBroadcast)){
            customReceiverListener!!.onCustomChanged()
        }
    }
}