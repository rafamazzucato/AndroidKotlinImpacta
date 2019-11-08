package br.com.impacta.kotlin.helloalarm.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.impacta.kotlin.helloalarm.activities.MainActivity
import br.com.impacta.kotlin.helloalarm.util.Constantes
import br.com.impacta.kotlin.helloalarm.util.NotificationUtil
import java.util.*

class LembreMeDeComerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d(Constantes.TAG, "Você precisa comer: ${Date()}")
        val notifIntent = Intent(context, MainActivity::class.java)
        NotificationUtil.create(context, 1, notifIntent, "Hora de comer algo...", "Que tal uma fruta?")
    }
}