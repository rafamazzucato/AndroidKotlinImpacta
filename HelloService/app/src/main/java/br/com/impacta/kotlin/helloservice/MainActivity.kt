package br.com.impacta.kotlin.helloservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, HelloService::class.java)

        btStart.setOnClickListener {
            startService(intent)
        }
        btStop.setOnClickListener {
            stopService(intent)
        }
    }
}
