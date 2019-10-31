package br.com.impacta.kotlin.laboratorio16.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.impacta.kotlin.laboratorio16.R
import br.com.impacta.kotlin.laboratorio16.daos.ContatoDAO
import br.com.impacta.kotlin.laboratorio16.models.Contato
import kotlinx.android.synthetic.main.tela_detalhes.*

class DetalheActivity : AppCompatActivity() {

    private val context: Context by lazy {
        this
    }
    private val contatoDAO: ContatoDAO by lazy {
        ContatoDAO(context)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_detalhes)
        
        initActions()
    }

    private fun initActions() {
        btn_salvar.setOnClickListener {
            adicionar()
        }
    }

    private fun adicionar() {
        val nome = et_nome.text.toString()
        val telefone = et_telefone.text.toString()
        val idade = et_idade.text.toString().toInt()

        val contato = Contato(
            nome = nome, telefone = telefone, idade = idade
        )

        val result = contatoDAO.inserirContato(contato)
        finish()
    }
}