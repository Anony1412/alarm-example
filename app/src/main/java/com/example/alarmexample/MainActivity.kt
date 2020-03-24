package com.example.alarmexample

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker.setIs24HourView(true)
    }

    fun startTiming(view: View) {
        val timeValue = timePicker.hour * 3600 + timePicker.minute * 60
        val intent = Intent(this, TimerReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            START_TIMING,
            intent,
            0
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + timeValue,
            pendingIntent
        )
        textView.text = "Alert after " + timePicker.hour + " hours " + timePicker.minute + " minutes!"
        textView.visibility = View.VISIBLE
    }

    fun stopTiming(view: View) {
        // do something
    }

    companion object {
        const val START_TIMING = 1234
    }
}
