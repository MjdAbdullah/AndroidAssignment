package com.example.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tvView)
    }

    override fun onStart() {
        super.onStart()
        tv.text = "I am online"
    }

    override fun onStop() {
        super.onStop()
        tv.text = "${Calendar.getInstance().time}"
    }

    override fun onRestart() {
        super.onRestart()
        tv.text = "I am online"
    }

    override fun onPause() {
        super.onPause()
        tv.text = "${Calendar.getInstance().time}"
    }
}