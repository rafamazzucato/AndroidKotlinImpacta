package br.com.impacta.kotlin.laboratorio13.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.impacta.kotlin.laboratorio13.R
import br.com.impacta.kotlin.laboratorio13.extensions.exibirMensagem
import br.com.impacta.kotlin.laboratorio13.utils.convertIdade
import br.com.impacta.kotlin.laboratorio13.utils.formatacaoBD
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var context : Context = this
    private var mensagemErro : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVars()
        initActions()
    }

    /**
     * Inicializacao da minhas variaveis
     */
    private fun initVars() {
        context = this
        textNome.setText("")
        inputIdade.setText("")
        checkboxSP.isChecked = false
    }

    /**
     * Inicializacao da acao do Botao de Analise de Crédito
     */
    private fun initActions() {
        btnAnalisarCredito.setOnClickListener { executaAnalise() }
    }

    private fun executaAnalise(){
        val nome = textNome.text.toString().formatacaoBD()
        val idadeStr = inputIdade.text.toString().formatacaoBD()

        if (validacao(nome, idadeStr)) {
            analiseCredito(nome, idadeStr)
        } else {
            exibirErro()
        }
    }

    /**
     * Analisa se o preenchimento do formulário é valido
     */
    private fun validacao(nome: String, idadeStr : String): Boolean {

        if (nome.isEmpty()) {
            mensagemErro = getString(R.string.alerta_erro_nome_obrigatorio)
            return false
        }

        when (convertIdade(idadeStr)) {
            -1 -> {
                mensagemErro = getString(R.string.alerta_erro_idade_obrigatoria)
                return false
            }
            -2 -> {
                mensagemErro = getString(R.string.alerta_erro_idade_invalida)
                return false
            }
            else -> {
                return true
            }
        }
    }

    /**
     * Exibe os erros detectados para o Usuário
     */
    private fun exibirErro() {
        exibirMensagem(mensagemErro)
    }

    /**
     * Faz a analise de Credito
     */
    private fun analiseCredito(nome: String, idadeStr : String) {
        val idade = convertIdade(idadeStr)
        var credito = 0.0

        if (checkboxSP.isChecked) {
            if (idade >= 25) {
                credito = 5000.0
            } else {
                credito = 1000.0
            }
        } else {
            if (idade >= 25) {
                credito = 500.0
            }
        }
        exibirMensagem("$nome teve $credito liberado")
    }
}
