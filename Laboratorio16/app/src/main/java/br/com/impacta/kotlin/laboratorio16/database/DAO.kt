package br.com.impacta.kotlin.laboratorio16.database

import android.content.Context

open class DAO {

    val dbHelper: SQLiteHelper

    constructor(context: Context){
        dbHelper = SQLiteHelper(
            context
        )
    }
}