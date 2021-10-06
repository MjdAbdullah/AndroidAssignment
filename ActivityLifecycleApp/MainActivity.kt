package com.example.app1

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Activity Lifecycle: ")
        println("1 - onCreate")

    }

    override fun onStart() {
        super.onStart()
        println("2 - onStart")
    }

    override fun onResume() {
        super.onResume()
        println("3 - onResume")
    }

    override fun onPause() {
        super.onPause()
        println("4 - onPause")
    }

    override fun onStop() {
        super.onStop()
        println("5 - onStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("If user navigates to the activity")
        println("6 - onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("7 - onDestroy")
    }

}