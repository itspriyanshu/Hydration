package com.example.demo.ViewModel

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.example.demo.MainActivity
import com.example.demo.R
import com.example.demo.Services.NotificationServices

class broadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        pushNotification(context)
    }
    fun pushNotification(context: Context?){
        val builder = NotificationCompat.Builder(context!!, NotificationServices.channel_id)

        val intent: Intent = Intent(context, MainActivity::class.java )
        val pendingIntent = PendingIntent.getActivity(context, 12, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentTitle("Drink Please!").setContentText("Its Time to drink a glass of water. Please go ahead and drink water.").setSmallIcon(
            R.drawable.ic_local_drink_grey_120px).setColor(Color.GRAY).setContentIntent(pendingIntent).setAutoCancel(true)


        var notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(12,builder.build())
    }
}