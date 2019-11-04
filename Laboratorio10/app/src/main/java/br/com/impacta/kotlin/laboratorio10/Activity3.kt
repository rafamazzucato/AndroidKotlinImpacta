package br.com.impacta.kotlin.laboratorio10

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        Log.i("onCreate", "Terminou de Criar a Activity3")
    }
}