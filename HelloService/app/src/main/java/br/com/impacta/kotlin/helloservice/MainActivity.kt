package br.com.impacta.kotlin.helloservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, HelloService::class.java)

        NotificationUtil.createChannel(this)


        try {
            val json = """
   { "title": "Most elegant way of using Gson + Kotlin with default values and null safety",
     "body": null,
     "viewCount": 9999,
     "payWall": false,
     "ignoredProperty": "Ignored"
   }
"""
            val article = Gson().fromJson(json, Article::class.java)
            Log.d("ConversaoJSON", article.title)
        }catch (e : Exception){
            Log.e("ConversaoJSON", e.message, e)
        }
        btStart.setOnClickListener {
            startService(intent)
        }
        btStop.setOnClickListener {
            stopService(intent)
        }
    }
}
