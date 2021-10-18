package com.example.buttoncounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textView)
        val bIncrease = findViewById<Button>(R.id.bIncrease)
        bIncrease.setOnClickListener {
            val increase = tv.text.toString().toInt() + 1
            tv.text = increase.toString()
        }

    }
}