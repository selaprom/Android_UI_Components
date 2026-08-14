package kh.com.sela.android.topbartype.service.firebase

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kh.com.sela.android.topbartype.R

object NotificationUtil {
     const val CHANNEL_ID = "default_channel"

    /**
     * @param context
     */
    fun createNotificationChannel(context: Context) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, "Default Channel", importance).apply {
            description = "This is default channel"
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * @param context
     * @param title
     * @param message
     */
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun sendNotification(
        context: Context,
        title: String,
        message: String
    ) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(context).notify(101001, builder.build())
    }
}