package br.com.impacta.kotlin.trocaactivites.activities

import android.content.Intent
import android.os.Bundle

import br.com.impacta.kotlin.laboratorio10.DebugActivity
import br.com.impacta.kotlin.trocaactivites.R
import kotlinx.android.synthetic.main.bem_vindo_activity.*

class BemVindoActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bem_vindo_activity)

        val intent = getIntent()
        val args = intent.extras
        val nome = args.getString("nome")

        txtBemVindo.text = nome
    }
}