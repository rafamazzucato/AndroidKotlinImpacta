package br.com.impacta.kotlin.laboratorio15.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.impacta.kotlin.laboratorio15.R
import br.com.impacta.kotlin.laboratorio15.tools.Constantes
import kotlinx.android.synthetic.main.tela_inicial.*

class MainActivity : AppCompatActivity() {

    /**
     * Contexto para Utilizacao no Toast.
     */
    private val context: Context by lazy {
        this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

        initActions()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            Constantes.MULTIPLICAR_POR -> processarRM5(resultCode, data)
            else -> {
            }
        }
    }

    private fun initActions() {
        buttonChamadaSimples.setOnClickListener {
            chamadaSimples(it)
        }
        buttonChamadaRetorno.setOnClickListener {
            chamadaRetorno(it)
        }
    }

    private fun chamadaSimples(view : View){
        startActivity(initIntent(Constantes.TIPO_SIMPLES, 10))
        finish()
    }

    private fun chamadaRetorno(view : View){
        startActivityForResult(initIntent(Constantes.TIPO_RETORNO,20), Constantes.MULTIPLICAR_POR)
    }

    private fun initIntent(parametroTipo : Int, parametroValor : Int) : Intent {
        val mIntent = Intent(context, SegundaActivity::class.java)

        mIntent.putExtra(Constantes.PARAMETRO_TIPO, parametroTipo)
        mIntent.putExtra(Constantes.PARAMETRO_VALOR, parametroValor)

        return mIntent
    }

    private fun processarRM5(resultCode: Int, data: Intent?) {
        var resultado = ""

        if (resultCode == Activity.RESULT_OK && data != null) {
            resultado = data.getIntExtra(Constantes.PARAMETRO_RETORNO, -1).toString()
        } else {
            resultado = getString(R.string.telainicial_alerta_retorno_cancelado)
        }

        Toast.makeText(
            context,
            resultado,
            Toast.LENGTH_SHORT
        ).show()
    }

}
