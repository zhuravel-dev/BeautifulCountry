package com.example.studyprojectrnc.musicPlayer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationManagerCompat
import com.example.studyprojectrnc.R

const val CHANNEL_ID_MUSIC = "app.MUSIC"
const val CHANNEL_NAME_MUSIC = "Music"
const val NOTIFICATION_ID_MUSIC = 101

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private var mNotificationManager: NotificationManager? = null
    private var playState = 0

    fun notification() {
        val mediaSession = MediaSession(this, "PlayerService")
        mediaSession.setPlaybackState(
            PlaybackState.Builder()
                .setState(
                    PlaybackState.STATE_PLAYING,
                    mediaPlayer.currentPosition.toLong(), 1.0f
                )
                .setActions(PlaybackState.ACTION_SEEK_TO)
                .build()
        )

        val noti = Notification.Builder(this@MusicService, CHANNEL_ID_MUSIC)
            .setSmallIcon(R.drawable.ic_pause)
            .setContentTitle("Track title")
            .setContentText("Artist - Album")
            .setStyle(
                Notification.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
            )
        with(NotificationManagerCompat.from(this))
        {
            notify(NOTIFICATION_ID_MUSIC, noti.build())
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.music)
        mediaPlayer.isLooping = false
        createNotificationChannel()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        mediaPlayer.start()
        notification()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val status = NotificationChannel(
                CHANNEL_ID_MUSIC,
                CHANNEL_NAME_MUSIC, NotificationManager.IMPORTANCE_LOW
            )
            status.description = "Music player"
            manager.createNotificationChannel(status)
        }
    }

    fun getPlayState(): Int = playState

    fun stop() {
        stopForeground(true)
        mNotificationManager = null
        stopSelf()
    }
}