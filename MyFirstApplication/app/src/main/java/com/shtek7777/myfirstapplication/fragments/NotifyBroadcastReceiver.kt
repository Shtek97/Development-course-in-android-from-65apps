package com.shtek7777.myfirstapplication.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.core.app.NotificationCompat
import com.shtek7777.myfirstapplication.MainActivity
import com.shtek7777.myfirstapplication.R

class NotifyBroadcastReceiver : BroadcastReceiver() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context, intent: Intent) {

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                context.getString(R.string.notify_channel_id),
                context.getString(R.string.notify_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.putExtra(FRAGMENT_ID, CONTACTS_DETAILS_LAYOUT);

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification =
            NotificationCompat.Builder(context, context.getString(R.string.notify_channel_id))
                .setContentTitle(context.getString(R.string.app_name))
                .setChannelId(context.getString(R.string.notify_channel_id))
                .setContentText(
                    context.getString(R.string.text_notify_birthday)
                            + intent.getStringExtra(CONTACT_NAME)
                )
                .setSmallIcon(android.R.drawable.ic_menu_my_calendar)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

        val contactId = intent.getIntExtra(CONTACT_ID, 0)

        notificationManager.notify(contactId, notification)

        val birthday = calendarBirthday(Calendar.getInstance())

        val reschedulePendingIntent = PendingIntent.getBroadcast(
            context,
            contactId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            birthday,
            reschedulePendingIntent
        )
    }

    companion object {
        const val CONTACT_ID = "CONTACT_ID"
        const val CONTACT_NAME = "CONTACT_NAME"
        const val FRAGMENT_ID = "FRAGMENT_ID"
        const val CONTACTS_DETAILS_LAYOUT = "contactDetailsLayout"
    }
}