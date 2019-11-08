package br.com.impacta.kotlin.helloalarm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.impacta.kotlin.helloalarm.R
import br.com.impacta.kotlin.helloalarm.receivers.LembreMeDeComerReceiver
import br.com.impacta.kotlin.helloalarm.util.AlarmUtil
import br.com.impacta.kotlin.helloalarm.util.NotificationUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val intentLembreme : Intent by lazy {
        Intent(this, LembreMeDeComerReceiver::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotificationUtil.createChannel(this)

        btnAgendar5s.setOnClickListener{ agendar() }
        btnAgendarRepeat30s.setOnClickListener { agendarComRepeat() }
        btnCancelar.setOnClickListener { cancelar() }
    }

    private fun cancelar() {
        AlarmUtil.cancel(this, intentLembreme)
    }

    private fun agendarComRepeat() {
        AlarmUtil.scheduleRepeat(this, intentLembreme, getTime(), 1000*10)
    }

    private fun agendar() {
        AlarmUtil.schedule(this, intentLembreme, getTime())
    }

    private fun getTime() : Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 5)

        return calendar.timeInMillis
    }
}
