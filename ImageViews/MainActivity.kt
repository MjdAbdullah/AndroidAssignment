package com.example.imageviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bImage1 = findViewById<Button>(R.id.bImage1)
        val bImage2 = findViewById<Button>(R.id.bImage2)
        val ivImage = findViewById<ImageView>(R.id.ivImage)

        bImage1.setOnClickListener { ivImage.setImageResource(R.drawable.images) }
        bImage2.setOnClickListener { ivImage.setImageResource(R.drawable.imagesw) }
    }
}