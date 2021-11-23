package com.example.notificationkotlin

import android.icu.text.CaseMap
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.lang.Exception

class MyFirebaseService : FirebaseMessagingService() { //Extend firebase messaging service

    private val notManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(baseContext)
    }

    //Constructing firebase service
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        // This method updates the token to talk with firebase
        Log.d(TAG, "new token.... $p0")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try {
            //Check for a null notification
            remoteMessage.notification?.let {
                showNotification(it.title, it.body)
            } ?:
            // If notification is null we show message from data
            showNotification(remoteMessage.data["title"], remoteMessage.data["message"])
            //showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        } catch (e : Exception){
            Log.d(TAG, e.stackTraceToString())
        }
    }

    private fun showNotification(title: String?, body: String?){
        //Contains all notification logic, get title and text from msg received
        NotificationCompat.Builder(baseContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)  //Title of notification
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
            .apply {
                //Here we notify for the new notification through the manager
                //apply block will allow us to handle this action with 'this' as the notification value
                notManager.notify(NOT_ID, this)
            }
    }

    companion object{
        // Tag for logcat
        const val TAG="FirebaseService"
        //Var for notification ID
        const val NOT_ID = 432
        const val CHANNEL_ID="channel_id"
    }
}