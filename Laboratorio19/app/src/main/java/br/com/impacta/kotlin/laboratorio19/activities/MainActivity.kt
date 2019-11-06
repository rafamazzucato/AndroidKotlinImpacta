package br.com.impacta.kotlin.laboratorio19.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import br.com.impacta.kotlin.laboratorio19.R
import br.com.impacta.kotlin.laboratorio19.models.Contato
import br.com.impacta.kotlin.laboratorio19.models.Transmissao
import br.com.impacta.kotlin.laboratorio19.tasks.Sincronismo
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
        Sincronismo(this, lv_contatos).execute()
    }

}
