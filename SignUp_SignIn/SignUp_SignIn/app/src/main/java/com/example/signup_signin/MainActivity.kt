package com.example.signup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bSignIn = findViewById<Button>(R.id.bSignIn)
        val bSignUp = findViewById<Button>(R.id.bSignUp)

        bSignIn.setOnClickListener {
            startActivity(Intent(this,SignIn::class.java))
        }

        bSignUp.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }

    }
}