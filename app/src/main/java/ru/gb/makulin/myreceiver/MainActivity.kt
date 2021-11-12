package ru.gb.makulin.myreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.gb.makulin.myreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val SENDED_MSG_EXTRA = "SENDED MSG EXTRA"
        const val INTENT_FILTER = "INTENT FILTER"
    }

    lateinit var binding: ActivityMainBinding

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.root.addView(TextView(context).apply {
                val msg = intent?.getStringExtra(SENDED_MSG_EXTRA)
                text = "Полученное сообщение: $msg"
            })
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver()
    }

    private fun registerReceiver() {
        this.registerReceiver(receiver, IntentFilter(INTENT_FILTER))
    }
}