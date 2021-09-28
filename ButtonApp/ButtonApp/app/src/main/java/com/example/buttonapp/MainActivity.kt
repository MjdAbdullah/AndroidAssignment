package com.example.buttonapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var bPlus : Button
    lateinit var bMinus : Button
    lateinit var tvMain : TextView
    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bPlus = findViewById(R.id.buttonPlus)
        bMinus = findViewById(R.id.buttonMinus)
        tvMain = findViewById(R.id.tvMain)

        bPlus.setOnClickListener { Plus() }
        bMinus.setOnClickListener { Minus() }

    }

    fun Plus(){
        num++
        tvMain.text = num.toString()
        CheckColor()
        Position()
    }
    fun Minus(){
        num--
        tvMain.text = num.toString()
        CheckColor()
        Position()
    }
    fun CheckColor(){
        if (num > 0){
            tvMain.setTextColor(Color.argb(255, 0, 255, 0))
        }else if (num < 0){
            tvMain.setTextColor(Color.argb(255, 255, 0, 0))
        }else{
            tvMain.setTextColor(Color.argb(255, 0, 0, 0))
        }
    }
    fun Position(){
        tvMain.translationX = num.toFloat()
    }

}