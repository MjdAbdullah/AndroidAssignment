package com.example.signup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity() {

    lateinit var bUp_enter : Button
    lateinit var etUp_name : EditText
    lateinit var etUp_password : EditText
    lateinit var etUp_phone : EditText
    lateinit var etUp_email : EditText
    companion object{
        var arr = ArrayList<UserDetails>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        bUp_enter = findViewById(R.id.bUp_enter)
        etUp_name = findViewById(R.id.etUp_name)
        etUp_password = findViewById(R.id.etUp_password)
        etUp_phone = findViewById(R.id.etUp_phone)
        etUp_email = findViewById(R.id.etUp_email)

        var DB = DBHlpr(applicationContext)
        arr = DB.getData()

        bUp_enter.setOnClickListener {
            if (etUp_name.text.isNotEmpty() && etUp_email.text.isNotEmpty() && etUp_password.text.isNotEmpty()
                && etUp_phone.text.isNotEmpty()){

                    var UserName = etUp_name.text.toString().replace(" ","").toLowerCase()
                    var UserEmail = etUp_email.text.toString().replace(" ","")
                    var UserPhone = etUp_phone.text.toString().replace(" ","")
                    var UserPassword = etUp_password.text.toString().replace(" ","")

                if(AvailableName(UserName)){

                    var state = DB.putData(UserDetails(UserName,UserEmail,UserPhone,UserPassword))
                    Toast.makeText(this@SignUp, "Complete Add User in $state", Toast.LENGTH_SHORT).show()
                    ClearText()
                    Open(UserDetails(UserName,UserEmail,UserPhone,UserPassword))

                }else{
                    Toast.makeText(this@SignUp, "The User Name Expect.", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this@SignUp, "There is Empty Value.", Toast.LENGTH_SHORT).show()
            }
        }

    }
    //-----------------------------------------------------------------------------------------------
    //Check if the user Name expect or not.
    fun AvailableName(s: String): Boolean {
        for(i in arr){
            if (i.Name == s){
                return false
            }
        }
        return true
    }
    //-----------------------------------------------------------------------------------------------
    // remove text
    private fun ClearText(){
        etUp_name.text.clear()
        etUp_email.text.clear()
        etUp_password.text.clear()
        etUp_phone.text.clear()
    }
    //------------------------------------------------------------------------------------------------
    // Open userPage
    private fun Open(user: UserDetails) {
        var intent = Intent(this,UserPage::class.java)
        intent.putExtra("UserName",user.Name)
        intent.putExtra("UserEmail",user.Email)
        intent.putExtra("UserPhone",user.Phone)
        intent.putExtra("UserPassword",user.Password)
        startActivity(intent)
    }
}