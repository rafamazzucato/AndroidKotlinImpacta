package br.com.impacta.kotlin.laboratorio16.models

data class Contato(
    val idContato : Long = -1,
    val nome : String,
    val telefone : String,
    val idade : Int) {
}