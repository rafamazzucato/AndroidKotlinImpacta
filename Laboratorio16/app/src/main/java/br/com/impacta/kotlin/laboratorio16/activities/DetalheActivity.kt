package br.com.impacta.kotlin.laboratorio16.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.impacta.kotlin.laboratorio16.R
import br.com.impacta.kotlin.laboratorio16.daos.ContatoDAO
import br.com.impacta.kotlin.laboratorio16.extensions.exibirMensagem
import br.com.impacta.kotlin.laboratorio16.models.Contato
import br.com.impacta.kotlin.laboratorio16.utils.Constantes
import kotlinx.android.synthetic.main.tela_detalhes.*

class DetalheActivity : Laboratorio16AbstractActivity() {

    private val context: Context by lazy {
        this
    }
    private val contatoDAO: ContatoDAO by lazy {
        ContatoDAO(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_detalhes)

        initVars()
    }

    private fun initVars(){
        val contato = intent.getSerializableExtra(Constantes.PARAMETRO_ID) as? Contato
        if(contato != null){
            et_codigo.setText(contato.idContato.toString())
            et_nome.setText(contato.nome)
            et_telefone.setText(contato.telefone)
            et_idade.setText(contato.idade.toString())

            btn_apagar.text = getString(R.string.detalhes_rotulo_btn_apagar)
            btn_apagar.setOnClickListener { confirmarOperacao() }

            btn_salvar.text = getString(R.string.detalhes_rotulo_btn_atualizar)
            btn_salvar.setOnClickListener { salvar(true) }
            return
        }

        btn_salvar.setOnClickListener { salvar(false) }
        btn_apagar.setOnClickListener { limpar() }
    }

    private fun confirmarOperacao(){
        val builder = AlertDialog.Builder(this@DetalheActivity)

        builder.setTitle("Detalhe do Contato")
        builder.setMessage("Você tem certeza que quer apagar o contato?")
        builder.setPositiveButton("SIM"){dialog, which ->
            apagar()
        }

        builder.setNegativeButton("NÃO"){dialog,which ->

        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun salvar(isUpdate : Boolean) {
        val codigoStr = et_codigo.text.toString()
        val nome = et_nome.text.toString()
        val telefone = et_telefone.text.toString()
        val idadeStr = et_idade.text.toString()

        if(nome.trim() == "" || telefone.trim() == "" || idadeStr == ""
            ||(isUpdate == true && codigoStr.trim() == "")){
            exibirMensagem("Favor preencher todo formulário")
            return
        }

        val idade = idadeStr.toInt()
        val codigo = if(codigoStr.trim() != ""){
            codigoStr.toInt()
        }else{
            -1
        }
        val contato = Contato(codigo, nome, telefone, idade)

        val result:Long
        if(isUpdate){
            result = contatoDAO.alterarContato(contato).toLong()
        }else{
            result = contatoDAO.inserirContato(contato)
        }

        if(result > 0 ){
            exibirMensagem("Contato Salvo com Sucesso!")
            finish()
        }else{
            exibirMensagem("Ocorreu erro ao criar contato.")
        }
    }

    private fun limpar(){

        et_codigo.setText("")
        et_nome.setText("")
        et_telefone.setText("")
        et_idade.setText("")

        exibirMensagem("Limpando Formulario")
    }

    private fun apagar(){
        val codigoStr = et_codigo.text.toString()

        if(codigoStr.trim() == ""){
            exibirMensagem("Código não encontrado")
        }

        val codigo =  codigoStr.toInt()
        val result = contatoDAO.apagarContato(codigo)

        if(result > 0 ){
            exibirMensagem("Contato Apagado com Sucesso!")
            finish()
        }else{
            exibirMensagem("Ocorreu erro ao criar contato.")
        }
    }
}