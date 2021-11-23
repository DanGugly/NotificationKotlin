package com.example.notificationkotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import androidx.core.app.NotificationManagerCompat
import com.example.notificationkotlin.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding

    private val CHANNEL_ID = "channel_id"
    private val notificationID = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        // createNotificationChannel()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = intent.getStringExtra("DATA") ?: ""
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(data: String?){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("YOUR DATA BELOW:")  //Title of notification
            .setContentText(data)
            .setPriority(PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationID, builder.build())
        }
    }

    override fun onResume() {
        super.onResume()
        //Best practise for creating notification channel in onresume as we are sure user is accessing the app
        createNotificationChannel()
        val text:String = intent.getStringExtra("DATA") ?: ""
        Log.d("Activity2", "Entered activity 2, DATA: $text")

        binding.button.setOnClickListener{
            Toast.makeText(baseContext,"Sending notification: $text" ,Toast.LENGTH_LONG).show()
            sendNotification(text)
        }
    }
}