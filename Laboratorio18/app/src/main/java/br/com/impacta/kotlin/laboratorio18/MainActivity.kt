package br.com.impacta.kotlin.laboratorio18

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val context: Context by lazy {
        this
    }

    private var results = StringBuilder()

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActions()
    }

    private fun initActions(){
        btnGerarJson.setOnClickListener {
            gerarJson()
        }

        btnLerJson.setOnClickListener {
            lerJson()
        }
    }

    private fun gerarJson(){
        try {
            // gera um array de JSONs através do método mock que gera passando uma quantidade de registros
            val json = gson.toJson(gerarContatosJSON(20))

            // zera o builder anterior e adiciona a nova transmissão
            results = StringBuilder()
            results.append(json)

            textoJson.setText(json)
        } catch (e: Exception) {
            Log.e(getString(R.string.tag_json), e.message, e)
        }
    }

    private fun lerJson(){
        try {
            val listType = object : TypeToken<List<Contato>>() { }.type
            val contatos = gson.fromJson<List<Contato>>(results.toString(), listType)

            var resultado = ""
            for(contato in contatos){
                resultado += "${contato.nome} - ${contato.idade} \n"
            }

            textoResultado.setText(resultado)
        } catch (e: Exception) {
            val erro = e.toString()
            Toast.makeText(context, "Erro: $erro", Toast.LENGTH_SHORT).show()
        }
    }

    private fun gerarContatosJSON(quantidade: Int) : ArrayList<Contato> {
        val contatos = ArrayList<Contato>()

        for (i in 1..quantidade) {
            val cAux = Contato(i, "Nome - " + i.toString(), "",  i * 2)
            contatos.add(cAux)
        }
        return contatos
    }
}
