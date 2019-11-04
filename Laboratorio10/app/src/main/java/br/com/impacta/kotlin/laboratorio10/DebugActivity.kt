package br.com.impacta.kotlin.laboratorio10

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class DebugActivity : AppCompatActivity() {

    private val TAG = "livro"

    private val className : String
        get(){
            val s = javaClass.name
            return s.substring(s.lastIndexOf("."))
        }

    override fun onCreate(icile: Bundle?) {
        super.onCreate(icile)
        Log.d(TAG,  "$className .onCreate() chamado: $icile")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,  "$className .onStart() chamado.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,  "$className .onRestart() chamado.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,  "$className .onResume() chamado.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,  "$className .onPause() chamado.")
    }

    override fun onSaveInstanceState(icile: Bundle) {
        super.onSaveInstanceState(icile)
        Log.d(TAG,  "$className .onSaveInstanceState() chamado: $icile")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,  "$className .onStop() chamado.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,  "$className .onDestroy() chamado.")
    }
}
