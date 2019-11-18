package br.com.impacta.kotlin.laboratorio24

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVars()
        initActions()
    }

    private fun initActions() {
        listContatos.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as HashMap<String, Any>
            configurarTelefone(item.get("contato") as Contato)
        }
    }

    private fun initVars() {
        if (temPermissaoLeituraContatos()) {
            criarListaContatos()
        } else {
            requisitaPermissaoAcessarContatos()
        }
    }

    private fun criarListaContatos() {
        var contatos = ArrayList<Contato>()

        val uriContatos = ContactsContract.Contacts.CONTENT_URI
        val order = ContactsContract.Contacts.DISPLAY_NAME

        // A informacao foi colocada no cursor
        var cursor = contentResolver.query(
            uriContatos, null, null, null,
            order
        )

        var codigo = ""
        var nome = ""
        var auxTemTelefone = ""
        var temTelefone = false
        var telefones = ""
        while(cursor.moveToNext()){
            codigo = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            auxTemTelefone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

            val uriTelefones = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

            val ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            val TELEFONE = ContactsContract.CommonDataKinds.Phone.NUMBER
            try {
                var cursor = contentResolver.query(
                    uriTelefones,
                    arrayOf(TELEFONE),
                    "$ID = ?",
                    arrayOf<String>(codigo), null
                )
                while (cursor!!.moveToNext()) {
                    telefones +=
                        cursor.getString(
                            cursor.getColumnIndex(TELEFONE)
                        )

                    if(!cursor.isLast){
                        telefones += "\n"
                    }
                }
                cursor.close()

            } catch (e: Exception) {
            }

            if(auxTemTelefone == "1"){
                temTelefone = true
            }

            contatos.add(Contato(codigo, nome, temTelefone, telefones))
        }

        cursor.close()

        val De = arrayOf("nome")
        val Para = intArrayOf(android.R.id.text1)

        var aux = ArrayList<Map<String, Any>>()
        var hm : HashMap<String, Any>

        contatos.forEach{
            hm = HashMap()
            hm.put("nome", it.nome)
            hm.put("contato", it)
            aux.add(hm)
        }

        listContatos.setAdapter(
            SimpleAdapter(
                this,
                aux,
                android.R.layout.simple_list_item_1,
                De,
                Para
            )
        )
    }

    private fun temPermissaoLeituraContatos(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        } else {
            return false
        }
    }
    private fun requisitaPermissaoAcessarContatos() {
        val permissions =
            arrayOf(
                android.Manifest.permission.READ_CONTACTS
            )
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    private fun configurarTelefone(item: Contato) {
        val telefones = StringBuilder()
        if (item.temTelefone) {
            telefones.append(item.telefones)
        } else {
            telefones.append("Não tem Telefone")
        }
        Toast.makeText(
            this,
            telefones.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }
}

