package com.example.task2mjd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var bGo : Button
    lateinit var edName : EditText
    lateinit var edEmil : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bGo = findViewById(R.id.button)
        edName = findViewById(R.id.editTextTextPersonName)
        edEmil = findViewById(R.id.editTextTextPersonName2)

        bGo.setOnClickListener {
            var name = edName.text.toString()
            var email = edEmil.text.toString()
            Toast.makeText(applicationContext, "$name $email", Toast.LENGTH_LONG).show()
        }
        edName.text.clear()
        edName.clearFocus()
        edEmil.text.clear()
        edEmil.clearFocus()
    }
}