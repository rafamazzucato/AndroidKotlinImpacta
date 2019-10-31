package br.com.impacta.kotlin.laboratorio16.models

import java.io.Serializable

data class Contato(
    val idContato : Int = -1,
    val nome : String,
    val telefone : String,
    val idade : Int)  : Serializable {
}