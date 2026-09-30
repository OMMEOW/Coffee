package com.coffeeshoptycoon.game

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CoffeeTycoonApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: androidx.hilt.work.HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)
            val offlineChannel = NotificationChannel(
                CHANNEL_OFFLINE_EARNINGS,
                getString(R.string.notification_channel_offline_earnings),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = getString(R.string.notification_channel_offline_earnings_desc)
            }
            val eventsChannel = NotificationChannel(
                CHANNEL_EVENTS,
                getString(R.string.notification_channel_events),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = getString(R.string.notification_channel_events_desc)
            }
            manager.createNotificationChannel(offlineChannel)
            manager.createNotificationChannel(eventsChannel)
        }
    }

    companion object {
        const val CHANNEL_OFFLINE_EARNINGS = "offline_earnings"
        const val CHANNEL_EVENTS = "events"
    }
}
