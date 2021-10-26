package com.example.signup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class UserPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvPassword = findViewById<TextView>(R.id.tvPassword)
        val bLogOut = findViewById<Button>(R.id.bLogOut)

        val intent = getIntent()
        tvName.text = "Name : " + intent.getStringExtra("UserName")
        tvEmail.text = "Email : " + intent.getStringExtra("UserEmail")
        tvPhone.text = "Phone : " + intent.getStringExtra("UserPhone")
        tvPassword.text = "Password : " + intent.getStringExtra("UserPassword")

        bLogOut.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}