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
        val bUpdate_Delete = findViewById<Button>(R.id.bUpdate_Delete)

        bAdd.setOnClickListener {
            startActivity(Intent(this,Add::class.java))
        }
        bStart.setOnClickListener {
            startActivity(Intent(this,Start::class.java))
        }
        bUpdate_Delete.setOnClickListener {
            startActivity(Intent(this,UpdateDelete::class.java))
        }
    }
}