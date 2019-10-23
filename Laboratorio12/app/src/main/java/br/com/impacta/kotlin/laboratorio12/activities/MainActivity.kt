package br.com.impacta.kotlin.laboratorio12.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.impacta.kotlin.laboratorio12.R
import kotlinx.android.synthetic.main.tela_inicial_activicty.*

class MainActivity : AppCompatActivity() {

    private var context: Context? = null
    private var mensagemErro: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial_activicty)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this
        mensagemErro = ""
        et_senha.setText("")
    }

    private fun initActions() {
        btn_cancelar.setOnClickListener {
//            limparControles()
        }
        btn_login.setOnClickListener {
//            if (validacao()) {
//                exibirCredenciais()
//            } else {
//                exibirErro()
//            }
        }
    }
}
