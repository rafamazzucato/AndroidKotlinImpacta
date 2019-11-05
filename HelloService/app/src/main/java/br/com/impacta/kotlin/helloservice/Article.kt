package br.com.impacta.kotlin.helloservice

data class Article(
    val title: String = "",
    val body: String = "",
    val viewCount: Int = 0,
    val payWall: Boolean = false,
    val titleImage: String = ""
)