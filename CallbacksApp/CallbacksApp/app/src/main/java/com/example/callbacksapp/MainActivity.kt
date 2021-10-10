package com.example.callbacksapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val TAG = "Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate: ")
        var b = findViewById<Button>(R.id.button)
        b.setOnClickListener {
            Toast.makeText(this,"onCreate Activity 1",Toast.LENGTH_SHORT).show()
            var intent = Intent(this , MainActivity2::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"onResume Activity 1",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"onResume: ")
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart Activity 1",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"onStart: ")
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart Activity 1",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"onRestart: ")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this,"onStop Activity 1",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this,"onPause Activity 1",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"onDestroy Activity 1",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"onDestroy: ")
    }

}