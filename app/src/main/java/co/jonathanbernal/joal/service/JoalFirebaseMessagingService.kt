package co.jonathanbernal.joal.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import co.jonathanbernal.joal.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class JoalFirebaseMessagingService : FirebaseMessagingService()  {



    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("NotificationService", "el token es $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val from = remoteMessage.from
        Log.e("NotificationService", "From es : $from")
        if (remoteMessage.toIntent().extras != null) {
            val intent = remoteMessage.toIntent().extras
            val title = intent?.getString("title")
            val body = intent?.getString("text")

            showNotification(title, body)
        }
    }

    private fun showNotification(title: String?,body: String?) {

        val builder = NotificationCompat.Builder(this, "snap_joal_channel_01")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("snap_joal_channel_01", name, importance).apply {
                description = descriptionText
            }
            channel.enableLights(true)
            channel.lightColor = Color.GREEN
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            val random = (0..8000).random()
            notify(random, builder.build())
        }
    }

}