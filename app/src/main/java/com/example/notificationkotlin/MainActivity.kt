package com.example.notificationkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notificationkotlin.databinding.ActivityMain2Binding
import com.example.notificationkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.button.setOnClickListener {
            val intent = Intent(baseContext, MainActivity2::class.java)
                .apply {
                    putExtra("DATA", binding.editText.text.toString())
                    startActivity(this)
                }
        }
    }
}