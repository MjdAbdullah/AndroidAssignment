package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et1 = findViewById<EditText>(R.id.et1)
        val bEnter = findViewById<Button>(R.id.bEnter)
        bEnter.setOnClickListener {
            if(et1.text.isNotEmpty()){
                val DB = DBHlpr(applicationContext)
                var state = DB.SaveValues(et1.text.toString())
                Toast.makeText(applicationContext,"Data saved successfully!\n$state",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"NO VALUE",Toast.LENGTH_SHORT).show()
            }
        }
    }
}