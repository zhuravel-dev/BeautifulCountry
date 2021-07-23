package com.example.studyprojectrnc.musicPlayer

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studyprojectrnc.R
import java.util.*

class BackgroundSoundService : Service(), Observer {

    companion object {
        const val CHANNEL_ID_MUSIC = "app.MUSIC"
        const val CHANNEL_NAME_MUSIC = "Music"
        const val NOTIFICATION_ID_MUSIC = 101
        const val BROADCAST_ID_MUSIC = 201
        const val NOTIFICATION_PLAY = "notification.PLAY"
        const val NOTIFICATION_CANCEL = "notification.CANCEL"
        const val ACTION_PLAY = "action.PLAY"
        const val ACTION_PAUSE = "action.PAUSE"
        const val ACTION_STOP = "action.STOP"
    }

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var remoteView: RemoteViews
    private lateinit var intentPlay: PendingIntent
    private lateinit var intentCancel: PendingIntent
    private val metaRetriever = MediaMetadataRetriever()

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            when (intent?.action) {
                NOTIFICATION_PLAY -> {
                    if (mediaPlayer.isPlaying) {
                        pause(context)
                    } else {
                        play(context)
                    }
                }
                NOTIFICATION_CANCEL -> {
                    pause(context)
                    stopForeground(true)
                    stopSelf()
                }
            }
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        return LocalBinder()
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.music)
        mediaPlayer.isLooping = false
        mediaPlayer.start()
        initNotification()
    }

    fun play(context: Context) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.start()
        val imageView = ImageView(context).findViewById<ImageView>(R.id.ivNotificationPlay)
        imageView?.setImageResource(R.drawable.ic_play)
        initNotification()
    }

    private fun initNotification() {
        createNotificationChannel()
        initRemoteView()
        registerReceiver()
        startNotification()
    }

    fun pause(context: Context) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            val imageView = ImageView(context).findViewById<ImageView>(R.id.ivNotificationPlay)
            imageView?.setImageResource(R.drawable.ic_pause)
            initNotification()
        }
    }

    private fun registerReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(NOTIFICATION_PLAY)
        intentFilter.addAction(NOTIFICATION_CANCEL)
        registerReceiver(receiver, intentFilter)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val status = NotificationChannel(
                CHANNEL_ID_MUSIC,
                CHANNEL_NAME_MUSIC, NotificationManager.IMPORTANCE_LOW
            )
            status.description = "Music player"
            nm.createNotificationChannel(status)
        }
    }

    private fun startNotification(): Notification {
        remoteView.setOnClickPendingIntent(R.id.ivNotificationPlay, intentPlay)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID_MUSIC)
        notificationBuilder.setSmallIcon(R.drawable.ic_music_note)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .setCustomContentView(remoteView)
            .setCustomBigContentView(remoteView).priority = NotificationCompat.PRIORITY_DEFAULT

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID_MUSIC, notificationBuilder.build())
        }
        return notificationBuilder.build()
    }

    private fun initRemoteView() {
        remoteView = RemoteViews(packageName, R.layout.player_notification)
        remoteView.setImageViewResource(
            R.id.ivNotificationPlay,
            if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
        intentPlay = PendingIntent.getBroadcast(
            this, BROADCAST_ID_MUSIC,
            Intent(NOTIFICATION_PLAY).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        intentCancel = PendingIntent.getBroadcast(
            this, BROADCAST_ID_MUSIC,
            Intent(NOTIFICATION_CANCEL).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    override fun update(p0: Observable?, any: Any?) {
        when (any) {
            ACTION_PLAY, ACTION_PAUSE -> {
                startForeground(NOTIFICATION_ID_MUSIC, startNotification())
            }
            ACTION_STOP -> {
                stopForeground(true)
            }
        }
    }

    inner class LocalBinder : Binder() {
        val service: BackgroundSoundService = this@BackgroundSoundService
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        metaRetriever.release()
    }
}
