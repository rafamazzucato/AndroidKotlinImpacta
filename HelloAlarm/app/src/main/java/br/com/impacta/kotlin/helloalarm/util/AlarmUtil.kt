package br.com.impacta.kotlin.helloalarm.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log

object AlarmUtil {


    fun schedule(context: Context, intent: Intent, triggerAtMiles : Long){
        getAlarm(context).set(AlarmManager.RTC_WAKEUP, triggerAtMiles, getPendingIntent(context, intent))
        Log.d(Constantes.TAG, "Alarme foi agendado com sucesso")
    }

    fun scheduleRepeat(context: Context, intent: Intent, triggerAtMiles : Long, inetervalMillis : Long){
        getAlarm(context).setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMiles, inetervalMillis, getPendingIntent(context, intent))
        Log.d(Constantes.TAG, "Alarme foi agendado com sucesso com Repeat")
    }

    fun cancel(context: Context, intent: Intent){
        getAlarm(context).cancel(getPendingIntent(context, intent))
        Log.d(Constantes.TAG, "Alarme cancelado com sucesso")
    }

    private fun getAlarm(context: Context) : AlarmManager{
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private fun getPendingIntent(context: Context, intent: Intent) : PendingIntent {
        return PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}