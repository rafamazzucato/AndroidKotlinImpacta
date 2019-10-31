package br.com.impacta.kotlin.laboratorio16.models

data class Contato(
    val idContato : Int = -1,
    val nome : String,
    val telefone : String,
    val idade : Int) {
}