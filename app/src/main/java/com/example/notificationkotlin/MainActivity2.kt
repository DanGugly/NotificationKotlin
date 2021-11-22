package com.example.notificationkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notificationkotlin.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val text:String = intent.getStringExtra("DATA") ?: ""
        Log.d("Activity2", "Entered activity 2, DATA: $text")

        binding.button.setOnClickListener{
            Toast.makeText(baseContext,"Sending notification: $text" ,Toast.LENGTH_LONG).show()
        }
    }
}