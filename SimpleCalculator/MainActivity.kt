package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var etNumber1 : EditText
        lateinit var etNumber2 : EditText
        var Result = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val bAdd = findViewById<Button>(R.id.bAdd)
        val bSub = findViewById<Button>(R.id.bSub)
        val bMultiply = findViewById<Button>(R.id.bMultiply)
        val bDivid = findViewById<Button>(R.id.bDivid)
        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById<EditText>(R.id.etNumber2)

        bAdd.setOnClickListener {
            if(Check()){ tvResult.text = "Result ${etNumber1.text.toString().toInt() + etNumber2.text.toString().toInt()}" }
            Clear()
        }
        bSub.setOnClickListener {
            if(Check()){ tvResult.text = "Result ${etNumber1.text.toString().toInt() - etNumber2.text.toString().toInt()}" }
            Clear()
        }
        bMultiply.setOnClickListener {
            if(Check()){ tvResult.text = "Result ${etNumber1.text.toString().toInt() * etNumber2.text.toString().toInt()}" }
            Clear()
        }
        bDivid.setOnClickListener {
            if(Check()){
                if(etNumber2.text.toString().toInt() != 0){
                    tvResult.text = "Result ${etNumber1.text.toString().toInt() / etNumber2.text.toString().toInt()}"
                }else{
                    Toast.makeText(this,"Can't Divide in 0", Toast.LENGTH_LONG).show()
                }
            }
            Clear()
        }
    }

    fun Check(): Boolean {
        if (etNumber1.text.isNotEmpty() && etNumber2.text.isNotEmpty()){
            return true
        }else{
            Toast.makeText(this,"NO VALUE", Toast.LENGTH_LONG).show()
            return false
        }
    }
    fun Clear(){
        etNumber1.text.clear()
        etNumber1.clearFocus()
        etNumber2.text.clear()
        etNumber2.clearFocus()
    }
}