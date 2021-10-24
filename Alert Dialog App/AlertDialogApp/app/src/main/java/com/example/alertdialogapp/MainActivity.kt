package com.example.alertdialogapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bShow = findViewById<Button>(R.id.bShow)
        bShow.setOnClickListener {
            MessageAlert()
        }

    }

    fun MessageAlert(){
        val alert = AlertDialog.Builder(this)
        val L = LinearLayout(this)
        val etText = EditText(this)
        val tvText = TextView(this)
        val bGO = Button(this)
        val bText = Button(this)
        bGO.text = "GO"
        bText.text = "TEXT"
        bGO.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("Value",etText.text.toString())
            startActivity(intent)
        }
        bText.setOnClickListener {
            tvText.text = etText.text.toString()
        }
        tvText.apply {
            setTextColor(Color.BLACK)
            textSize = 20f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        etText.hint = "Enter Text"
        L.orientation = LinearLayout.VERTICAL
        L.addView(etText)
        L.addView(tvText)
        L.addView(bGO)
        L.addView(bText)

        alert.setTitle("Alert Message")
            .setView(L)
            .show()
    }
}