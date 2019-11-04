package br.com.impacta.kotlin.laboratorio16.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.impacta.kotlin.laboratorio16.R
import br.com.impacta.kotlin.laboratorio16.models.Contato
import kotlinx.android.synthetic.main.celula_contato.view.*

class ContatosAdapter(context:Context, val contatos : ArrayList<Contato>) : BaseAdapter() {

    /**
     * Responsavel pela conversao do arquivo de layout da celula de xml para
    binario
     */
    private val mInflater: LayoutInflater

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var result = convertView
        if (result == null) {
            result = mInflater.inflate(R.layout.celula_contato, parent, false)
        }

        val item = getItem(position)
        val model = SyntheticModel(result!!)

        model.contatoNome.text = item.nome
        model.contatoIdade.text = item.idade.toString()

        return result
    }

    class SyntheticModel(baseView: View) {
        val contatoNome = baseView.contatoNome
        val contatoIdade = baseView.contatoIdade
    }

    override fun getItem(position: Int): Contato {
        return contatos[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).idContato.toLong()
    }

    override fun getCount(): Int {
        return contatos.size
    }
}