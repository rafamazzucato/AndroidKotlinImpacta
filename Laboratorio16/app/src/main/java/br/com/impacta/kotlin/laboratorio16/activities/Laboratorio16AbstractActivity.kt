package br.com.impacta.kotlin.laboratorio16.activities

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.impacta.kotlin.laboratorio16.R
import br.com.impacta.kotlin.laboratorio16.utils.Constantes

open class Laboratorio16AbstractActivity : AppCompatActivity(){

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.actionhugo_novo_contato) {
            chamarTD(-1)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun chamarTD(id: Int) {
        val mIntent = Intent(this, DetalheActivity::class.java)
        mIntent.putExtra(Constantes.PARAMETRO_ID, id)
        startActivity(mIntent)
    }
}