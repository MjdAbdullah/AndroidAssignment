package com.example.debuggingchallenge
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var llMain: LinearLayout
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var submitBtn: Button
    private lateinit var activeUsers: TextView

    private var users = arrayListOf(
        "Freddy",
        "Jason",
        "Ripley",
        "Poncho",
        "Saitama",
        "Archer",
        "Derek",
        "Pamela",
        "Simba",
        "Simon",
        "Retsy",
        "Peter",
        "Daria",
        "Smitty"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llMain = findViewById(R.id.llMain)
        userName = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        password2 = findViewById(R.id.etConfirmPassword)
        submitBtn = findViewById(R.id.btSubmit)
        submitBtn.setOnClickListener {
            // Add one more statement to check the conform of password, that call ConformPassword fun
            // withe and (&&)
            if(usernameAccepted(userName.text.toString().toLowerCase()) && passwordAccepted(password.text.toString())
                && ConformPassword(password.text.toString(),password2.text.toString()))
                {
                Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show()
                users.add(userName.text.toString().capitalize())
                // Add (.clear) to fix Bug: (entry fields stay on screen when the list of active users is displayed)
                userName.text.clear()
                userName.clearFocus()
                password.text.clear()
                password.clearFocus()
                password2.text.clear()
                password2.clearFocus()
                displayUsers()
                }
        }
        activeUsers = findViewById(R.id.tvActiveUsers)
    }

    private fun usernameAccepted(text: String): Boolean{
        val tvName1 = findViewById<TextView>(R.id.tvName1)
        val tvName2 = findViewById<TextView>(R.id.tvName2)
        val tvName3 = findViewById<TextView>(R.id.tvName3)
        // Add (for)to fix the Bug: (app sometimes lets me add an existing user's name)
        for(i in users.indices){
            if (users[i].toLowerCase() == text){
                Toast.makeText(this, "The username is already taken", Toast.LENGTH_LONG).show()
                return false
            }
        }
        //Add (else) for all (if) to fix Bug: (I sometimes get two error messages when adding a user)
        // Bonus : Add color, Red for unmet requirements .. Green for met requirements
        // when the user do the requirements will display green also will be red
        tvName1.setTextColor(Color.RED)
        tvName2.setTextColor(Color.RED)
        tvName3.setTextColor(Color.RED)
        if(text.length in 5..15){
            tvName1.setTextColor(Color.GREEN)
            if(!hasNumber(text)){
                tvName2.setTextColor(Color.GREEN)
                if(!hasSpecial(text) && !text.contains(" ")){
                    tvName3.setTextColor(Color.GREEN)
                    return true
                }else{
                    Toast.makeText(this, "The username cannot contain special characters or spaces", Toast.LENGTH_LONG).show()
                }
            }else {
                Toast.makeText(this, "The username cannot contain numbers", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "The username must be between 5 and 15 characters long", Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun passwordAccepted(text: String): Boolean{
        val tvPass1 = findViewById<TextView>(R.id.tvPass1)
        val tvPass2 = findViewById<TextView>(R.id.tvPass2)
        val tvPass3 = findViewById<TextView>(R.id.tvPass3)
        val tvPass4 = findViewById<TextView>(R.id.tvPass4)

        tvPass1.setTextColor(Color.RED)
        tvPass2.setTextColor(Color.RED)
        tvPass3.setTextColor(Color.RED)
        tvPass4.setTextColor(Color.RED)
        //Add (else) for all (if) to fix Bugs: (I sometimes get two error messages when adding a user)
        // Bonus : Add color, Red for unmet requirements .. Green for met requirements
        // when the user do the requirements will display green also will be red
        if(text.length >= 8){
            tvPass1.setTextColor(Color.GREEN)
            if(hasUpper(text)){
                tvPass2.setTextColor(Color.GREEN)
                if(hasNumber(text)){
                    tvPass3.setTextColor(Color.GREEN)
                    if(hasSpecial(text)){
                        tvPass4.setTextColor(Color.GREEN)
                        return true
                    }else{
                        Toast.makeText(this, "The password must contain a special character", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "The password must contain a number", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "The password must contain an uppercase letter", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "The password must be at least 8 characters long", Toast.LENGTH_LONG).show()
        }
        return false
    }
    // Add this function to fix Bug: (account is created even when I don't enter a second password)
    private fun ConformPassword(text1: String , text2: String):Boolean{
        if (text1.equals(text2)){
            return true
        }
        password2.text.clear()
        Toast.makeText(this, "The password is not match", Toast.LENGTH_LONG).show()
        return false
    }
    // Update this function to fix Bug:
    // (passwords get rejected even when use a capital letter)
    private fun hasUpper(text: String): Boolean{
        for(i in 'A'..'Z'){
            if (text.contains(i)){
                return true
            }
        }
        return false
    }

    private fun hasNumber(text: String): Boolean{
        for(i in 0..9){
            if(text.contains(i.toString())){
                return true
            }
        }
        return false
    }

    private fun hasSpecial(text: String): Boolean{
        val specialCharacters = "!@#$%"
        for(special in specialCharacters){
            if(text.contains(special)){
                return true
            }
        }
        return false
    }

    private fun displayUsers(){
        // Add color in XML for this TextView
        // android:background="@color/white"
        var allUsers = "Active Users:\n\n"
        for(user in users){
            allUsers += user + "\n"
        }
        activeUsers.text = allUsers
        activeUsers.isVisible = true
    }
}