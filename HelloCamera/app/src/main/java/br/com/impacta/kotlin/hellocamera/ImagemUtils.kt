package br.com.impacta.kotlin.hellocamera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

object ImagemUtils {

    fun redimensionar(arquivo:File,  larguraView : Int, alturaView: Int) : Bitmap {
        val opcoes = BitmapFactory.Options()
        opcoes.inJustDecodeBounds = true
        BitmapFactory.decodeFile(arquivo.absolutePath, opcoes)

        opcoes.inSampleSize = calcularTamanhoRedimencionadoPadrao(opcoes, larguraView, alturaView)

        opcoes.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(arquivo.absolutePath, opcoes)
    }

    private fun calcularTamanhoRedimencionadoPadrao(opcoes: BitmapFactory.Options, larguraView: Int, alturaView: Int): Int {
        val alturaFoto = opcoes.outHeight
        val larguraFoto = opcoes.outWidth
        var tamanhoPadrao = 1

        if(alturaFoto > alturaView || larguraFoto > larguraView){
            val metadeAltura = alturaFoto / 2 //500
            val metadeLargura = larguraFoto / 2 //250

            // altura 200
            // largura 100

            while(metadeLargura / tamanhoPadrao >= larguraView &&
                    metadeAltura / tamanhoPadrao >= alturaView){
                tamanhoPadrao *= 2
            }
        }

        return tamanhoPadrao
    }
}