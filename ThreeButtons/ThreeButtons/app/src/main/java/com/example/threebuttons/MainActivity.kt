package com.example.threebuttons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var et1 :EditText
    lateinit var et2 :EditText
    lateinit var et3 :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        et3 = findViewById(R.id.et3)
        val tvTest = findViewById<TextView>(R.id.tvText)
        val bGo = findViewById<Button>(R.id.bGo)
        val bToast = findViewById<Button>(R.id.bToast)
        val bTv = findViewById<Button>(R.id.bTv)

        bGo.setOnClickListener {
            if (CheckEmpty()){
                var intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("value","${et1.text}\n${et2.text}\n${et3.text}")
                startActivity(intent)
            }
        }

        bToast.setOnClickListener {
            if (CheckEmpty()){
                Toast.makeText(this,"${et1.text}\n${et2.text}\n${et3.text}",Toast.LENGTH_SHORT).show()
            }
        }

        bTv.setOnClickListener {
            if (CheckEmpty()){
                tvTest.text = "${et1.text}\n${et2.text}\n${et3.text}"
            }
        }

    }

    fun CheckEmpty() : Boolean{
        if(et1.text.isNotEmpty() && et2.text.isNotEmpty() && et3.text.isNotEmpty()){
            return true
        }
        return false
    }
}