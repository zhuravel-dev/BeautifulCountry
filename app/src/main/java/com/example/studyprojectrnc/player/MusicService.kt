package com.example.studyprojectrnc.player

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
import com.example.studyprojectrnc.R

const val CHANNEL_ID_MUSIC = "app.MUSIC"
const val CHANNEL_NAME_MUSIC = "Music"

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    val mediaSession = MediaSession(this, "PlayerService")
    val mediaStyle = Notification.MediaStyle().setMediaSession(mediaSession.sessionToken)

    val notification = Notification.Builder(this@MusicService, CHANNEL_ID_MUSIC)
        .setStyle(mediaStyle)
        .build()

  /*   val pauseAction: Notification.Action = Notification.Action.Builder(pauseIcon, "Pause", pauseIntent
        ).build()
        notification.addAction(pauseAction)
*/

    override fun onBind(intent: Intent): IBinder?{
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
        notification.actions
        mediaSession.setPlaybackState(
            PlaybackState.Builder()
                .setState(
                    PlaybackState.STATE_PLAYING,
                    mediaPlayer.currentPosition.toLong(),
        //          playbackSpeed
                )
                .setActions(PlaybackState.ACTION_SEEK_TO)
                .build()
        )
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
}