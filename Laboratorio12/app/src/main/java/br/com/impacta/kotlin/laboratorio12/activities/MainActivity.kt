package br.com.impacta.kotlin.laboratorio12.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import br.com.impacta.kotlin.laboratorio12.R
import br.com.impacta.kotlin.laboratorio12.extensions.exibirMensagem
import kotlinx.android.synthetic.main.tela_inicial_activicty.*

class MainActivity : AppCompatActivity() {

    private var context: Context? = null
    private var mensagemErro: String = ""

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
        et_nome.setText("")
        et_nome.requestFocus()
    }

    private fun initActions() {
        btn_cancelar.setOnClickListener() {
            cancelar(it)
        }
        btn_login.setOnClickListener {
            if (validacao()) {
                exibirCredenciais()
            } else {
                exibirErro()
            }
        }
    }

    private fun cancelar(view : View){
        if(view is AppCompatButton){

            val background = view.background

            view.text = "Processando"
            view.isEnabled = false
            view.setBackgroundColor(getColor(R.color.colorAccent))

            Thread {
                initVars()
                Thread.sleep(1500)

                runOnUiThread{
                    view.text = getString(R.string.rotulo_btn_cancelar)
                    view.isEnabled = true
                    view.background = background
                }
            }.start()
        }
    }

    private fun validacao() : Boolean {
        var resultado = true
        var erro = ""
        val nomeDigitado = et_nome.text.toString().trim()
        val senhaDigitada = et_senha.text.toString().trim()

        if(nomeDigitado.isEmpty()){
            erro += getString(R.string.alerta_erro_nome_obrigatorio) + "\n"
            resultado = false
        }

        if(senhaDigitada.isEmpty()){
            erro += getString(R.string.alerta_erro_senha_obrigatoria) + "\n"
            resultado = false
        }

        if(resultado == true && nomeDigitado != "Android" && senhaDigitada != "A123"){
            erro = getString(R.string.alerta_erro_credenciais_invalidas) + "\n"
            resultado = false
        }

        if(!erro.isEmpty()){
            mensagemErro = erro.substring(0, erro.length-2)
        }

        return resultado
    }

    private fun exibirCredenciais(){
        exibirMensagem(getString(R.string.alerta_credenciais_validas))
    }

    private fun exibirErro(){
        if(!mensagemErro.isEmpty()){
            exibirMensagem(mensagemErro)
        }
    }

}
