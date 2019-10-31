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

class MainActivity : AppCompatActivity() {

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.actionhugo_novo_contato) {
            chamarTD(-1L)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initVars() {
        val contatos = contatoDAO.obterListaContatos()

        listaContatos.adapter = ContatosAdapter(
            context,
            contatos
        )
    }

    private fun initActions() {
//        listaContatos.onItemClickListener = AdapterView.OnItemClickListener {
//                parent, view, position, id ->
//            val hmAux = parent.getItemAtPosition(position) as HMAux
//            chamarTD(java.lang.Long.parseLong(hmAux[HMAux.ID]!!))
//        }
    }

    private fun chamarTD(id: Long) {
        val mIntent = Intent(context, DetalheActivity::class.java)
        mIntent.putExtra(Constantes.PARAMETRO_ID, id)
        startActivity(mIntent)
    }
}
