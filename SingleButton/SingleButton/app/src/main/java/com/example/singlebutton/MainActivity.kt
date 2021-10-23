package com.example.singlebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Implemented the button using Kotlin code
        val bKotlin = findViewById<Button>(R.id.bKotlin)
        bKotlin.setOnClickListener {
            Toast.makeText(this,"KOTLIN",Toast.LENGTH_SHORT).show()
        }

    }
    //----------------------------------------------------------------------------------------------
    //  Implemented the button using XML
    fun bXML(v : View){
        Toast.makeText(this,"XML",Toast.LENGTH_SHORT).show()
    }
}