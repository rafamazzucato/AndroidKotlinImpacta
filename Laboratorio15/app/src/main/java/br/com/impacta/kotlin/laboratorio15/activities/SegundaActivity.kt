package br.com.impacta.kotlin.laboratorio15.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.impacta.kotlin.laboratorio15.R
import br.com.impacta.kotlin.laboratorio15.tools.Constantes
import kotlinx.android.synthetic.main.segunda_tela.*

class SegundaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segunda_tela)

        val tipo =  intent.getIntExtra(Constantes.PARAMETRO_TIPO, -1)
        val valor = intent.getIntExtra(Constantes.PARAMETRO_VALOR, -1)

        initVars(tipo, valor)
        initActions(valor)
    }

    private fun initVars(tipo : Int, valor : Int) {

        if (tipo == Constantes.TIPO_SIMPLES) {
            btnMultiplicar.isEnabled = false
        } else {
            btnMultiplicar.isEnabled = true
        }
        tv_valor_original.text = valor.toString()
    }

    private fun initActions(valor : Int) {
        btnMultiplicar.setOnClickListener {
            val resultado_operacao = valor * Constantes.MULTIPLICAR_POR
            val data = Intent()

            data.putExtra(Constantes.PARAMETRO_RETORNO, resultado_operacao)

            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}