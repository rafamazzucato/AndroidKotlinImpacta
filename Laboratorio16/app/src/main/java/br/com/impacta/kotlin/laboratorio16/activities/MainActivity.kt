package br.com.impacta.kotlin.laboratorio16.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import br.com.impacta.kotlin.laboratorio16.R
import br.com.impacta.kotlin.laboratorio16.adapters.ContatosAdapter
import br.com.impacta.kotlin.laboratorio16.daos.ContatoDAO
import br.com.impacta.kotlin.laboratorio16.models.Contato
import br.com.impacta.kotlin.laboratorio16.utils.Constantes
import kotlinx.android.synthetic.main.tela_inicial.*

class MainActivity : Laboratorio16AbstractActivity() {

    private val context: Context by lazy {
        this
    }
    private val contatoDAO: ContatoDAO by lazy {
        ContatoDAO(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)
    }

    override fun onResume() {
        super.onResume()
        initVars()
        initActions()
    }

    private fun initVars() {
        val contatos = contatoDAO.obterListaContatos()

        listaContatos.adapter = ContatosAdapter(
            context,
            contatos
        )
    }

    private fun initActions() {
        listaContatos.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val contato = parent.getItemAtPosition(position) as Contato
            chamarTD(contato)
        }
    }
}
