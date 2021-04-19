package com.example.yearleft

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat

class Alarm : BroadcastReceiver() {
    var k =1;
    lateinit var notifificationcompatbuilder1 : NotificationCompat.Builder
    lateinit var notifificationcompatbuilder2 : NotificationCompat.Builder
    lateinit var notificationchannel : NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var vibrator : Vibrator

    companion object{
        var Gone_per ="100"
        var Left_per ="200"

    }
    override fun onReceive(context: Context?, intent: Intent?) {

        val pattern   = LongArray(4)
        pattern.set(0,0)
        pattern.set(1,380)
        pattern.set(2,220)
        pattern.set(3,380)

        Gone_per = intent?.getStringExtra("100")!!
        Left_per = intent.getStringExtra("200")!!
        cretachannel1(context!!)
        cretachannel2(context!!)
        notifificationcompatbuilder1= createbuilder(context!!)
        notifificationcompatbuilder2= createbuilder(context!!)
        notifificationcompatbuilder2.setContentTitle("Remaining").setColor(Color.RED).setContentText(Left_per).setChannelId(200.toString()).setSmallIcon(R.drawable.ic_baseline_hourglass_top_24)
        notificationManager.notify(100,notifificationcompatbuilder1.build())
        notificationManager.notify(200,notifificationcompatbuilder2.build())
    }

    private fun cretachannel1(context: Context) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(100.toString(),"Daily Notification Passed", IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(channel)
        vibrator=context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        startVibrator(context,vibrator)

    }
    private fun cretachannel2(context: Context) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(200.toString(),"Daily Notification Remaining", IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(channel)
    }
    private fun createbuilder(context: Context) : NotificationCompat.Builder {


        val  nnotifificationcompatbuilder = NotificationCompat.Builder(context,"100").setAutoCancel(true)
            .setOngoing(false)
            .setContentTitle("Passed")
            .setContentText(Gone_per)
           .setSmallIcon(R.drawable.ic_baseline_hourglass_disabled_24)
           .setColor(Color.GREEN)


        return nnotifificationcompatbuilder
    }

    private fun startVibrator(context: Context,vibratorr:Vibrator) {
        val pattern   = LongArray(4)
        pattern.set(0,0)
        pattern.set(1,380)
        pattern.set(2,220)
        pattern.set(3,380)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibratorr.vibrate(VibrationEffect.createWaveform(pattern,-1))
        }
    }

}

