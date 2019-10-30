package br.com.impacta.kotlin.laboratorio16.daos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import br.com.impacta.kotlin.laboratorio16.database.DAO
import br.com.impacta.kotlin.laboratorio16.models.Contato

class ContatoDAO(context : Context) : DAO(context) {

    private val TAG = "ContatoDAO"

    companion object {
        val TABELA = "contatos"
        val IDCONTATO = "idcontato"
        val NOME = "nome"
        val TELEFONE = "telefone"
        val IDADE = "idade"
    }

    fun inserirContato(contato: Contato) : Long {
        val cv = parseContentValues(contato)
        return db.insert(TABELA, null, cv)
    }

    fun alterarContato(contato: Contato) : Int {
        val filtro = "$IDCONTATO = ? "
        val argumentos = arrayOf(contato.idContato.toString())
        val cv = parseContentValues(contato)

        return db.update(TABELA, cv, filtro, argumentos)
    }

    fun apagarContato(idcontato: Long) : Int {
        val filtro = "$IDCONTATO = ? "
        val argumentos = arrayOf(idcontato.toString())
        return db.delete(TABELA, filtro, argumentos)
    }

    fun obterContatoByID(idcontato: Long): Contato? {
        var cAux: Contato? = null

        try {
            val comando = " SELECT * FROM $TABELA WHERE $IDCONTATO = ? "
            val argumentos = arrayOf(idcontato.toString())
            val cursor = db!!.rawQuery(comando.toLowerCase(), argumentos)

            if(cursor.moveToFirst()){
                cAux = parseCursortoObject(cursor)
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }

        return cAux
    }

    fun obterListaContatos(): ArrayList<Contato> {
        val dados = ArrayList<Contato>()
        var cAux: Contato? = null
        try {
            val comando = " SELECT $IDCONTATO, $NOME, $IDADE, $TELEFONE FROM $TABELA ORDER BY $NOME "
            val cursor = db.rawQuery(comando.toLowerCase(), null)
            while (cursor.moveToNext()) {
                cAux = parseCursortoObject(cursor)

                if(cAux != null){
                    dados.add(cAux)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }

        return dados
    }

    private fun parseContentValues(contato : Contato) : ContentValues{
        val cv = ContentValues()

        if(contato.idContato != -1L){
            cv.put(IDCONTATO, contato.idContato)
        }

        cv.put(NOME, contato.nome)
        cv.put(TELEFONE, contato.telefone)
        cv.put(IDADE, contato.idade)

        return cv
    }

    private fun parseCursortoObject(cursor: Cursor) : Contato?{
        if(cursor.count > 0){
            val idContato = cursor.getLong(cursor.getColumnIndex(IDCONTATO))
            val nome = cursor.getString(cursor.getColumnIndex(NOME))
            val telefone = cursor.getString(cursor.getColumnIndex(TELEFONE))
            val idade = cursor.getInt(cursor.getColumnIndex(IDADE))

            return Contato(idContato, nome, telefone, idade)
        }
        return null
    }
}