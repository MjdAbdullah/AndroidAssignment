package com.example.signup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignIn : AppCompatActivity() {

    lateinit var etIN_pass : EditText
    lateinit var etIN_name : EditText

    companion object{
        var arr = ArrayList<UserDetails>()
        lateinit var user : UserDetails
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etIN_name = findViewById(R.id.etIN_name)
        etIN_pass = findViewById(R.id.etIN_pass)
        val DB = DBHlpr(applicationContext)
        arr = DB.getData()

        val bIN_enter = findViewById<Button>(R.id.bIN_enter)
        bIN_enter.setOnClickListener {
            if (etIN_name.text.isNotEmpty() && etIN_pass.text.isNotEmpty()){
                if (CheckUserName()){
                    Open()
                }else{
                    Toast.makeText(this,"Wrong!! username or password",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty Value",Toast.LENGTH_SHORT).show()
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    // Check user name
    private fun CheckUserName(): Boolean {
        for (i in arr){
            if(i.Name == etIN_name.text.toString() && i.Password == etIN_pass.text.toString()){
                user = i
                return true
            }
        }
        return false
    }
    //----------------------------------------------------------------------------------------------
    // open user page.
    private fun Open() {
        var intent = Intent(this,UserPage::class.java)
        intent.putExtra("UserName",user.Name)
        intent.putExtra("UserEmail",user.Email)
        intent.putExtra("UserPhone",user.Phone)
        intent.putExtra("UserPassword",user.Password)
        startActivity(intent)
    }
}