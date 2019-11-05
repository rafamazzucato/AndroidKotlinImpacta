package br.com.impacta.kotlin.helloservice

import android.app.IntentService
import android.content.Intent
import android.util.Log

class HelloService : IntentService("LivroAndroid") {

    private var count = 0
    private var running = false
    private val MAX = 10
    private val TAG = "LivroAndroid"

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, " >> HelloService.onHandleIntent()")
        running = true

        while(running && count < MAX){
            Thread.sleep(1000)
            Log.d(TAG, " >> HelloService executando... ${count}")
            count++
        }

        Log.d(TAG, " << HelloService.onHandleIntent()")

        val intent = Intent(this, MainActivity::class.java)
        NotificationUtil.create(this, 1, intent, "Livro Android", "Serviço Concluído")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, " >> HelloService.onDestroy()")
        running = false
    }
}
