package br.com.impacta.kotlin.laboratorio13.utils

import java.lang.NumberFormatException
import java.text.ParseException

/**
 * Metodo para fazer a validacao da Idade
 */
fun convertIdade(idade: String): Int {
    try {
        val idadeAux = Integer.parseInt(idade)
        return if (idadeAux <= 150 && idadeAux >= 5) {
            idadeAux
        } else {
            -2
        }
    } catch (e: Exception) {
        return -1
    }
}

/**
 * Metodo que elimina espacos em branco para uma possivel gravacao em banco de
dados
 */
fun String.formatacaoBD(texto: String = this): String {
    return texto.trim()
}