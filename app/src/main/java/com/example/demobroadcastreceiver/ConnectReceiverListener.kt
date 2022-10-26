package com.example.demobroadcastreceiver

interface ConnectReceiverListener {
    fun onNetworkConnectionChanged(isConnected : Boolean)
}