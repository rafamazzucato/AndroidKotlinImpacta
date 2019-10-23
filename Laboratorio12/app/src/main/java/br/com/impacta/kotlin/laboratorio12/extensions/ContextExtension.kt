package br.com.impacta.kotlin.laboratorio12.extensions

import android.content.Context
import android.widget.Toast

fun Context.exibirMensagem(mensagem: String){
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}