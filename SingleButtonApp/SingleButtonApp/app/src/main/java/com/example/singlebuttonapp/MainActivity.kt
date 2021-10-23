package com.example.singlebuttonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etText = findViewById<EditText>(R.id.etText)
        val bEnter = findViewById<Button>(R.id.bEnter)
        val tvText = findViewById<TextView>(R.id.tvText)

        bEnter.setOnClickListener {
            if(etText.text.isNotEmpty()){
                tvText.text = etText.text
                etText.clearFocus()
                etText.text.clear()
            }else{
                Toast.makeText(this,"Empty Value",Toast.LENGTH_SHORT).show()
            }
        }
    }
}