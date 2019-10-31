package br.com.impacta.kotlin.laboratorio16.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import br.com.impacta.kotlin.laboratorio16.utils.Constantes

class SQLiteHelper(context : Context) :
    SQLiteOpenHelper(context, Constantes.BANCO, null, Constantes.VERSAO) {

    private val TAG = "SQLiteHelper"

    override fun onCreate(db: SQLiteDatabase) {
        try{
            val sb = StringBuilder()

            sb.append(
                    "CREATE TABLE IF NOT EXISTS [contatos] ( " +
                    " [idcontato] INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " [nome] TEXT NOT NULL, " +
                    " [telefone] TEXT NOT NULL, " +
                    " [idade] INT NOT NULL ); "
            )

            executeSQL(sb, db)
        } catch(exception : Exception){
            Log.e(TAG, exception.message, exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            val sb = StringBuilder()

            sb.append("DROP TABLE IF EXISTS [contatos];")

            executeSQL(sb, db)
            onCreate(db)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }

    private fun executeSQL(sb : StringBuilder, db: SQLiteDatabase){
        val comandos = sb.toString().split(";".toRegex()).dropLastWhile {
            it.isEmpty() }.toTypedArray()

        for (i in comandos.indices) {
            db.execSQL(comandos[i].toLowerCase())
        }
    }
}