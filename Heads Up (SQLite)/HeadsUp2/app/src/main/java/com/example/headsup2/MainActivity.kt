package com.example.headsup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bStart =findViewById<Button>(R.id.bStart)
        val bAdd =findViewById<Button>(R.id.bAddActivity)
        bAdd.setOnClickListener {
            startActivity(Intent(this,Add::class.java))
        }
    }
}