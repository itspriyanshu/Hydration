package com.example.demo.Services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

class NotificationServices private constructor (var context: Context) {




    companion object{
        var instance: NotificationServices? = null
        var channel_id: String = "1"
        var channel_name: String = "Test Channel"

        fun getInstance(context: Context): NotificationServices{
            if(instance == null){
                instance = NotificationServices(context)
            }
            return instance!!
        }
    }

    fun CreateAndRegisterChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.description = "New Testing Channel"
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
        }

    }
}