package br.com.impacta.kotlin.hellonotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotificationUtil.createChannel(this)

        btnCriarNotificacao.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            NotificationUtil.create(this, 1, intent, "livro android", "Hello Notificação")
        }
    }
}
