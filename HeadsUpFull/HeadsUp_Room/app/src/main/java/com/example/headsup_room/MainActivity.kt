package com.example.headsup_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var bStart: Button
    lateinit var bUpdate_Delete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bStart =findViewById<Button>(R.id.bStart)
        bUpdate_Delete = findViewById<Button>(R.id.bUpdate_Delete)

        bStart.setOnClickListener {
            val intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }
        bUpdate_Delete.setOnClickListener {
            val intent = Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }
    }

}
