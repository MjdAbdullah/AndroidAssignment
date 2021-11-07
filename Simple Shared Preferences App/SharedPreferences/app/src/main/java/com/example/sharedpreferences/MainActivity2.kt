package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    lateinit var name1 : String
    lateinit var location : String
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvLocation = findViewById<TextView>(R.id.tvLocation)
        val bSH = findViewById<Button>(R.id.bSH)
        val intent = getIntent()

        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        name1 = intent.getStringExtra("Name").toString()
        location = intent.getStringExtra("Location").toString()
        tvName.text = name1
        tvLocation.text = location

        bSH.setOnClickListener {
            tvName.text = sharedPreferences.getString("Name",name1).toString()
            tvLocation.text = sharedPreferences.getString("Location",location).toString()
        }
    }
}