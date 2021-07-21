package com.example.studyprojectrnc.musicPlayer

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.media.session.PlaybackState
import android.net.Uri
import android.os.Build
import android.os.RemoteException
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.studyprojectrnc.R

class NotificationManager @Throws(RemoteException::class)
constructor(private val mService: MusicService) : BroadcastReceiver() {

    private var mNotificationManager: android.app.NotificationManager? = null
    private val mPlayIntent: PendingIntent
    private val mPauseIntent: PendingIntent
    private val mPreviousIntent: PendingIntent
    private val mNextIntent: PendingIntent
    private val mStopIntent: PendingIntent
    private var mRemoteViews: RemoteViews? = null
    private var mExpandedRemoteViews: RemoteViews? = null
    private var notificationBuilder: NotificationCompat.Builder? = null
    var mStarted = false

    private fun getPackageName(): String {
        return mService.packageName
    }

    init {
        mNotificationManager =
            mService.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

        mPauseIntent = PendingIntent.getBroadcast(
            mService, NOTIFICATION_REQUEST_CODE,
            Intent(ACTION_PAUSE).setPackage(getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT
        )
        mPlayIntent = PendingIntent.getBroadcast(
            mService, NOTIFICATION_REQUEST_CODE,
            Intent(ACTION_PLAY).setPackage(getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT
        )
        mPreviousIntent = PendingIntent.getBroadcast(
            mService, NOTIFICATION_REQUEST_CODE,
            Intent(ACTION_PREV).setPackage(getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT
        )
        mNextIntent = PendingIntent.getBroadcast(
            mService, NOTIFICATION_REQUEST_CODE,
            Intent(ACTION_NEXT).setPackage(getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT
        )
        mStopIntent = PendingIntent.getBroadcast(
            mService, NOTIFICATION_REQUEST_CODE,
            Intent(ACTION_STOP).setPackage(getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT
        )
        mNotificationManager?.cancelAll()
    }

    fun createMediaNotification() {
        val filter = IntentFilter().apply {
            addAction(ACTION_NEXT)
            addAction(ACTION_PAUSE)
            addAction(ACTION_PLAY)
            addAction(ACTION_PREV)
            addAction(ACTION_STOP)
        }
        mService.registerReceiver(this, filter)

        if (!mStarted) {
            mStarted = true
            mService.startForeground(NOTIFICATION_ID, generateNotification())
        }
    }


    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {

            ACTION_STOP -> {
                mService.run {
                    unregisterReceiver(this@NotificationManager)
                    stop()
                }
            }
        }
    }

    fun generateNotification(): Notification? {
        if (notificationBuilder == null) {
            notificationBuilder = NotificationCompat.Builder(mService, CHANNEL_ID)
            notificationBuilder?.setSmallIcon(R.drawable.ic_music_note)
                ?.setLargeIcon(
                    BitmapFactory.decodeResource(
                        mService.resources,
                        R.drawable.ic_music_note
                    )
                )
                ?.setContentTitle(mService.getString(R.string.app_name))
                ?.setContentText(mService.getString(R.string.app_name))
                ?.setDeleteIntent(mStopIntent)
                ?.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                ?.setCategory(NotificationCompat.CATEGORY_TRANSPORT)
                ?.setOnlyAlertOnce(true)
        }

        mRemoteViews =
            RemoteViews(getPackageName(), R.layout.player_notification)
        notificationBuilder?.setCustomContentView(mRemoteViews)
        mExpandedRemoteViews = RemoteViews(getPackageName(), R.layout.player_notification)
        notificationBuilder?.setCustomBigContentView(mExpandedRemoteViews)
        notificationBuilder?.setContentIntent(createContentIntent())
        notificationBuilder?.setOngoing(true)
        mExpandedRemoteViews?.let { createExpandedRemoteViews(it) }
        mNotificationManager?.notify(NOTIFICATION_ID, notificationBuilder?.build())
        if (mService.getPlayState() == PlaybackState.STATE_PLAYING ||
            mService.getPlayState() == PlaybackState.STATE_BUFFERING
        ) showPauseIcon() else showPlayIcon()
        return notificationBuilder?.build()
    }

    private fun createContentIntent(): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("player://")).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        return TaskStackBuilder.create(mService).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(NOTIFICATION_REQUEST_INTENT_CODE, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    private fun showPlayIcon() {
        mRemoteViews?.setViewVisibility(
            R.id.ivNotificationPause,
            View.GONE
        )
        mRemoteViews?.setViewVisibility(
            R.id.ivNotificationPlay,
            View.VISIBLE
        )
        mExpandedRemoteViews?.setViewVisibility(
            R.id.ivNotificationPause,
            View.GONE
        )
        mExpandedRemoteViews?.setViewVisibility(
            R.id.ivNotificationPlay,
            View.VISIBLE
        )
    }

    private fun showPauseIcon() {
        mRemoteViews?.setViewVisibility(
            R.id.ivNotificationPause,
            View.VISIBLE
        )
        mRemoteViews?.setViewVisibility(
            R.id.ivNotificationPlay,
            View.GONE
        )
        mExpandedRemoteViews?.setViewVisibility(
            R.id.ivNotificationPause,
            View.VISIBLE
        )
        mExpandedRemoteViews?.setViewVisibility(
            R.id.ivNotificationPlay,
            View.GONE
        )
    }

    private fun createExpandedRemoteViews(expandedRemoteViews: RemoteViews) {
        if (isSupportExpand) {
            expandedRemoteViews.setOnClickPendingIntent(
                R.id.ivSkip_back,
                mPreviousIntent
            )
            expandedRemoteViews.setOnClickPendingIntent(
                R.id.ivNotificationClear,
                mStopIntent
            )
            expandedRemoteViews.setOnClickPendingIntent(
                R.id.ivNotificationPause,
                mPauseIntent
            )
            expandedRemoteViews.setOnClickPendingIntent(
                R.id.ivNotificationSkipNext,
                mNextIntent
            )
            expandedRemoteViews.setOnClickPendingIntent(
                R.id.ivNotificationPlay,
                mPlayIntent
            )
            expandedRemoteViews.setImageViewResource(
                R.id.imageNotification,
                R.drawable.placeholder
            )
        }

        expandedRemoteViews.setViewVisibility(
            R.id.ivNotificationSkipNext,
            View.VISIBLE
        )
        expandedRemoteViews.setViewVisibility(
            R.id.ivSkip_back,
            View.VISIBLE
        )
    }

    companion object {
        private val TAG = NotificationManager::class.java.name
        private val isSupportExpand = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        private const val ACTION_PAUSE = "app.pause"
        private const val ACTION_PLAY = "app.play"
        private const val ACTION_PREV = "app.prev"
        private const val ACTION_NEXT = "app.next"
        private const val ACTION_STOP = "app.stop"
        private const val CHANNEL_ID = "app.MUSIC_CHANNEL_ID"
        private const val NOTIFICATION_ID = 412
        private const val NOTIFICATION_REQUEST_CODE = 100
        private const val NOTIFICATION_REQUEST_INTENT_CODE = 125245
    }
}