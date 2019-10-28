package br.com.impacta.kotlin.laboratorio14.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.impacta.kotlin.laboratorio14.R
import br.com.impacta.kotlin.laboratorio14.utils.HM
import br.com.impacta.kotlin.laboratorio14.utils.HMAux
import kotlinx.android.synthetic.main.celula.*
import kotlinx.android.synthetic.main.celula.view.*


class AcoesAdapter (private val context : Context, private val resource: Int,
                    private val dados: List<HM>) : BaseAdapter(){

    /**
     * Responsavel pela conversao do arquivo de layout da celula de xml para
    binario
     */
    private val mInflater: LayoutInflater

    /**
     * Indica o id do item selecionado
     */
    private var selecteds = mutableListOf<Long>()

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = mInflater.inflate(resource, parent, false)
        }

        val item = getItem(position)
        val model = SyntheticModel(convertView!!)

        model.imageViewCelula.setImageResource(item.imagemResource)
        model.textViewCelula.text = item.acao

        if (selecteds.contains(item.id)){
            model.linear.background = context.getDrawable(R.drawable.fundo_selected)
        } else {
            model.linear.background = context.getDrawable(R.drawable.fundo_unselected)
        }
        return convertView
    }

    class SyntheticModel(baseView: View) {
        val imageViewCelula = baseView.imageViewCelula
        val textViewCelula = baseView.textViewCelula
        val linear = baseView.linearCelulaFundo
    }

    override fun getItem(position: Int): HM {
        return dados[position]
    }

    override fun getItemId(position: Int): Long {
        val item = getItem(position)
        return item.id
    }

    override fun getCount(): Int {
        return dados.size
    }

    /**
     * Faz a marcacao e a desmarcacao do registro conforme a sua selecao.
     */
    fun setSelectedId(id: Long) {
        if (selecteds.contains(id)){
            selecteds.remove(id)
        } else {
            selecteds.add(id)
        }
        notifyDataSetChanged()
    }
}