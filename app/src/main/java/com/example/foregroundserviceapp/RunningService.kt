package com.example.foregroundserviceapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.app.NotificationCompat

class RunningService():Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action){
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }

        return START_STICKY
    }

    private fun start(){
        val notification = createNotification().build()
        startForeground(1,notification)
    }

    enum class Actions(){
        START,STOP
    }

    companion object NotificationChannelID{
        const val channelID = "running_channel"
    }

    private fun createNotification(): NotificationCompat.Builder {


        return NotificationCompat.Builder(this,NotificationChannelID.channelID)
            .setContentTitle("Service running")
            .setContentText("The foreground service is running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
    }

}