package com.example.studyapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bJava = findViewById<Button>(R.id.bJava)
        val bKotlin = findViewById<Button>(R.id.bKotlin)

        bJava.setOnClickListener {
            val intent = Intent(this,Android::class.java)
            startActivity(intent)
        }
        bKotlin.setOnClickListener {
            val intent = Intent(this, Kotlin::class.java)
            startActivity(intent)
        }

    }
}