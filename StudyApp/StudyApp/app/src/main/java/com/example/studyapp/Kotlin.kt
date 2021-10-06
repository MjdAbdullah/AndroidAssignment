package com.example.studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Kotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        title = "Kotlin Review"

        val array = arrayListOf(
            arrayListOf("var and val", "Declaring variables", "val  is immutable, var is mutable"),
            arrayListOf("User Input", "Getting user input", "Give user prompt before to\nthey can enter any text\nCode : readLine()"),
            arrayListOf("Strings", "String concatenations, interpolation, and methods", "can use $ for variables inside String"),
            arrayListOf("Data Types", "Understanding data types.", "Int - Flot - Double for number \nString for text\n char for Chareacther"),
            arrayListOf("Basie Operation","Performing math operations in kotlin","+ - / *")
        )

        val rvAndroid = findViewById<RecyclerView>(R.id.rvKotlin)
        rvAndroid.adapter = RecyclerViewAdapter(this, array)
        rvAndroid.layoutManager = LinearLayoutManager(this)
    }
}