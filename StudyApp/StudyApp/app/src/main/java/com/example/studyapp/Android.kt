package com.example.studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Android : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)

        title = "Android Review"

        val array = arrayListOf(
            arrayListOf("Project Setup", "Setting up an Android Studio Project.", "Detailed......"),
            arrayListOf("Console", "Printing to the console.", "Detailed......")
        )

        val rvAndroid = findViewById<RecyclerView>(R.id.rvAndroid)
        rvAndroid.adapter = RecyclerViewAdapter(this, array)
        rvAndroid.layoutManager = LinearLayoutManager(this)
    }
}