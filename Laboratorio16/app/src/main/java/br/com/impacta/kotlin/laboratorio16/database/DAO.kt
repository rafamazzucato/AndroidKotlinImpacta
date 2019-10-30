package br.com.impacta.kotlin.laboratorio16.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.com.impacta.kotlin.laboratorio16.utils.Constantes

open class DAO {

    val dbHelper: SQLiteHelper
    val db: SQLiteDatabase

    constructor(context: Context){
        dbHelper = SQLiteHelper(
            context,
            Constantes.BANCO,
            null,
            Constantes.VERSAO
        )
        db = dbHelper.writableDatabase
    }
}