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


    lateinit var rvMain : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DB = DBHlpr(applicationContext)

        rvMain = findViewById<RecyclerView>(R.id.rvMain)
        val et1 = findViewById<EditText>(R.id.et1)
        val bEnter = findViewById<Button>(R.id.bEnter)
        //var arr = DB.getData()
        bEnter.setOnClickListener {
            if(et1.text.isNotEmpty()){
                var state = DB.putData(NoteDetails(0,et1.text.toString()))
                rvMain.adapter = RecyclerViewAdapter(this,this ,DB.getData())
                Toast.makeText(applicationContext,"Data saved successfully !\n$state",Toast.LENGTH_SHORT).show()
                et1.text.clear()
            }else{
                Toast.makeText(this,"NO VALUE",Toast.LENGTH_SHORT).show()
            }
        }
        rvMain.adapter = RecyclerViewAdapter(this,this ,DB.getData())
        rvMain.layoutManager = LinearLayoutManager(this)
    }
    fun update(DB : DBHlpr){
        rvMain.adapter = RecyclerViewAdapter(this,this ,DB.getData())
    }
}