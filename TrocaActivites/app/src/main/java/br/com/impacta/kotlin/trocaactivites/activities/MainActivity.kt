package br.com.impacta.kotlin.trocaactivites.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.impacta.kotlin.laboratorio10.DebugActivity
import br.com.impacta.kotlin.trocaactivites.R

class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, BemVindoActivity::class.java)
        startActivity(intent)
        //finish()
    }
}
