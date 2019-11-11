package br.com.impacta.kotlin.hellofragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val frag1 = HelloFragment()
            fragmentTransaction.add(R.id.layoutFrag, frag1, "HelloFragment")
            fragmentTransaction.commit()
        }
    }
}
