package com.example.headsup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etName = findViewById<EditText>(R.id.etName)
        val etT1 = findViewById<EditText>(R.id.etT1)
        val etT2 = findViewById<EditText>(R.id.etT2)
        val etT3 = findViewById<EditText>(R.id.etT3)
        val bAdd = findViewById<Button>(R.id.bAdd)

        bAdd.setOnClickListener {
            if (etName.text.isNotEmpty() && etT1.text.isNotEmpty() && etT2.text.isNotEmpty() &&
                etT3.text.isNotEmpty()){
                val DB = DBHlpr(applicationContext)
                var state = DB.SaveValues(
                    etName.text.toString(),
                    etT1.text.toString(),
                    etT2.text.toString(),
                    etT3.text.toString()
                )
                Toast.makeText(this,"Saved in row $state",Toast.LENGTH_SHORT).show()
                etName.text.clear()
                etT1.text.clear()
                etT2.text.clear()
                etT3.text.clear()
            }else{
                Toast.makeText(this,"Null Value",Toast.LENGTH_SHORT).show()
            }
        }

    }
}