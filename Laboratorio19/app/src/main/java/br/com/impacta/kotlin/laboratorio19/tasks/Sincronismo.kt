package br.com.impacta.kotlin.laboratorio19.tasks

import android.R
import android.content.Context
import android.os.AsyncTask
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import br.com.impacta.kotlin.laboratorio19.models.Contato
import br.com.impacta.kotlin.laboratorio19.models.Transmissao
import br.com.impacta.kotlin.laboratorio19.utils.Constantes
import br.com.impacta.kotlin.laboratorio19.utils.comunicacao
import com.google.gson.Gson

class Sincronismo(
    private val context : Context,
    private val listViewContatos : ListView)
    : AsyncTask<Void, Int, java.util.ArrayList<Contato>>() {

    override fun doInBackground(vararg void: Void): java.util.ArrayList<Contato>?
    {
        try {
            val auxContato = gerarContatos(3)
            val transmissao = Transmissao(auxContato)
            val json = Gson().toJson(transmissao)

            val resultado = comunicacao(
                Constantes.web_service,
                json
            )

            val auxResult = Gson().fromJson<Transmissao>(resultado, Transmissao::class.java)
            return auxResult.contatos
        } catch (e: Exception) {
            val res = e.toString()
        }
        return ArrayList<Contato>()
    }
    override fun onPostExecute(contatos: java.util.ArrayList<Contato>) {
        super.onPostExecute(contatos)
        listViewContatos.adapter = ArrayAdapter<Contato>(
            context,
            R.layout.simple_list_item_1,
            contatos
        ) as ListAdapter?
    }

    private fun gerarContatos(quantidade: Int): ArrayList<Contato> {
        val contatos = ArrayList<Contato>()

        for (i in 1..quantidade) {
            val cAux = Contato(i.toLong(), "Nome - " + i.toString(), i * 2)
            contatos.add(cAux)
        }

        return contatos
    }
}