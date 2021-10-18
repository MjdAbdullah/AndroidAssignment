package com.example.arraylistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object {
        var arr = arrayListOf<String>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvShow = findViewById<TextView>(R.id.textView)
        val bName = findViewById<Button>(R.id.bName)
        val bNumber = findViewById<Button>(R.id.bNumber)
        val etName = findViewById<EditText>(R.id.etName)
        val etNumber = findViewById<EditText>(R.id.etNumber)

        bName.setOnClickListener {
            if(etName.text.isNotEmpty()){
                arr.add(etName.text.toString())
                etName.text.clear()
                etName.clearFocus()
            }else{
                Toast.makeText(this,"Empty Value",Toast.LENGTH_LONG).show()
            }
        }
        bNumber.setOnClickListener {
            if (etNumber.text.isNotEmpty()){
                val num = etNumber.text.toString().toInt()-1
                etNumber.clearFocus()
                etNumber.text.clear()
                if (num < arr.size){
                    tvShow.text = arr[num]
                }else{
                    tvShow.text = "NULL"
                }
            }else{
                Toast.makeText(this,"Empty Value",Toast.LENGTH_LONG).show()
            }
        }

    }
}