package br.com.impacta.kotlin.helloservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import java.lang.System.out

object NotificationUtil {
    internal val CHANNEL_ID = "1"

    fun createChannel(context: Context){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appName = context.getString(R.string.app_name)
            val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_DEFAULT)
            c.lightColor = Color.BLUE
            c.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            manager.createNotificationChannel(c)
        }
    }

    fun create(context: Context, id : Int, intent: Intent, title : String, msg : String){
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, id.toString())
            .setContentIntent(p)
            .setContentTitle(title)
            .setContentText(msg)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        val n = builder.build()
        manager.notify(id, n)
    }


}
