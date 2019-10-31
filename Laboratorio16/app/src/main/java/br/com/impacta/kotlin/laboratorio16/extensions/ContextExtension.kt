package br.com.impacta.kotlin.laboratorio16.extensions

import android.content.Context
import android.widget.Toast

fun Context.exibirMensagem(message : String){
    Toast.makeText(
       this,
        message,
        Toast.LENGTH_LONG
    ).show()
}