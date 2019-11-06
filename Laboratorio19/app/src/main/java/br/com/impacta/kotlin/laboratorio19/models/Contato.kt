package br.com.impacta.kotlin.laboratorio19.models

class Contato(
    var idcontato: Long = -1L,
    var nome: String = "sem nome",
    var idade: Int = -1) {

    // Capaz de retornar a representacao do contato na forma de texto
    override fun toString(): String {
        return nome + "-" + idade
    }
}