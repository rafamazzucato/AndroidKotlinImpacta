package br.com.impacta.kotlin.laboratorio16.utils

fun converterIdade(idade: String): Int {
    try {
        val indice = Integer.parseInt(idade)
        return if (indice >= 5) indice else -1
    } catch (e: Exception) {
        return -1
    }
}