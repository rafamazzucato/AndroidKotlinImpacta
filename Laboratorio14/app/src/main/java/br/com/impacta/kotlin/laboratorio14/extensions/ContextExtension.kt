package br.com.impacta.kotlin.laboratorio14.extensions

import android.content.Context
import android.widget.Toast

/**
 * Metodo para exibicao de mensagens ao usuario
 */
fun Context.exibirMensagem(mensagem: String?) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}