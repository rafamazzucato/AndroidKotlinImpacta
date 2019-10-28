package br.com.impacta.kotlin.laboratorio14.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.impacta.kotlin.laboratorio14.R
import br.com.impacta.kotlin.laboratorio14.adapters.AcoesAdapter
import br.com.impacta.kotlin.laboratorio14.extensions.exibirMensagem
import br.com.impacta.kotlin.laboratorio14.utils.HM
import kotlinx.android.synthetic.main.tela_inicial.*

class MainActivity : AppCompatActivity() {

    private var adapterAct: AcoesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

        initVars()
        initActions()
    }

    private fun initVars() {
        adapterAct = AcoesAdapter(
            this,
            R.layout.celula,
            gerarRegistros()
        )
        listViewActions.adapter = adapterAct
    }

    private fun initActions() {
        listViewActions.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as HM
            adapterAct?.setSelectedId(id)
            exibirMensagem(item.descricao)
        }
    }

    /**
     * Metodo que criara a nossa cole
     */
    private fun gerarRegistros(): ArrayList<HM> {
        val icons = intArrayOf(
            R.drawable.ic_beenhere_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_build_black_24dp,
            R.drawable.ic_business_center_black_24dp,
            R.drawable.ic_contact_phone_black_24dp
        )

        val dados = ArrayList<HM>()

        for (i in icons.indices) {
            val aux = HM(
                id =  i + 1L,
                acao = "Acao " + (i + 1).toString(),
                descricao =  "Descricao " + (i + 1).toString(),
                imagemResource =  icons[i]
            )
            dados.add(aux)
        }
        return dados
    }
}
