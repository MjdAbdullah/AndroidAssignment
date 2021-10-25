package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DB = DBHlpr(applicationContext)

        val rvMain = findViewById<RecyclerView>(R.id.rvMain)
        val et1 = findViewById<EditText>(R.id.et1)
        val bEnter = findViewById<Button>(R.id.bEnter)

        bEnter.setOnClickListener {
            if(et1.text.isNotEmpty()){
                var state = DB.putData(et1.text.toString())
                rvMain.adapter = RecyclerViewAdapter(this,DB.getData())
                Toast.makeText(applicationContext,"Data saved successfully !\n$state",Toast.LENGTH_SHORT).show()
                et1.text.clear()
            }else{
                Toast.makeText(this,"NO VALUE",Toast.LENGTH_SHORT).show()
            }
        }

        rvMain.adapter = RecyclerViewAdapter(this,DB.getData())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}