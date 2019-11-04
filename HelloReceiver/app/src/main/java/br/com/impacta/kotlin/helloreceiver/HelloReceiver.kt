package br.com.impacta.kotlin.helloreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class HelloReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(" livroAndroid", "Hello Receiver")
        Toast.makeText(context, "Intent recebida", Toast.LENGTH_LONG).show()
    }
}
