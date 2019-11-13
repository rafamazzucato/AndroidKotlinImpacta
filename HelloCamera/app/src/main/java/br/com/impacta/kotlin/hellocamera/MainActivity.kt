package br.com.impacta.kotlin.hellocamera

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val TAG = "hellocamera"
    lateinit var file : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btAbrirCamera.setOnClickListener {
            file = capturaCaminhoFotoNoSD("foto.jpg")
            Log.d(TAG, "Camera File: $file")

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val uri =  FileProvider.getUriForFile(this,
                applicationContext.packageName + ".provider",
                file)

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, 0)
        }

        if(savedInstanceState != null){
            file = savedInstanceState.getSerializable("file") as File
            exibirImagem(file)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putSerializable("file", file)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "resultCode: $resultCode")

        if(resultCode == Activity.RESULT_OK){
            exibirImagem(file)
        }
    }

    private fun capturaCaminhoFotoNoSD(fileName : String) : File{
        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if(!sdCardDir.exists()){
            sdCardDir.mkdir()
        }

        val file = File(sdCardDir, fileName)
        return file
    }

    private fun exibirImagem(file: File) {
        if(file.exists()){
            val largura = imagem.width
            val altura = imagem.height

            val bitmap = ImagemUtils.redimensionar(file, largura, altura)
            imagem.setImageBitmap(bitmap)
        }
    }

}
