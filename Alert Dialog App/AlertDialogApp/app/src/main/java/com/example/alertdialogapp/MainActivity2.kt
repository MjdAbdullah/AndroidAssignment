package com.example.alertdialogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tvText = findViewById<TextView>(R.id.tvText2)
        tvText.text = intent.getStringExtra("Value").toString()

    }
}