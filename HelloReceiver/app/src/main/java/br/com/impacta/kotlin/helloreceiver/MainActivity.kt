package br.com.impacta.kotlin.helloreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val helloReceiver : HelloReceiver by lazy{
        HelloReceiver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btEnviarBroadCast.setOnClickListener {
            //val intent = Intent(this, HelloReceiver::class.java)
            //sendBroadcast(intent)

            sendBroadcast(Intent("BINGO"))

            Toast.makeText(this, "Intent enviada", Toast.LENGTH_LONG).show()
        }

        registerReceiver(helloReceiver, IntentFilter("BINGO"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(helloReceiver)
    }

}
