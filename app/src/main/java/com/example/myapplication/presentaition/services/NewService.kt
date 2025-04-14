package com.example.myapplication.presentaition.services

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Process
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.presentaition.services.NotificationService.Companion.CHANNEL_ID

class NewService : Service() {

    // declaring object of MediaPlayer
    private lateinit var player:MediaPlayer

    // execution of service will start
    // on calling this method
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Handler that receives messages from the thread
    override fun onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
           start()

            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)

        // providing the boolean
        // value as true to play
        // the audio on loop
        player.setLooping(true)


        // starting the process
        player.start()
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }

        // returns the status
        // of the program
        return START_STICKY
    }

    private fun sendCustomNotification(title: String, message: String) {

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(2, notification)
    }

    // execution of the service will
    // stop on calling this method
    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
        super.onDestroy()

        // stopping the process
        if (player.isPlaying) {
            player.stop()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
