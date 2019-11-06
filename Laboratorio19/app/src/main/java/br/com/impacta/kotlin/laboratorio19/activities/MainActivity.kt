package br.com.impacta.kotlin.laboratorio19.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import br.com.impacta.kotlin.laboratorio19.R
import br.com.impacta.kotlin.laboratorio19.models.Contato
import br.com.impacta.kotlin.laboratorio19.models.Transmissao
import br.com.impacta.kotlin.laboratorio19.utils.Constantes
import br.com.impacta.kotlin.laboratorio19.utils.comunicacao
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Sincronismo().execute()
    }

    private inner class Sincronismo : AsyncTask<Void, Int, java.util.ArrayList<Contato>>() {
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
            lv_contatos.adapter = ArrayAdapter<Contato>(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                contatos
            ) as ListAdapter?
        }
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
